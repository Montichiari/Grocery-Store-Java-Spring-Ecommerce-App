import App from "@/app/App";
import AdminPage from "@/components/layouts/AdminPage/AdminPage";
import HomePage from "@/components/layouts/HomePage/HomePage";
import ListingPage from "@/components/layouts/ShopPage/ShopPage";
import LoginPage from "@/components/layouts/LoginPage/LoginPage";
import ShoppingCartPage from "@/components/layouts/ShoppingCartPage/ShoppingCartPage";
import PastOrdersPage from "@/components/layouts/PastOrdersPage/PastOrdersPage";
import OrderStatusPage from "@/components/layouts/OrderStatusPage/OrderStatusPage";
import ProductList from "@/features/ProductList/components/ProductList";

export const routes = [
  {
    title: "Welcome",
    path: "/",
    element: <App />,
    children: [],
  },
  {
    title: "",
    path: "/home",
    element: <HomePage />,
  },
  {
    title: "Login",
    path: "/login",
    element: <LoginPage />,
    children: [],
  },
  {
    title: "",
    path: "/shop",
    element: <ListingPage />,
    children: [
      {
        title: "Product List",
        path: "products",
        element: <ProductList />,
        defaultPage: true,
      },
      {
        title: "Shopping Cart",
        path: "cart",
        element: <ShoppingCartPage />,
      },
      {
        title: "Old Orders",
        path: "old-orders",
        element: <PastOrdersPage />,
      },
      {
        title: "Order Status",
        path: "order-status",
        element: <OrderStatusPage />,
      },
    ],
  },
  {
    path: "/admin-panel",
    element: <AdminPage />,
    children: [],
  },
];
