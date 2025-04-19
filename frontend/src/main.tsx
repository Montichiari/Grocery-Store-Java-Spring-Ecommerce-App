import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { MantineProvider } from "@mantine/core";
import { BrowserRouter, Route, Routes } from "react-router";
import { adminRoutes, mainRoutes, shopRoutes } from "@/routes/Routes.tsx";
import "./index.css";
import "@mantine/core/styles.css";
import "mantine-datatable/styles.layer.css";
import "@mantine/dates/styles.css";
import "@mantine/code-highlight/styles.css";
import "@mantine/notifications/styles.css";
import { Notifications } from "@mantine/notifications";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <MantineProvider>
          <Notifications />
          <Routes>
            {[...adminRoutes, ...mainRoutes, ...shopRoutes].map((route) => {
              if (route.children)
                return (
                  <Route path={route.path} element={route.element}>
                    {route.children.map((childRoute) => (
                      <Route
                        index={childRoute.defaultPage}
                        path={childRoute.path}
                        element={childRoute.element}
                      />
                    ))}
                  </Route>
                );
              else return <Route path={route.path} element={route.element} />;
            })}
          </Routes>
        </MantineProvider>
      </BrowserRouter>
    </QueryClientProvider>
  </StrictMode>
);
