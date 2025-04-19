import { UserRegistrationSchema, UserAccountDetails } from "@/types/User.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { useState } from "react";

export default function useAddStaff() {
  const queryClient = useQueryClient();
  const { mutateAsync: createStaffMutation } = useMutation({
    mutationFn: async () =>
      await api.post<UserRegistrationSchema, UserAccountDetails>(
        "account/register/staff",
        {},
        staffDetails
      ),
    onSuccess: (data) => {
      if (!data.data)
        notify.error(
          "Unable to Add New Staff",
          "There seems to be an issue with the inputs you've provided."
        );
      else {
        notify.success(
          "New Staff Added!",
          `Your new staff has successfully been added!`
        );
        setStaffDetails({
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
  const [staffDetails, setStaffDetails] = useState<UserRegistrationSchema>({
    email: "",
    address: "",
    firstName: "",
    lastName: "",
    handPhoneNo: "",
    password: "",
  });

  return { staffDetails, setStaffDetails, createStaffMutation };
}
