import { AppShell, Box, Text } from "@mantine/core";
import { IconLogout } from "@tabler/icons-react";
import classes from "./AppShellSidebarNav.module.css";
import { Link } from "react-router";
import { useState } from "react";

function AppShellSidebarNav() {
  const [active, setActive] = useState("Vegetables");
  const sampleDashboardCategories = [
    {
      label: "Shopping Cart",
      link: "/shop/cart",
    },
    {
      label: "View order status",
      link: "/shop/order-status",
    },
    {
      label: "View past orders",
      link: "/shop/old-orders",
    },
  ];
  const sampleCategories = [
    {
      label: "View all",
      link: "/shop/products",
    },
    {
      label: "Vegetables",
      link: "/shop/products",
    },
    {
      label: "Frozen Meats",
      link: "/shop/products",
    },
    {
      label: "Fruits",
      link: "/shop/products",
    },
    {
      label: "Cereal",
      link: "/shop/products",
    },
    {
      label: "Bread",
      link: "/shop/products",
    },
    {
      label: "Party Supplies",
      link: "/shop/products",
    },
    {
      label: "Baking Supplies",
      link: "/shop/products",
    },
  ];

  const dashboardCategories = sampleDashboardCategories.map((category) => (
    <Link
      to={category.link}
      className={classes.link}
      data-active={category.label === active || undefined}
      key={category.label}
      onClick={() => setActive(category.label)}
    >
      <span>{category.label}</span>
    </Link>
  ));
  const foodCategories = sampleCategories.map((category) => (
    <Link
      to={category.link}
      className={classes.link}
      data-active={category.label === active || undefined}
      key={category.label}
      onClick={() => setActive(category.label)}
    >
      <span>{category.label}</span>
    </Link>
  ));

  return (
    <AppShell.Navbar>
      <Box m="md" className={classes.navbarMain}>
        <Text py="md" fw={500} size="xs" color="dimmed">
          Dashboard
        </Text>
        {dashboardCategories}
        <Text py="md" fw={500} size="xs" color="dimmed">
          Product Categories
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
