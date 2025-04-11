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

function ProductList() {
  const theme = useMantineTheme();
  return (
    <Stack>
      <Title c={theme.colors.gray[7]}>All products</Title>
      <SimpleGrid cols={3}>
        <Paper mih="150px" shadow="md" p="md" m="md">
          <Stack align="center" justify="center" gap={0}>
            <Image
              fit="fill"
              radius="md"
              src="https://media.istockphoto.com/id/1055079680/vector/black-linear-photo-camera-like-no-image-available.jpg?s=612x612&w=0&k=20&c=P1DebpeMIAtXj_ZbVsKVvg-duuL0v9DlrOZUvPG6UJk="
            />
            <Group align="center" justify="center" w="100%">
              <Badge size="xs" variant="light" color="green">
                Vegetables
              </Badge>
            </Group>
            <Stack gap={0} align="center" justify="center">
              <Text fw={500} size="md" ta="center">
                Chinese Bokchoy from China
              </Text>
              <Text fw={700}>$5.50</Text>
            </Stack>
            <Group>
              <Button variant="light" color="red" fullWidth type="button">
                Add to cart
              </Button>
            </Group>
          </Stack>
        </Paper>
      </SimpleGrid>
    </Stack>
  );
}

export default ProductList;
