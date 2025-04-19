import { ActionIcon } from "@mantine/core";
import { IconTrash } from "@tabler/icons-react";

function DeleteProductButton({ id }: { id: number }) {
  return (
    <ActionIcon variant="light" color="red">
      <IconTrash style={{ width: "70%", height: "70%" }} stroke={1.5} />
    </ActionIcon>
  );
}

export default DeleteProductButton;
