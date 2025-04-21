import { BaseModal } from "@/components/BaseModal/BaseModal";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { ActionIcon, Button, Code, Stack, Text } from "@mantine/core";
import { IconTrash } from "@tabler/icons-react";
import { useQueryClient, useMutation } from "@tanstack/react-query";

// Not used, as deleting products are not allowed in this app
function DeleteProductButton({ id, name }: { id: number; name: string }) {
  const queryClient = useQueryClient();
  const { mutateAsync: deleteProductMutation } = useMutation({
    mutationFn: async () => await api.delete<void>(`admin/product/${id}`),
    onSuccess: (data) => {
      if (!data.status || data.status >= 400)
        notify.error(
          "Unable To Delete Product!",
          "Something has gone wrong, the product was not deleted from the database."
        );
      else {
        notify.success(
          "Product Deleted!",
          `Your product has successfully been deleted!`
        );
        queryClient.invalidateQueries({
          queryKey: ["admin-product-list"],
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
      <BaseModal.Content title={`Delete Product ${id} Confirmation`}>
        <Stack>
          <Text size="sm">Are you sure you want to delete:</Text>
          <Stack gap={0}>
            <Code>Product Name: {name}</Code>
            <Code>Product ID: {id}</Code>
          </Stack>
          <Button
            variant="light"
            color="red"
            fullWidth
            onClick={() => deleteProductMutation()}
          >
            Delete Product {id}
          </Button>
        </Stack>
      </BaseModal.Content>
    </BaseModal>
  );
}

export default DeleteProductButton;
