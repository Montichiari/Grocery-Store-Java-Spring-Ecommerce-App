import { UserRegistrationSchema, UserAccountDetails } from "@/types/User.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";

export default function useRegisterCustomer() {
  const queryClient = useQueryClient();
  const { mutateAsync: createCustomerMutation } = useMutation({
    mutationFn: async () =>
      await api.post<UserRegistrationSchema, UserAccountDetails>(
        "user/register/customer",
        {},
        customerDetails
      ),
    onSuccess: (data) => {
      if (!data.data)
        notify.error(
          "Unable to Add New Customer",
          "There seems to be an issue with the inputs you've provided."
        );
      else {
        notify.success(
          "Registration Successful!",
          `Your account has been created! Please login through the login form.`
        );
        setCustomerDetails({
          email: "",
          address: "",
          firstName: "",
          handPhoneNo: "",
          lastName: "",
          password: "",
        });
        queryClient.invalidateQueries({
          queryKey: ["account-list"],
        });
      }
    },
  });
  const [customerDetails, setCustomerDetails] =
    useState<UserRegistrationSchema>({
      email: "",
      address: "",
      firstName: "",
      lastName: "",
      handPhoneNo: "",
      password: "",
    });

  return { customerDetails, setCustomerDetails, createCustomerMutation };
}
