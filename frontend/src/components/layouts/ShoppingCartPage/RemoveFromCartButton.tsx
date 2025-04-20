import { useUser } from "@/stores/UserStore";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { ActionIcon } from "@mantine/core";
import { IconTrash } from "@tabler/icons-react";
import { useMutation, useQueryClient } from "@tanstack/react-query";

function RemoveFromCartButton({ id, name }: { id: number; name: string }) {
  const queryClient = useQueryClient();
  const user = useUser();
  const { mutateAsync: deleteCartItemMutation } = useMutation({
    mutationFn: async () => await api.delete(`cart/remove/${id}`),
    onSuccess: (data) => {
      if (data.status && data.status >= 400)
        notify.error(
          "Unable to remove product",
          "Something has gone wrong, the product hasn't been deleted from your cart."
        );
      else {
        notify.success(
          "Product deleted!",
          `${name} has successfully been removed from your cart!`
        );
        queryClient.invalidateQueries({
          queryKey: ["shopping-cart", user.id],
        });
      }
    },
  });
  return (
    <ActionIcon
      variant="light"
      color="red"
      onClick={() => deleteCartItemMutation()}
    >
      <IconTrash style={{ width: "70%", height: "70%" }} stroke={1.5} />
    </ActionIcon>
  );
}

export default RemoveFromCartButton;
