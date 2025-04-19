import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import GenericTable from "@/components/GenericTable/GenericTable";
import { UserAccountDetails } from "@/types/User.types";
import api from "@/utils/API";
import { ActionIcon, Box, Group } from "@mantine/core";
import { IconEdit, IconTrash } from "@tabler/icons-react";
import { useQuery } from "@tanstack/react-query";
import AddStaffButton from "./AddStaffButton";
import EditUserButton from "./EditUserButton";
import DeleteUserButton from "./DeleteUserButton";

function StaffListPage() {
  const { data: accountList, isLoading } = useQuery({
    queryKey: ["account-list"],
    queryFn: async () => await api.get<UserAccountDetails[]>("account/all"),
  });

  if (isLoading) return <ComponentLoader />;
  if (!accountList || !accountList.data)
    return <Box>Unable to retrieve full account list.</Box>;

  return (
    <Box>
      <AddStaffButton />
      <GenericTable
        tableData={accountList.data}
        columnData={[
          {
            accessor: "id",
            sortable: true,
          },
          {
            accessor: "firstName",
            sortable: true,
          },
          {
            accessor: "lastName",
            sortable: true,
          },
          {
            accessor: "role",
            sortable: true,
          },
          {
            accessor: "address",
            sortable: true,
          },
          {
            accessor: "email",
            sortable: true,
          },
          {
            accessor: "handPhoneNo",
            sortable: true,
          },
          {
            accessor: "Actions",
            render: (record) => (
              <Group>
                <EditUserButton {...(record as UserAccountDetails)} />
                <DeleteUserButton
                  id={record.id as number}
                  name={record.firstName as string}
                />
              </Group>
            ),
          },
        ]}
      />
    </Box>
  );
}

export default StaffListPage;
