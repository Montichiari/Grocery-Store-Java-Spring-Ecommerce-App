import { useUserStore } from "@/stores/UserStore";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { Button } from "@mantine/core";
import { useMutation, useQueryClient } from "@tanstack/react-query";

function ClearShoppingCartButton() {
  const queryClient = useQueryClient();
  const { user } = useUserStore();
  const { mutateAsync: clearCartMutation } = useMutation({
    mutationFn: async () => await api.delete<void>("cart/clear"),
    onSuccess: (data) => {
      if (data.status && data.status > 400)
        notify.error(
          "Unable to clear cart",
          "There seems to be an issue with clearing your cart."
        );
      else {
        notify.success(
          "Cart Cleared!",
          "All your items in your cart have been removed!"
        );

        queryClient.invalidateQueries({
          queryKey: ["shopping-cart", user.id],
        });
      }
    },
  });
  return (
    <Button variant="light" color="gray" onClick={() => clearCartMutation()}>
      Clear Shopping Cart
    </Button>
  );
}

export default ClearShoppingCartButton;
