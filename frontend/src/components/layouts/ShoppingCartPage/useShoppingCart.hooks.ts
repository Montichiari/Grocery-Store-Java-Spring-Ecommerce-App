import { useUserStore } from "@/stores/UserStore";
import { ShoppingCartItem } from "@/types/ShoppingCart.types";
import api from "@/utils/API";
import { useQuery } from "@tanstack/react-query";

export default function useShoppingCart() {
  const { user } = useUserStore();
  const { data: cartItems, isLoading } = useQuery({
    queryKey: ["shopping-cart", user.id],
    queryFn: async () => await api.get<ShoppingCartItem[]>("cart/items"),
  });

  return { isLoading, cartItems };
}
