import { BaseModal } from "@/components/BaseModal/BaseModal";
import { ProductCategories, ProductInfo } from "@/types/Product.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import {
  ActionIcon,
  Button,
  Input,
  NumberInput,
  Select,
  Stack,
} from "@mantine/core";
import { IconEdit } from "@tabler/icons-react";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { useState } from "react";

type EditProductSchema = {
  name: string;
  unitPrice: string;
  stock: number;
  category: string;
  image: string;
};

function EditProductButton(productInfo: ProductInfo) {
  const queryClient = useQueryClient();
  const { mutateAsync: editProductMutation } = useMutation({
    mutationFn: async () =>
      await api.patch<EditProductSchema, void>(
        `admin/product/${productInfo.id}`,
        {},
        productDetails
      ),
    onSuccess: (data) => {
      queryClient.invalidateQueries({
        queryKey: ["admin-product-list"],
      });
      if (!data.status || data.status >= 400) {
        notify.error(
          "Unable To Edit Product!",
          "Something has gone wrong, the product was not added to the database."
        );
      } else {
        notify.success(
          "New Product Added!",
          `Your product has successfully been edited!`
        );
      }
    },
  });
  const [productDetails, setProductDetails] = useState<EditProductSchema>({
    name: productInfo.name,
    unitPrice: productInfo.unitPrice.toString(),
    stock: productInfo.stock,
    category: productInfo.category,
    image: productInfo.image,
  });
  return (
    <BaseModal>
      <BaseModal.ClickTarget>
        <ActionIcon variant="light" color="orange">
          <IconEdit style={{ width: "70%", height: "70%" }} stroke={1.5} />
        </ActionIcon>
      </BaseModal.ClickTarget>
      <BaseModal.Content title={`Editing Product ${productInfo.id}`}>
        <Stack>
          <form onSubmit={(e) => e.preventDefault()}>
            <Input.Wrapper
              label="Product Name"
              description="Enter name of the product"
            >
              <Input
                placeholder="E.g. Beef Sausage Roll"
                defaultValue={productInfo.name}
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
                defaultValue={productInfo.image}
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
              defaultValue={productInfo.unitPrice}
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
              defaultValue={productInfo.stock}
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
              defaultValue={productInfo.category}
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
              color="orange"
              fullWidth
              onClick={() => editProductMutation()}
            >
              Edit product details
            </Button>
          </form>
        </Stack>
      </BaseModal.Content>
    </BaseModal>
  );
}

export default EditProductButton;
