import GenericTable from "@/components/GenericTable/GenericTable";
import dateFormat from "@/utils/DateUtil";
import usePastOrders from "./usePastOrders.hooks";
import { Box } from "@mantine/core";
import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";

function PastOrdersPage() {
  const { orderList, isLoading } = usePastOrders();
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
        ]}
      />
    </Box>
  );
}

export default PastOrdersPage;
