import GenericTable from "@/components/GenericTable/GenericTable";
import { ShoppingCartItem } from "@/types/ShoppingCart.types";
import { Box, Group, Image, Stack, Text, Title } from "@mantine/core";
import useShoppingCart from "./useShoppingCart.hooks";
import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import ShoppingCartPaymentModal from "./ShoppingCartPaymentModal";
import RemoveFromCartButton from "./RemoveFromCartButton";
import ProductQuantityButton from "./ProductQuantityButton";
import ClearShoppingCartButton from "./ClearShoppingCartButton";

function ShoppingCartPage() {
  const { cartItems, isLoading } = useShoppingCart();

  if (isLoading) return <ComponentLoader />;
  if (!cartItems || !cartItems.data || cartItems.data.length === 0)
    return <Box>No items found in shopping cart.</Box>;

  return (
    <Box pb="xl">
      <ClearShoppingCartButton />
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
              return (
                <ProductQuantityButton
                  id={cartItem.product.id}
                  name={cartItem.product.name}
                  defaultQuantity={cartItem.quantity}
                />
              );
            },
          },
          {
            accessor: "unitPrice",
            sortable: true,
            width: 100,
            render: (record) => {
              const cartItem = record as ShoppingCartItem;
              return `$${cartItem.product.unitPrice.toFixed(2)}`;
            },
          },
          {
            accessor: "totalPrice",
            sortable: true,
            width: 100,
            render: (record) => {
              const cartItem = record as ShoppingCartItem;
              return `$${(
                cartItem.quantity * cartItem.product.unitPrice
              ).toFixed(2)}`;
            },
          },
          {
            accessor: "Actions",
            render: (record) => {
              const cartItem = record as ShoppingCartItem;
              return (
                <RemoveFromCartButton
                  id={cartItem.id}
                  name={cartItem.product.name}
                />
              );
            },
          },
        ]}
        tableData={cartItems.data}
      />
      <Stack gap={0}>
        <Group justify="flex-start" my="md">
          <Title order={2} fw={700}>
            Grand Total:
          </Title>
          <Text size="lg">
            $
            {cartItems.data
              .reduce(
                (grandTotal, currItem) =>
                  currItem.quantity * currItem.product.unitPrice + grandTotal,
                0
              )
              .toFixed(2)}
          </Text>
        </Group>
        <ShoppingCartPaymentModal
          totalCost={cartItems.data.reduce(
            (grandTotal, currItem) =>
              currItem.quantity * currItem.product.unitPrice + grandTotal,
            0
          )}
        />
      </Stack>
    </Box>
  );
}

export default ShoppingCartPage;
