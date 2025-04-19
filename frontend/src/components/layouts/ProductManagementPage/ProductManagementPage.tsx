import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import GenericTable from "@/components/GenericTable/GenericTable";
import { ProductInfo } from "@/types/Product.types";
import { UserAccountDetails } from "@/types/User.types";
import api from "@/utils/API";
import { ActionIcon, Box, Group, Image } from "@mantine/core";
import { IconEdit, IconTrash } from "@tabler/icons-react";
import { useQuery } from "@tanstack/react-query";
import { useState, useEffect } from "react";

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
  }, [data]);

  if (isLoading) return <ComponentLoader />;
  if (!productList || productList.length === 0)
    return <Box>Unable to retrieve full product list.</Box>;

  return (
    <Box>
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
            render: () => (
              <Group>
                <ActionIcon variant="light" color="orange">
                  <IconEdit
                    style={{ width: "70%", height: "70%" }}
                    stroke={1.5}
                  />
                </ActionIcon>
                <ActionIcon variant="light" color="red">
                  <IconTrash
                    style={{ width: "70%", height: "70%" }}
                    stroke={1.5}
                  />
                </ActionIcon>
              </Group>
            ),
          },
        ]}
      />
    </Box>
  );
}

export default ProductManagementPage;
