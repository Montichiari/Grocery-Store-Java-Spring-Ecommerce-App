import { useUserStore } from "@/stores/UserStore";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { NumberInput } from "@mantine/core";
import { useMutation, useQueryClient } from "@tanstack/react-query";

function ProductQuantityButton({
  id,
  name,
  defaultQuantity,
}: {
  id: number;
  name: string;
  defaultQuantity: number;
}) {
  const queryClient = useQueryClient();
  const { user } = useUserStore();
  const { mutateAsync: quantityMutation } = useMutation({
    mutationFn: async ({
      productId,
      quantity,
    }: {
      productId: number;
      quantity: number;
    }) =>
      await api.post<void, void>(`cart/add/${productId}`, {
        quantity: quantity,
      }),
    onSuccess: (data) => {
      if (!data.data)
        notify.error(
          "Unable adjust product quantity",
          `There seems to be an issue adjusting the quantities of ${name} in the cart. Please try again later.`
        );
      else {
        notify.success(
          `${name} quantity adjusted!`,
          `${name}'s quantity has been adjusted in your cart!`
        );
        queryClient.invalidateQueries({
          queryKey: ["shopping-cart", user.id],
        });
      }
    },
  });
  return (
    <NumberInput
      min={1}
      allowNegative={false}
      clampBehavior="strict"
      defaultValue={defaultQuantity}
      onChange={(value) =>
        quantityMutation({
          productId: id,
          quantity: Number(value) - defaultQuantity,
        })
      }
    />
  );
}

export default ProductQuantityButton;
