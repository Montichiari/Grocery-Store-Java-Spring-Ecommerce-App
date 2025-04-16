import {
  Badge,
  Button,
  Group,
  Image,
  Paper,
  SimpleGrid,
  Stack,
  Text,
  Title,
  useMantineTheme,
} from "@mantine/core";
import Product from "./Product";

function ProductList() {
  const theme = useMantineTheme();
  return (
    <Stack>
      <SimpleGrid cols={4}>
        {[...new Array(50)].map((_) => (
          <Product />
        ))}
      </SimpleGrid>
    </Stack>
  );
}

export default ProductList;
