import { useCheckoutStore } from "@/stores/OrderConfirmationStore";
import dateFormat from "@/utils/DateUtil";
import { Box, Stack, Table, Text, Title } from "@mantine/core";

function ShoppingCartConfirmation() {
  const { checkoutDetails } = useCheckoutStore();

  // console.log(checkoutDetails);
  if (checkoutDetails === undefined) return;
  return (
    <Box>
      <Stack>
        <Title order={2}>Thank you for shopping with us!</Title>
        <Text>Here's a summary of your purchase:</Text>
        <Table>
          <Table.Thead>
            <Table.Tr>
              <Table.Th>Invoice ID:</Table.Th>
              <Table.Th>Purchased on:</Table.Th>
              <Table.Th>Fulfillment Date:</Table.Th>
              <Table.Th>Status:</Table.Th>
              <Table.Th>Payment Method:</Table.Th>
            </Table.Tr>
          </Table.Thead>
          <Table.Tbody>
            <Table.Tr>
              <Table.Td>{checkoutDetails.id}</Table.Td>
              <Table.Td>
                {dateFormat.formatter(checkoutDetails.createAt)}
              </Table.Td>
              <Table.Td>
                {dateFormat.formatter(checkoutDetails.fulfilmentDate)}
              </Table.Td>
              <Table.Td>{checkoutDetails.status}</Table.Td>
              <Table.Td>{checkoutDetails.paymentMethod}</Table.Td>
            </Table.Tr>
          </Table.Tbody>
        </Table>
        <Text>We hope to see you again!</Text>
      </Stack>
    </Box>
  );
}

export default ShoppingCartConfirmation;
