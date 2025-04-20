import { OrderInfoSummary } from "@/types/Order.types";
import api from "@/utils/API";
import {
  Badge,
  Box,
  Group,
  Paper,
  SimpleGrid,
  Text,
  Title,
} from "@mantine/core";
import { useQuery } from "@tanstack/react-query";

function SalesSummary() {
  const { data: weeklySales } = useQuery({
    queryKey: ["weekly-sales-summary"],
    queryFn: async () =>
      await api.get<OrderInfoSummary[]>("admin/weekly-orders"),
  });
  if (!weeklySales || !weeklySales.data) return null;
  return (
    <Box my="md">
      <SimpleGrid cols={4}>
        <Paper shadow="md" withBorder p="md">
          <Group>
            <Badge variant="light" size="sm" color="red">
              Weekly sales
            </Badge>
          </Group>
          <Title>{weeklySales.data.reduce((total) => (total += 1), 0)}</Title>
          <Text size="sm" color="dimmed">
            orders made in the past week
          </Text>
        </Paper>
        <Paper shadow="md" withBorder p="md">
          <Group>
            <Badge variant="light" size="sm" color="blue">
              Orders Delivered
            </Badge>
          </Group>
          <Title>
            {weeklySales.data.reduce((total, sale) => {
              if (sale.status.toLowerCase() === "Delivered".toLowerCase())
                return (total += 1);
              return total;
            }, 0)}
          </Title>
          <Text size="sm" color="dimmed">
            orders delivered in past week
          </Text>
        </Paper>
        <Paper shadow="md" withBorder p="md">
          <Group>
            <Badge variant="light" size="sm" color="orange">
              Orders Processing
            </Badge>
          </Group>
          <Title>
            {weeklySales.data.reduce((total, sale) => {
              if (sale.status.toLowerCase() === "Processing".toLowerCase())
                return (total += 1);
              return total;
            }, 0)}
          </Title>
          <Text size="sm" color="dimmed">
            orders processing in the past week
          </Text>
        </Paper>
        <Paper shadow="md" withBorder p="md">
          <Group>
            <Badge variant="light" size="sm" color="green">
              Orders Shipped
            </Badge>
          </Group>
          <Title>
            {weeklySales.data.reduce((total, sale) => {
              if (sale.status.toLowerCase() === "Shipped".toLowerCase())
                return (total += 1);
              return total;
            }, 0)}
          </Title>
          <Text size="sm" color="dimmed">
            orders shipped in the past week
          </Text>
        </Paper>
      </SimpleGrid>
    </Box>
  );
}

export default SalesSummary;
