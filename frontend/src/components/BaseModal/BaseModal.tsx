import { Box, MantineSize, Modal } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import React, { cloneElement } from "react";

export type ModalWithChildren = {
  children?: React.ReactNode;
};

interface BaseModalClickTargetProps extends ModalWithChildren {
  open?: () => void;
  close?: () => void;
}
interface BaseModalContentProps extends ModalWithChildren {
  title: string;
  opened?: boolean;
  close?: () => void;
  size?: MantineSize | (string & {}) | number;
}

export const BaseModal = ({ ...props }: ModalWithChildren) => {
  const [opened, { open, close }] = useDisclosure(false);
  return (
    <>
      {React.Children.map(props.children, (child) =>
        cloneElement(child as any, { open, close, opened })
      )}
    </>
  );
};

BaseModal.ClickTarget = ({ ...props }: BaseModalClickTargetProps) => {
  return (
    <>
      {React.Children.map(props.children, (child) => (
        <Box style={{ cursor: "pointer" }} onClick={props.open}>
          {child}
        </Box>
      ))}
    </>
  );
};

BaseModal.Content = ({ ...props }: BaseModalContentProps) => {
  return (
    <>
      {
        <Modal
          opened={props.opened ? props.opened : false}
          onClose={props.close ? props.close : () => {}}
          title={props.title}
          size={props.size}
        >
          {props.children}
        </Modal>
      }
    </>
  );
};
