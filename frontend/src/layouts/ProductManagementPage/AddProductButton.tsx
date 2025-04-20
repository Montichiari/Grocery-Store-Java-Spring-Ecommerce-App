import { BaseModal } from "@/components/BaseModal/BaseModal";
import { ProductCategories } from "@/types/Product.types";
import { Button, Input, NumberInput, Select, Stack } from "@mantine/core";
import useAddProduct from "./useAddProduct.hooks";

function AddProductButton() {
  const { createProductMutation, productDetails, setProductDetails } =
    useAddProduct();
  return (
    <BaseModal>
      <BaseModal.ClickTarget>
        <Button variant="light" color="blue">
          Add new product
        </Button>
      </BaseModal.ClickTarget>
      <BaseModal.Content title="Add new product">
        <Stack>
          <form onSubmit={(e) => e.preventDefault()}>
            <Input.Wrapper
              label="Product Name"
              description="Enter name of the product"
            >
              <Input
                placeholder="E.g. Beef Sausage Roll"
                onChange={(event) =>
                  setProductDetails({
                    ...productDetails,
                    name: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper
              label="Image URL"
              description="Enter the product URL"
            >
              <Input
                placeholder="E.g. https://img.taste.com.au/VYcz0pA0/taste/2016/11/easy-sausage-rolls-28532-1.jpeg"
                onChange={(event) =>
                  setProductDetails({
                    ...productDetails,
                    image: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <NumberInput
              label="Unit Price"
              description="Enter the price per product unit"
              prefix="$"
              placeholder="E.g. $20.00"
              min={0}
              onChange={(event) =>
                setProductDetails({
                  ...productDetails,
                  unitPrice: Number(event.valueOf()).toFixed(2),
                })
              }
            />
            <NumberInput
              label="Stock"
              description="Enter the stocked amount of the product"
              placeholder="e.g. 114"
              allowNegative={false}
              onChange={(event) =>
                setProductDetails({
                  ...productDetails,
                  stock: Number(event.valueOf()),
                })
              }
            />
            <Select
              label="Category"
              description="Pick the closest category of the product"
              placeholder="Pick a category"
              data={
                [
                  "Beverages",
                  "Fruits",
                  "Household",
                  "Meat and Poultry",
                  "Prepared Meals",
                  "Seafood",
                  "Snacks and Pantry",
                  "Vegetables",
                ] as ProductCategories[]
              }
              onChange={(value) =>
                setProductDetails({
                  ...productDetails,
                  category: value ? value : "",
                })
              }
            />
            <Button
              my="md"
              type="submit"
              variant="light"
              color="blue"
              fullWidth
              onClick={() => createProductMutation()}
            >
              Create new product
            </Button>
          </form>
        </Stack>
      </BaseModal.Content>
    </BaseModal>
  );
}

export default AddProductButton;
