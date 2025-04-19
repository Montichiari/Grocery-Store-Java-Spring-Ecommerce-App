import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import GenericTable from "@/components/GenericTable/GenericTable";
import { UserAccountDetails } from "@/types/User.types";
import api from "@/utils/API";
import { ActionIcon, Box, Group } from "@mantine/core";
import { IconEdit, IconTrash } from "@tabler/icons-react";
import { useQuery } from "@tanstack/react-query";
import { useEffect, useState } from "react";

function StaffListPage() {
  const { data, isLoading } = useQuery({
    queryKey: ["account-list"],
    queryFn: async () => await api.get<UserAccountDetails[]>("account/all"),
  });
  const [accountList, setAccountList] = useState<UserAccountDetails[]>(
    data?.data ? data.data : []
  );
  useEffect(() => {
    if (data?.data) setAccountList(data.data);
  }, [data]);

  if (isLoading) return <ComponentLoader />;
  if (!accountList || accountList.length === 0)
    return <Box>Unable to retrieve full account list.</Box>;

  return (
    <Box>
      <GenericTable
        tableData={accountList}
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

export default StaffListPage;
