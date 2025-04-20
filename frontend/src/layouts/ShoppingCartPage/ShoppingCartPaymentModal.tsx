import { BaseModal } from "@/components/BaseModal/BaseModal";
import { AllowedPaymentMethods } from "@/types/Checkout.types";
import { Group, Button, Text, Select, Stack } from "@mantine/core";
import usePayment from "./usePayment.hooks";

type ShoppingCartPaymentModalProps = {
  totalCost: number;
};
function ShoppingCartPaymentModal({ ...props }: ShoppingCartPaymentModalProps) {
  const { checkoutMutation, paymentMethod, setPaymentMethod } = usePayment();
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
