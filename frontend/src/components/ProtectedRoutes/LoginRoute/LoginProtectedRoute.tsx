import { useIsAuth, useUser, useUserStore } from "@/stores/UserStore";
import api from "@/utils/API";
import { useMutation } from "@tanstack/react-query";
import { PropsWithChildren, useEffect } from "react";

type LoginProtectedRoutes = PropsWithChildren;

const LoginProtectedRoutes = ({ children }: LoginProtectedRoutes) => {
  const isAuthenticated = useIsAuth();
  const { removeUserState } = useUserStore();
  const { mutateAsync: logoutMutation } = useMutation({
    mutationFn: async () => await api.get("user/logout"),
  });
  const user = useUser();

  useEffect(() => {
    console.log("Auth check here");
    if (isAuthenticated || user) {
      logoutMutation();
      removeUserState();
    }
  }, []);

  return children;
};

export default LoginProtectedRoutes;
