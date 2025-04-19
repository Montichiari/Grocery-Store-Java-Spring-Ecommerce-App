import { Paper, Stack, Group, Badge, Image, Text, Button } from "@mantine/core";
import AddToCartButton from "./AddToCartButton";

type ProductCardProps = {
  id: number;
  category: string;
  name: string;
  stock: number;
  unitPrice: number;
  image?: string;
};

function Product({ ...props }: ProductCardProps) {
  const defaultImage =
    "https://media.istockphoto.com/id/1055079680/vector/black-linear-photo-camera-like-no-image-available.jpg?s=612x612&w=0&k=20&c=P1DebpeMIAtXj_ZbVsKVvg-duuL0v9DlrOZUvPG6UJk=";
  return (
    <Paper maw={300} shadow="md" p="md" m="md">
      <Stack h="100%" justify="space-between" gap={0}>
        <Image
          fit="contain"
          radius="md"
          w={250}
          mx="auto"
          src={props.image === "" ? defaultImage : props.image}
        />
        <Group my="md" align="flex-start" justify="flex-start">
          <Stack gap={0}>
            <Badge size="xs" variant="light" color="green">
              {props.category}
            </Badge>
            <Text fw={500} size="md" ta="left">
              {props.name}
            </Text>
            <Text fw={700}>${props.unitPrice.toFixed(2)}</Text>
            <Text size="xs" c="dimmed">
              {props.stock}x units remaining
            </Text>
          </Stack>
        </Group>
        {props.stock > 0 ? (
          <AddToCartButton id={props.id} name={props.name} />
        ) : (
          <Button variant="light" fullWidth disabled>
            Out of stock
          </Button>
        )}
      </Stack>
    </Paper>
  );
}

export default Product;
