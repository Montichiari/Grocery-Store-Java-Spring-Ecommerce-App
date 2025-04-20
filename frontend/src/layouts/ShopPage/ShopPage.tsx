import AppShellContent from "@/components/AppShellLayout/AppShellContent";
import AppShellHeader from "@/components/AppShellLayout/AppShellHeader";
import AppShellSidebarNav from "@/components/AppShellLayout/AppShellSidebarNav";
import { NavbarLink } from "@/types/AppShellSidebarNav.types";
import { AppShell } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";

function ShopPage() {
  const [opened, { toggle }] = useDisclosure();
  const dashboardCategories: NavbarLink[] = [
    {
      label: "Shopping Cart",
      link: "/shop/cart",
    },
    {
      label: "View Orders",
      link: "/shop/orders",
    },
    {
      label: "Search Products",
      link: "/shop/search",
    },
  ];
  const productCategories: NavbarLink[] = [
    {
      label: "View all",
      link: "/shop/products/all",
    },
    {
      label: "Beverages",
      link: "/shop/products/beverages",
    },
    {
      label: "Household",
      link: "/shop/products/household",
    },
    {
      label: "Meat and Poultry",
      link: "/shop/products/meat-and-poultry",
    },
    {
      label: "Prepared Meals",
      link: "/shop/products/prepared-meals",
    },
    {
      label: "Fruits",
      link: "/shop/products/fruits",
    },
    {
      label: "Vegetables",
      link: "/shop/products/vegetables",
    },
    {
      label: "Seafood",
      link: "/shop/products/seafood",
    },
    {
      label: "Snacks and Pantry",
      link: "/shop/products/snacks-and-pantry",
    },
  ];
  return (
    <AppShell
      header={{ height: 59 }}
      navbar={{ width: 299, breakpoint: "sm", collapsed: { mobile: !opened } }}
    >
      <AppShellHeader opened={opened} toggle={toggle} />
      <AppShellSidebarNav
        defaultSection="View all"
        navSection={[
          {
            sectionHeader: "Dashboard",
            links: [...dashboardCategories],
          },
          {
            sectionHeader: "Product Categories",
            links: [...productCategories],
          },
        ]}
      />
      <AppShellContent />
    </AppShell>
  );
}

export default ShopPage;
