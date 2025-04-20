import GenericTable from "@/components/GenericTable/GenericTable";
import dateFormat from "@/utils/DateUtil";
import usePastOrders from "./usePastOrders.hooks";
import { Box } from "@mantine/core";
import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import PastOrderModal from "./PastOrderModal";
import { OrderInfo } from "@/types/Order.types";

function PastOrdersPage() {
  const { orderList, isLoading } = usePastOrders();
  if (isLoading) return <ComponentLoader />;
  if (!orderList || orderList.length === 0)
    return <Box>No past orders found.</Box>;

  return (
    <Box mb="xl">
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
