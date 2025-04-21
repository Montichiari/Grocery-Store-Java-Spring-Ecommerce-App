import { BaseModal } from "@/components/BaseModal/BaseModal";
import GenericTable from "@/components/GenericTable/GenericTable";
import { OrderInfo } from "@/types/Order.types";
import { ShoppingCartItem } from "@/types/ShoppingCart.types";
import { Button, Image, Stack, Text } from "@mantine/core";

function PastOrderModal(order: OrderInfo) {
  return (
    <BaseModal>
      <BaseModal.ClickTarget>
        <Button fullWidth variant="light">
          View Order Details
        </Button>
      </BaseModal.ClickTarget>
      <BaseModal.Content size="xl" title={`Order ID ${order.id} Details`}>
        <Stack>
          <GenericTable
            columnData={[
              {
                accessor: "pic",
                title: "",
                render: (record) => {
                  const cartItem = record as ShoppingCartItem;
                  return (
                    <Image
                      src={cartItem.product.image}
                      radius="md"
                      fit="contain"
                      w={100}
                      mx="auto"
                    />
                  );
                },
              },
              {
                accessor: "name",
                sortable: true,
                render: (record) => {
                  const cartItem = record as ShoppingCartItem;
                  return cartItem.product.name;
                },
              },
              {
                accessor: "category",
                sortable: true,
                render: (record) => {
                  const cartItem = record as ShoppingCartItem;
                  return cartItem.product.category;
                },
              },
              {
                accessor: "quantity",
                sortable: true,
                width: 100,
                render: (record) => {
                  const cartItem = record as ShoppingCartItem;
                  return cartItem.quantity;
                },
              },
              {
                accessor: "unitPrice",
                sortable: true,
                width: 100,
                render: (record) => {
                  const cartItem = record as ShoppingCartItem;
                  return `$${cartItem.unitPrice?.toFixed(2)}`;
                },
              },
              {
                accessor: "totalPrice",
                sortable: true,
                width: 100,
                render: (record) => {
                  const cartItem = record as ShoppingCartItem;
                  return `$${(
                    cartItem.quantity *
                    (cartItem.unitPrice ? cartItem.unitPrice : 0)
                  ).toFixed(2)}`;
                },
              },
            ]}
            tableData={order.orderItems as ShoppingCartItem[]}
          />
          <Text>Grand Total: ${(order.totalAmount as number).toFixed(2)}</Text>
        </Stack>
      </BaseModal.Content>
    </BaseModal>
  );
}

export default PastOrderModal;
