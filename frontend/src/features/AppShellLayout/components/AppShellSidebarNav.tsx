import { useState } from "react";
import { AppShell, Box, Text } from "@mantine/core";
import { IconLogout } from "@tabler/icons-react";
import classes from "./AppShellSidebarNav.module.css";

function AppShellSidebarNav() {
  const [active, setActive] = useState("Vegetables");
  const sampleDashboardCategories = [
    {
      label: "Shopping Cart",
      link: "/",
    },
    {
      label: "View order status",
      link: "/",
    },
    {
      label: "View past orders",
      link: "/",
    },
  ];
  const sampleCategories = [
    {
      label: "View all",
      link: "/",
    },
    {
      label: "Vegetables",
      link: "/",
    },
    {
      label: "Frozen Meats",
      link: "/",
    },
    {
      label: "Fruits",
      link: "/",
    },
    {
      label: "Cereal",
      link: "/",
    },
    {
      label: "Bread",
      link: "/",
    },
    {
      label: "Party Supplies",
      link: "/",
    },
    {
      label: "Baking Supplies",
      link: "/",
    },
  ];

  const dashboardCategories = sampleDashboardCategories.map((category) => (
    <a
      className={classes.link}
      data-active={category.label === active || undefined}
      href={category.link}
      key={category.label}
      onClick={(event) => {
        event.preventDefault();
        setActive(category.label);
      }}
    >
      <span>{category.label}</span>
    </a>
  ));
  const foodCategories = sampleCategories.map((category) => (
    <a
      className={classes.link}
      data-active={category.label === active || undefined}
      href={category.link}
      key={category.label}
      onClick={(event) => {
        event.preventDefault();
        setActive(category.label);
      }}
    >
      <span>{category.label}</span>
    </a>
  ));

  return (
    <AppShell.Navbar>
      <Box m="md" className={classes.navbarMain}>
        <Text py="md" fw={500} size="xs" color="dimmed">
          Dashboard
        </Text>
        {dashboardCategories}
        <Text py="md" fw={500} size="xs" color="dimmed">
          Categories
        </Text>
        {foodCategories}
      </Box>

      <Box m="md" className={classes.footer}>
        <a
          href="#"
          className={classes.link}
          onClick={(event) => event.preventDefault()}
        >
          <IconLogout className={classes.linkIcon} stroke={1.5} />
          <span>Logout</span>
        </a>
      </Box>
    </AppShell.Navbar>
  );
}

export default AppShellSidebarNav;
