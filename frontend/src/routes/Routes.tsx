import App from "@/app/App";
import AdminPage from "@/components/layouts/AdminPage/AdminPage";
import HomePage from "@/components/layouts/HomePage/HomePage";
import ListingPage from "@/components/layouts/ShopPage/ShopPage";
import LoginPage from "@/components/layouts/LoginPage/LoginPage";

export const routes = [
  {
    path: "/",
    element: <App />,
    children: [],
  },
  {
    path: "/home",
    element: <HomePage />,
    children: [
      {
        path: "/category/*",
      },
    ],
  },
  {
    path: "/login",
    element: <LoginPage />,
    children: [],
  },
  {
    path: "/listings",
    element: <ListingPage />,
    children: [],
  },
  {
    path: "/admin-panel",
    element: <AdminPage />,
    children: [],
  },
];
