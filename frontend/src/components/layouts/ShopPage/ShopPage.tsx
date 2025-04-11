import AppShellContent from "@/features/AppShellLayout/components/AppShellContent";
import AppShellHeader from "@/features/AppShellLayout/components/AppShellHeader";
import AppShellSidebarNav from "@/features/AppShellLayout/components/AppShellSidebarNav";
import { AppShell } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";

function ShopPage() {
  const [opened, { toggle }] = useDisclosure();
  return (
    <AppShell
      header={{ height: 59 }}
      navbar={{ width: 299, breakpoint: "sm", collapsed: { mobile: !opened } }}
    >
      <AppShellHeader opened={opened} toggle={toggle} />
      <AppShellSidebarNav />
      <AppShellContent />
    </AppShell>
  );
}

export default ShopPage;
