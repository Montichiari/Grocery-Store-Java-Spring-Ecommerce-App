import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import GenericTable from "@/components/GenericTable/GenericTable";
import { ProductInfo } from "@/types/Product.types";
import api from "@/utils/API";
import { Box, Group, Image } from "@mantine/core";
import { useQuery } from "@tanstack/react-query";
import { useState, useEffect } from "react";
import AddProductButton from "./AddProductButton";
import EditProductButton from "./EditProductButton";
import DeleteProductButton from "./DeleteProductButton";

function ProductManagementPage() {
  const { data, isLoading } = useQuery({
    queryKey: ["admin-product-list"],
    queryFn: async () => await api.get<ProductInfo[]>("admin/product-list"),
  });
  const [productList, setProductList] = useState<ProductInfo[]>(
    data?.data ? data.data : []
  );
  useEffect(() => {
    if (data?.data) setProductList(data.data);
    console.log("triggered");
  }, [data, productList]);

  if (isLoading) return <ComponentLoader />;
  if (!productList || productList.length === 0)
    return <Box>Unable to retrieve full product list.</Box>;

  return (
    <Box>
      <AddProductButton />
      <GenericTable
        tableData={productList}
        columnData={[
          {
            accessor: "pic",
            title: "",
            render: (record) => {
              const productInfo = record as ProductInfo;
              return (
                <Image
                  src={productInfo.image}
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
            accessor: "Actions",
            render: (record) => (
              <Group>
                <EditProductButton {...(record as ProductInfo)} />
                <DeleteProductButton id={record.id as number} />
              </Group>
            ),
          },
        ]}
      />
    </Box>
  );
}

export default ProductManagementPage;
