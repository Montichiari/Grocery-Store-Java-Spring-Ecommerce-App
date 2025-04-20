import { useUser } from "@/stores/UserStore";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useQueryClient, useMutation } from "@tanstack/react-query";

export default function useAddToCart({
  id,
  name,
}: {
  id: number;
  name: string;
}) {
  const queryClient = useQueryClient();
  const user = useUser();
  const { mutateAsync: addToCartMutation } = useMutation({
    mutationFn: async () =>
      await api.post<void, void>(`cart/add/${id}`, { quantity: 1 }),
    onSuccess: (data) => {
      if (!data.data)
        notify.error(
          "Unable to add to Cart",
          `There seems to be an issue adding ${name} to your cart. Please try again later.`
        );
      else {
        notify.success(
          "Product Added to Cart!",
          `${name} has successfully been added to your shopping cart!`
        );
        queryClient.invalidateQueries({
          queryKey: ["shopping-cart", user.id],
        });
      }
    },
  });

  return { addToCartMutation };
}
