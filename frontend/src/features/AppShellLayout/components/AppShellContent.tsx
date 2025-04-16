import { AppShell, Title, useMantineTheme } from "@mantine/core";
import { Outlet, useLocation } from "react-router";
import { routes } from "@/routes/Routes";
function AppShellContent() {
  const location = useLocation();
  const theme = useMantineTheme();

  return (
    <AppShell.Main mih="100%">
      <Title c={theme.colors.gray[7]} style={{ textTransform: "capitalize" }}>
        {location.pathname.split("/")[2].replace("-", " ")}
      </Title>
      {<Outlet />}
    </AppShell.Main>
  );
}

export default AppShellContent;
