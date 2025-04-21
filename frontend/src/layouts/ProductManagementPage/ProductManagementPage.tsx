import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import GenericTable from "@/components/GenericTable/GenericTable";
import { ProductInfo } from "@/types/Product.types";
import api from "@/utils/API";
import { Box, Group, Image } from "@mantine/core";
import { useQuery } from "@tanstack/react-query";
import AddProductButton from "./AddProductButton";
import EditProductButton from "./EditProductButton";
import DeleteProductButton from "./DeleteProductButton";
import SalesSummary from "./SalesSummary";

function ProductManagementPage() {
  const { data: productList, isLoading } = useQuery({
    queryKey: ["admin-product-list"],
    queryFn: async () => await api.get<ProductInfo[]>("admin/product-list"),
  });

  if (isLoading) return <ComponentLoader />;

  if (!productList || !productList.data)
    return <Box>Unable to retrieve full product list.</Box>;

  return (
    <Box>
      <SalesSummary />
      <AddProductButton />
      <GenericTable
        tableData={productList.data}
        columnData={[
          {
            accessor: "pic",
            title: "",
            render: (record) => {
              const productInfo = record as ProductInfo;
              return (
                <Image
                  src={
                    productInfo.image === ""
                      ? "https://media.istockphoto.com/id/1055079680/vector/black-linear-photo-camera-like-no-image-available.jpg?s=612x612&w=0&k=20&c=P1DebpeMIAtXj_ZbVsKVvg-duuL0v9DlrOZUvPG6UJk="
                      : productInfo.image
                  }
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
              const productInfo = record as ProductInfo;
              return productInfo.name;
            },
          },
          {
            accessor: "category",
            sortable: true,
            render: (record) => {
              const productInfo = record as ProductInfo;
              return productInfo.category;
            },
          },
          {
            accessor: "unitPrice",
            sortable: true,
            width: 100,
            render: (record) => {
              const productInfo = record as ProductInfo;
              return `$${productInfo.unitPrice.toFixed(2)}`;
            },
          },
          {
            accessor: "stock",
            sortable: true,
            width: 100,
            render: (record) => {
              const productInfo = record as ProductInfo;
              return `${productInfo.stock}x left`;
            },
          },
          {
            accessor: "Actions",
            render: (record) => (
              <Group>
                <EditProductButton {...(record as ProductInfo)} />
                <DeleteProductButton
                  id={record.id as number}
                  name={record.name as string}
                />
              </Group>
            ),
          },
        ]}
      />
    </Box>
  );
}

export default ProductManagementPage;
