import { useCheckoutStore } from "@/stores/OrderConfirmationStore";
import { useUserStore } from "@/stores/UserStore";
import { CheckoutDetails } from "@/types/Checkout.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { useState } from "react";
import { useNavigate } from "react-router";

export default function usePayment() {
  const { user } = useUserStore();
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const { setCheckoutState } = useCheckoutStore();
  const [paymentMethod, setPaymentMethod] = useState<string | null>("");
  const { mutateAsync: checkoutMutation } = useMutation({
    mutationFn: async () =>
      await api.post<{}, CheckoutDetails>("order/checkout", {
        paymentMethod: paymentMethod,
      }),
    onSuccess: async (data) => {
      console.log(data);
      notify.success("Payment Successful!", "Your payment has been completed!");
      if (data.data !== undefined) setCheckoutState(data.data);
      await queryClient.invalidateQueries({
        queryKey: ["shopping-cart", user.id],
      });
      await queryClient.invalidateQueries({
        queryKey: ["all-products"],
      });
      await queryClient.invalidateQueries({
        queryKey: ["order-list", user.id],
      });
      navigate("/shop/confirmation");
    },
  });

  return { paymentMethod, setPaymentMethod, checkoutMutation };
}
