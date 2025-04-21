import App from "@/app/App";
import AdminPage from "@/layouts/AdminPage/AdminPage";
import HomePage from "@/layouts/HomePage/HomePage";
import ListingPage from "@/layouts/ShopPage/ShopPage";
import LoginPage from "@/layouts/LoginPage/LoginPage";
import ShoppingCartPage from "@/layouts/ShoppingCartPage/ShoppingCartPage";
import PastOrdersPage from "@/layouts/PastOrdersPage/PastOrdersPage";
import ProductList from "@/components/ProductList/ProductList";
import StaffListPage from "@/layouts/StaffListPage/StaffListPage";
import ProductManagementPage from "@/layouts/ProductManagementPage/ProductManagementPage";
import { JSX } from "react";
import ShoppingCartConfirmation from "@/layouts/ShoppingCartPage/ShoppingCartConfirmation";
import CustomerProtectedRoute from "@/components/ProtectedRoutes/CustomerRoute/CustomerProtectedRoute";
import StaffProtectedRoutes from "@/components/ProtectedRoutes/StaffRoute/StaffProtectedRoute";
import SearchPage from "@/layouts/SearchPage/SearchPage";
import LoginProtectedRoutes from "@/components/ProtectedRoutes/LoginRoute/LoginProtectedRoute";

type RouteInfo = {
  title: string;
  path: string;
  element: JSX.Element;
  children?: RouteInfo[];
  defaultPage?: boolean;
};

export const mainRoutes: RouteInfo[] = [
  {
    title: "Welcome",
    path: "/",
    element: <App />,
  },
  {
    title: "",
    path: "/home",
    element: <HomePage />,
  },
  {
    title: "Login",
    path: "/login",
    element: (
      <LoginProtectedRoutes>
        <LoginPage />
      </LoginProtectedRoutes>
    ),
  },
];

export const adminRoutes: RouteInfo[] = [
  {
    title: "",
    path: "/admin-panel",
    element: (
      <StaffProtectedRoutes>
        <AdminPage />
      </StaffProtectedRoutes>
    ),
    children: [
      {
        title: "Staff Management",
        path: "staff-management",
        element: <StaffListPage />,
      },
      {
        title: "Product Management",
        path: "product-management",
        element: <ProductManagementPage />,
      },
    ],
  },
];

export const shopRoutes: RouteInfo[] = [
  {
    title: "",
    path: "/shop",
    element: (
      <CustomerProtectedRoute>
        <ListingPage />
      </CustomerProtectedRoute>
    ),
    children: [
      {
        title: "Product List",
        path: "products/:category",
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
        path: "orders",
        element: <PastOrdersPage />,
      },
      {
        title: "Order Confirmation",
        path: "confirmation",
        element: <ShoppingCartConfirmation />,
      },
      {
        title: "Search Products",
        path: "search",
        element: <SearchPage />,
      },
    ],
  },
];
