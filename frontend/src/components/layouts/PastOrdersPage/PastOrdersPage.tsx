import GenericTable from "@/components/GenericTable/GenericTable";
import dateFormat from "@/utils/DateUtil";
import usePastOrders from "./usePastOrders.hooks";
import { Box, Button, Image, Stack, Text } from "@mantine/core";
import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import { BaseModal } from "@/components/BaseModal/BaseModal";
import { ProductInfo } from "@/types/Product.types";
import { ShoppingCartItem } from "@/types/ShoppingCart.types";
import PastOrderModal from "./PastOrderModal";
import { OrderInfo } from "@/types/Order.types";

function PastOrdersPage() {
  const { orderList, isLoading } = usePastOrders();
  if (isLoading) return <ComponentLoader />;
  if (!orderList || orderList.length === 0)
    return <Box>No past orders found.</Box>;

  return (
    <Box>
      <GenericTable
        tableData={orderList}
        columnData={[
          { accessor: "id", sortable: true },
          {
            accessor: "createAt",
            title: "Order Date",
            sortable: true,
            render: (record) => (
              <>{dateFormat.formatter(record.createAt as string)}</>
            ),
          },
          {
            accessor: "fulfilmentDate",
            sortable: true,
            render: (record) => (
              <>{dateFormat.formatter(record.fulfilmentDate as string)}</>
            ),
          },
          { accessor: "status", sortable: true },
          { accessor: "paymentMethod", sortable: true },
          {
            accessor: "viewOrderList",
            render: (record) => <PastOrderModal {...(record as OrderInfo)} />,
          },
        ]}
      />
    </Box>
  );
}

export default PastOrdersPage;
