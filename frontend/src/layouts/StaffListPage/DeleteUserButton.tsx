import { BaseModal } from "@/components/BaseModal/BaseModal";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { ActionIcon, Button, Code, Stack, Text } from "@mantine/core";
import { IconTrash } from "@tabler/icons-react";
import { useQueryClient, useMutation } from "@tanstack/react-query";

function DeleteUserButton({ id, name }: { id: number; name: string }) {
  const queryClient = useQueryClient();
  const { mutateAsync: deleteUserMutation } = useMutation({
    mutationFn: async () => await api.delete<void>(`account/${id}`),
    onSuccess: (data) => {
      if (!data.status || data.status >= 400)
        notify.error(
          "Unable To Delete User!",
          "Something has gone wrong, the user has not been deleted from the database."
        );
      else {
        notify.success(
          `${name} has been deleted!`,
          `${name} has successfully been deleted from the database!`
        );
        queryClient.invalidateQueries({
          queryKey: ["account-list"],
        });
      }
    },
  });
  return (
    <BaseModal>
      <BaseModal.ClickTarget>
        <ActionIcon variant="light" color="red">
          <IconTrash style={{ width: "70%", height: "70%" }} stroke={1.5} />
        </ActionIcon>
      </BaseModal.ClickTarget>
      <BaseModal.Content title={`Delete ${name} Confirmation`}>
        <Stack>
          <Text size="sm">Are you sure you want to delete:</Text>
          <Stack gap={0}>
            <Code>Account Name: {name}</Code>
            <Code>Account ID: {id}</Code>
          </Stack>
          <Button
            variant="light"
            color="red"
            fullWidth
            onClick={() => deleteUserMutation()}
          >
            Delete Account {id}
          </Button>
        </Stack>
      </BaseModal.Content>
    </BaseModal>
  );
}

export default DeleteUserButton;
