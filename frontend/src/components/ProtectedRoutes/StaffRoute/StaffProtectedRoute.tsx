import { useIsAuth, useUser, useUserStore } from "@/stores/UserStore";
import { UserRoles } from "@/types/User.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useMutation } from "@tanstack/react-query";
import { PropsWithChildren, useEffect } from "react";
import { useNavigate } from "react-router";

type StaffProtectedRoutes = PropsWithChildren;

const StaffProtectedRoutes = ({ children }: StaffProtectedRoutes) => {
  const allowedRoles: UserRoles[] = ["staff"];
  const navigate = useNavigate();
  const isAuthenticated = useIsAuth();
  const { removeUserState } = useUserStore();
  const { mutateAsync: logoutMutation } = useMutation({
    mutationFn: async () => await api.get("user/logout"),
    onSuccess: () =>
      notify.error(
        "Logged out",
        "You have been forcefully signed out due to attempting to access a restricted route"
      ),
  });
  const user = useUser();

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login");
      return;
    }
    if (!user || !user.role || !allowedRoles.includes(user.role)) {
      logoutMutation();
      removeUserState();
      navigate("/login");
      return;
    }
  }, [isAuthenticated, user, navigate]);

  return isAuthenticated && user?.role && allowedRoles.includes(user.role)
    ? children
    : null;
};

export default StaffProtectedRoutes;
