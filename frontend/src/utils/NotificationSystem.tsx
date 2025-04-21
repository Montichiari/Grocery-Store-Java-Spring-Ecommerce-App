import { notifications } from "@mantine/notifications";
import { IconCheck, IconExclamationCircleFilled } from "@tabler/icons-react";

const notify = {
  success(
    title: string = "Success!",
    message: string = "Your request has been successfully fulfilled!"
  ): void {
    notifications.show({
      position: "top-right",
      withBorder: true,
      color: "green",
      autoClose: 5000,
      icon: <IconCheck />,
      title: title,
      message: message,
    });
  },
  error(
    title: string = "Uh oh!",
    message: string = "Something has gone terribly wrong!"
  ): void {
    notifications.show({
      position: "top-right",
      withBorder: true,
      color: "red",
      autoClose: 5000,
      icon: <IconExclamationCircleFilled />,
      title: title,
      message: message,
    });
  },
};

export default notify;
