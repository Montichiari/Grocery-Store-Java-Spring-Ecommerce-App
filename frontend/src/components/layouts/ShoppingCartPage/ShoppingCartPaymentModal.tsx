import { BaseModal } from "@/components/BaseModal/BaseModal";
import { AllowedPaymentMethods, CheckoutDetails } from "@/types/Checkout.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { Group, Button, Text, Select, Stack } from "@mantine/core";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import { useUserStore } from "@/stores/UserStore";
import { useCheckoutStore } from "@/stores/OrderConfirmationStore";
import { useNavigate } from "react-router";

type ShoppingCartPaymentModalProps = {
  totalCost: number;
};
function ShoppingCartPaymentModal({ ...props }: ShoppingCartPaymentModalProps) {
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
      navigate("/shop/confirmation");
    },
  });
  return (
    <Group>
      <BaseModal>
        <BaseModal.ClickTarget>
          <Button variant="light" color="orange" fullWidth>
            Make Payment
          </Button>
        </BaseModal.ClickTarget>
        <BaseModal.Content title="Payment Details">
          <Stack py="md">
            <Group gap="md">
              <Text size="sm">Amount to charge:</Text>
              <Text fw={700}>${props.totalCost.toFixed(2)}</Text>
            </Group>
            <Select
              value={paymentMethod}
              onChange={setPaymentMethod}
              label="Available payment methods"
              placeholder="Pick payment method"
              data={
                [
                  "Credit Card",
                  "Paypal",
                  "Bank Transfer",
                ] as AllowedPaymentMethods[]
              }
            />
            <Button
              variant="light"
              color="blue"
              fullWidth
              onClick={() => checkoutMutation()}
            >
              Pay now
            </Button>
          </Stack>
        </BaseModal.Content>
      </BaseModal>
    </Group>
  );
}

export default ShoppingCartPaymentModal;
