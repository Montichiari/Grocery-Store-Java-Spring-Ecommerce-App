import ProductList from "@/features/ProductList/ProductList";
import { AppShell } from "@mantine/core";
function AppShellContent() {
  return <AppShell.Main mih="100%">{<ProductList />}</AppShell.Main>;
}

export default AppShellContent;
