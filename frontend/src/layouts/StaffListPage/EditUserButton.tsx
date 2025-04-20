import { BaseModal } from "@/components/BaseModal/BaseModal";
import { ActionIcon, Button, Input, Stack } from "@mantine/core";
import { IconEdit } from "@tabler/icons-react";
import useEditStaff from "./useEditStaff.hooks";
import { UserAccountDetails } from "@/types/User.types";

function EditUserButton({ ...props }: UserAccountDetails) {
  const { editUserMutation, setUserDetails, userDetails } = useEditStaff({
    ...props,
  });
  return (
    <BaseModal>
      <BaseModal.ClickTarget>
        <ActionIcon variant="light" color="orange">
          <IconEdit style={{ width: "70%", height: "70%" }} stroke={1.5} />
        </ActionIcon>
      </BaseModal.ClickTarget>
      <BaseModal.Content title={`Editing ${props.firstName}'s account details`}>
        <Stack>
          <form onSubmit={(e) => e.preventDefault()}>
            <Input.Wrapper
              label="First Name"
              description="Enter your user's first name"
            >
              <Input
                placeholder="E.g. Jessica"
                value={userDetails.firstName}
                onChange={(event) =>
                  setUserDetails({
                    ...userDetails,
                    firstName: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper
              label="Last Name"
              description="Enter your user's last name"
            >
              <Input
                placeholder="E.g. Tan"
                value={userDetails.lastName}
                onChange={(event) =>
                  setUserDetails({
                    ...userDetails,
                    lastName: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper label="Email" description="Enter user's email">
              <Input
                placeholder="E.g. new_staff@gmail.com"
                value={userDetails.email}
                onChange={(event) =>
                  setUserDetails({
                    ...userDetails,
                    email: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper
              label="Password"
              description="Enter your user's password"
            >
              <Input
                type="password"
                value={userDetails.password}
                placeholder="E.g. newStaffPassword123!"
                onChange={(event) =>
                  setUserDetails({
                    ...userDetails,
                    password: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper
              label="Handphone No."
              description="Enter your user's handphone number"
            >
              <Input
                placeholder="E.g. 97521726"
                value={userDetails.handPhoneNo}
                onChange={(event) =>
                  setUserDetails({
                    ...userDetails,
                    handPhoneNo: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper
              label="address"
              description="Enter your user's address"
            >
              <Input
                placeholder="85 Jalan Jalan Merah Singapore 487521"
                value={userDetails.address}
                onChange={(event) =>
                  setUserDetails({
                    ...userDetails,
                    address: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Button
              my="md"
              type="submit"
              variant="light"
              color="orange"
              fullWidth
              onClick={() => editUserMutation()}
            >
              Modify user's details
            </Button>
          </form>
        </Stack>
      </BaseModal.Content>
    </BaseModal>
  );
}

export default EditUserButton;
