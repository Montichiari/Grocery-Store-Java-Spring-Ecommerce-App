import {
  Image,
  Paper,
  SimpleGrid,
  Stack,
  Title,
  useMantineTheme,
} from "@mantine/core";

function ProductList() {
  const theme = useMantineTheme();
  return (
    <Stack>
      <Title c={theme.colors.gray[7]}>All products</Title>
      <SimpleGrid cols={4}>
        <Paper mih="150px" shadow="md" p="md" m="md">
          <Stack align="flex-start" justify="center">
            <Image
              fit="contain"
              h={50}
              radius="md"
              src="https://media.istockphoto.com/id/1055079680/vector/black-linear-photo-camera-like-no-image-available.jpg?s=612x612&w=0&k=20&c=P1DebpeMIAtXj_ZbVsKVvg-duuL0v9DlrOZUvPG6UJk="
            />
            Hello world
          </Stack>
        </Paper>
      </SimpleGrid>
    </Stack>
  );
}

export default ProductList;
