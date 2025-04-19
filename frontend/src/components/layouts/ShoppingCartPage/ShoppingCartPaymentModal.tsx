import { BaseModal } from "@/components/BaseModal/BaseModal";
import { AllowedPaymentMethods, CheckoutDetails } from "@/types/Checkout.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { Group, Button, Text, Select, Stack } from "@mantine/core";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import ShoppingCartConfirmation from "./ShoppingCartConfirmation";
import { useUserStore } from "@/stores/UserStore";

type ShoppingCartPaymentModalProps = {
  totalCost: number;
};
function ShoppingCartPaymentModal({ ...props }: ShoppingCartPaymentModalProps) {
  const { user } = useUserStore();
  const queryClient = useQueryClient();
  const [paymentMethod, setPaymentMethod] = useState<string | null>("");
  const { mutateAsync: checkoutMutation, data: checkoutData } = useMutation({
    mutationFn: async () =>
      await api.post<{}, CheckoutDetails>("order/checkout", {
        paymentMethod: paymentMethod,
      }),
    onSuccess: async () => {
      notify.success("Payment Successful!", "Your payment has been completed!");
      await queryClient.invalidateQueries({
        queryKey: ["shopping-cart", user.id],
      });
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
          {!checkoutData?.data ? (
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
          ) : (
            <ShoppingCartConfirmation {...checkoutData.data} />
          )}
        </BaseModal.Content>
      </BaseModal>
    </Group>
  );
}

export default ShoppingCartPaymentModal;
