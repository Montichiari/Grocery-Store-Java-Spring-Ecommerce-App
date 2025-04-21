import { AppShell, Box, Text } from "@mantine/core";
import { IconLogout } from "@tabler/icons-react";
import classes from "./AppShellSidebarNav.module.css";
import { Link } from "react-router";
import { useState } from "react";
import { NavbarLink } from "@/types/AppShellSidebarNav.types";
import { useMutation } from "@tanstack/react-query";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";

type AppShellSidebarNavProps = {
  navSection: {
    sectionHeader: string;
    links: NavbarLink[];
  }[];
  defaultSection?: string;
};

function AppShellSidebarNav({ ...props }: AppShellSidebarNavProps) {
  const { mutateAsync: logoutMutation } = useMutation({
    mutationFn: async () => await api.get("user/logout"),
    onSuccess: () =>
      notify.success(
        "Successfully Logged Out!",
        "You have been successfully logged out of your account."
      ),
  });
  const [active, setActive] = useState(props.defaultSection);
  const navbarCategories = props.navSection.map((category) => (
    <>
      <Text py="md" fw={500} size="xs" color="dimmed">
        {category.sectionHeader}
      </Text>
      {category.links.map((link, index) => (
        <Link
          to={link.link}
          className={classes.link}
          data-active={link.label === active || undefined}
          key={index}
          onClick={() => setActive(link.label)}
        >
          <span>{link.label}</span>
        </Link>
      ))}
    </>
  ));

  return (
    <AppShell.Navbar>
      <Box m="md" className={classes.navbarMain}>
        {navbarCategories}
      </Box>

      <Box m="md" className={classes.footer}>
        <Link to="/" className={classes.link} onClick={() => logoutMutation()}>
          <IconLogout className={classes.linkIcon} stroke={1.5} />
          <span>Logout</span>
        </Link>
      </Box>
    </AppShell.Navbar>
  );
}

export default AppShellSidebarNav;
