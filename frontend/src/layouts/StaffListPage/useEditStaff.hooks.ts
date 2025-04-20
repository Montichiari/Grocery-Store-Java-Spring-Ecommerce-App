import { UserRegistrationSchema, UserAccountDetails } from "@/types/User.types";
import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { useState } from "react";

export default function useEditStaff(accountDetails: UserAccountDetails) {
  const queryClient = useQueryClient();
  const { mutateAsync: editUserMutation } = useMutation({
    mutationFn: async () =>
      await api.put<UserRegistrationSchema, UserAccountDetails>(
        `account/${accountDetails.id}`,
        {},
        userDetails
      ),
    onSuccess: (data) => {
      if (!data.data)
        notify.error(
          "Unable to Edit User Details",
          "There seems to be an issue with the inputs you've provided."
        );
      else {
        notify.success(
          "Your user has been edited!",
          `Your user details have been successfully modified!`
        );
        queryClient.invalidateQueries({
          queryKey: ["account-list"],
        });
      }
    },
  });
  const [userDetails, setUserDetails] = useState<UserRegistrationSchema>({
    email: accountDetails.email,
    address: accountDetails.address,
    firstName: accountDetails.firstName,
    lastName: accountDetails.lastName,
    handPhoneNo: accountDetails.handPhoneNo,
    password: "",
  });

  return { userDetails, setUserDetails, editUserMutation };
}
