import { useUserStore } from "@/stores/UserStore";
import { UserAccountDetails } from "@/types/User.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useMutation } from "@tanstack/react-query";
import { useState } from "react";
import { useNavigate } from "react-router";

export default function useLogin() {
  let navigate = useNavigate();
  const { setUserState } = useUserStore();
  const [loginInfo, setLoginInfo] = useState({
    email: "",
    password: "",
  });
  const { mutateAsync: loginMutation } = useMutation({
    mutationFn: async () =>
      await api.post<{ email: string; password: string }, UserAccountDetails>(
        "user/login",
        {},
        loginInfo
      ),
    onSuccess: (data) => {
      // setUserState(userData)
      if (data.error && data.error?.status >= 400) {
        notify.error("An error has occurred", "Invalid email or password.");
      }

      if (data.status === 200) {
        const userData = data.data as UserAccountDetails;
        setUserState(userData, true);

        if (data.data?.role === "customer") navigate("/shop/products/all");
        else navigate("/admin-panel/staff-management");

        notify.success(
          "Login Successful",
          `Welcome back to GetFreshFood, ${userData.firstName}!`
        );
      }
    },
  });

  return {
    loginMutation,
    loginInfo,
    setLoginInfo,
  };
}
