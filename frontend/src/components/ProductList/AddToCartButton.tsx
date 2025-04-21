import { Button } from "@mantine/core";
import useAddToCart from "./useAddToCart";

function AddToCartButton({ id, name }: { id: number; name: string }) {
  const { addToCartMutation } = useAddToCart({ id, name });
  return (
    <Button
      variant="light"
      fullWidth
      color="orange"
      onClick={() => addToCartMutation()}
    >
      Add to cart
    </Button>
  );
}

export default AddToCartButton;
