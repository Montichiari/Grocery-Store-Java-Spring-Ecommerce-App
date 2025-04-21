import { useUser } from "@/stores/UserStore";
import { OrderInfo } from "@/types/Order.types";
import api from "@/utils/API";
import { useQuery } from "@tanstack/react-query";
import { useEffect, useState } from "react";

export default function usePastOrders() {
  const user = useUser();
  const { data, isLoading } = useQuery({
    queryKey: ["order-list", user.id],
    queryFn: async () => await api.get<OrderInfo[]>("order/history"),
  });
  const [orderList, setOrderList] = useState<OrderInfo[]>(
    data?.data ? data.data : []
  );
  useEffect(() => {
    if (data?.data) setOrderList(data.data);
  }, [data]);

  return { orderList, isLoading };
}
