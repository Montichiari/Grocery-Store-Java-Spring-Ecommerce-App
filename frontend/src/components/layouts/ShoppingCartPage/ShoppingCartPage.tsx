import GenericTable from "@/components/GenericTable/GenericTable";
import { ShoppingCartItem } from "@/types/ShoppingCart.types";
import {
  ActionIcon,
  Box,
  Group,
  Image,
  NumberInput,
  Text,
  Title,
} from "@mantine/core";
import { IconEdit, IconTrash } from "@tabler/icons-react";
import useShoppingCart from "./useShoppingCart.hooks";
import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";

function ShoppingCartPage() {
  const { cartItems, setCartItems, isLoading } = useShoppingCart();
  const handleInputChange = (cartItemId: number, value: number) => {
    const newValues = [...cartItems];
    newValues.map((item) => {
      if (item.id === cartItemId) {
        item.quantity = value;
      }
      return item;
    });
    setCartItems(newValues);
  };

  if (isLoading) return <ComponentLoader />;
  if (!cartItems || cartItems.length === 0)
    return <Box>No items in shopping cart.</Box>;

  return (
    <Box>
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
                <NumberInput
                  clampBehavior="strict"
                  defaultValue={cartItem.quantity}
                  onChange={(value) =>
                    handleInputChange(cartItem.id, Number(value))
                  }
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
            render: () => (
              <Group>
                <ActionIcon variant="light" color="orange">
                  <IconEdit
                    style={{ width: "70%", height: "70%" }}
                    stroke={1.5}
                  />
                </ActionIcon>
                <ActionIcon variant="light" color="red">
                  <IconTrash
                    style={{ width: "70%", height: "70%" }}
                    stroke={1.5}
                  />
                </ActionIcon>
              </Group>
            ),
          },
        ]}
        tableData={cartItems}
      />
      <Group justify="flex-start" my="md">
        <Title order={2} fw={700}>
          Grand Total:
        </Title>
        <Text size="lg">
          $
          {cartItems
            .reduce(
              (grandTotal, currItem) =>
                currItem.quantity * currItem.product.unitPrice + grandTotal,
              0
            )
            .toFixed(2)}
        </Text>
      </Group>
    </Box>
  );
}

export default ShoppingCartPage;
