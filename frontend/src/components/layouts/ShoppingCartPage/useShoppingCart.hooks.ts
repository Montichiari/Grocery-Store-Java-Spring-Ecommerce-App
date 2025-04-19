import { useUserStore } from "@/stores/UserStore";
import { ShoppingCartItem } from "@/types/ShoppingCart.types";
import api from "@/utils/API";
import { useQuery } from "@tanstack/react-query";
import { useState, useEffect } from "react";

export default function useShoppingCart() {
  const { user } = useUserStore();
  const { data, isLoading } = useQuery({
    queryKey: ["shopping-cart", user.id],
    queryFn: async () => await api.get<ShoppingCartItem[]>("cart/items"),
  });
  const [cartItems, setCartItems] = useState<ShoppingCartItem[]>(
    data?.data ? data.data : []
  );
  useEffect(() => {
    if (data?.data) setCartItems(data.data);
  }, [data]);

  return { isLoading, cartItems, setCartItems };
}
