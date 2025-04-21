import { BaseModal } from "@/components/BaseModal/BaseModal";
import { Box, Button, Input, Stack } from "@mantine/core";
import useAddStaff from "./useAddStaff.hooks";

function AddStaffButton() {
  const { createStaffMutation, setStaffDetails, staffDetails } = useAddStaff();
  return (
    <Box>
      <BaseModal>
        <BaseModal.ClickTarget>
          <Button variant="light" color="blue">
            Add new staff member
          </Button>
        </BaseModal.ClickTarget>
        <BaseModal.Content title="Add new staff member">
          <Stack>
            <form onSubmit={(e) => e.preventDefault()}>
              <Input.Wrapper
                label="First Name"
                description="Enter your staff's first name"
              >
                <Input
                  placeholder="E.g. Jessica"
                  value={staffDetails.firstName}
                  onChange={(event) =>
                    setStaffDetails({
                      ...staffDetails,
                      firstName: event.target.value,
                    })
                  }
                />
              </Input.Wrapper>
              <Input.Wrapper
                label="Last Name"
                description="Enter your staff's last name"
              >
                <Input
                  placeholder="E.g. Tan"
                  value={staffDetails.lastName}
                  onChange={(event) =>
                    setStaffDetails({
                      ...staffDetails,
                      lastName: event.target.value,
                    })
                  }
                />
              </Input.Wrapper>
              <Input.Wrapper label="Email" description="Enter staff email">
                <Input
                  placeholder="E.g. new_staff@gmail.com"
                  value={staffDetails.email}
                  onChange={(event) =>
                    setStaffDetails({
                      ...staffDetails,
                      email: event.target.value,
                    })
                  }
                />
              </Input.Wrapper>
              <Input.Wrapper
                label="Password"
                description="Enter your staff's password"
              >
                <Input
                  type="password"
                  value={staffDetails.password}
                  placeholder="E.g. newStaffPassword123!"
                  onChange={(event) =>
                    setStaffDetails({
                      ...staffDetails,
                      password: event.target.value,
                    })
                  }
                />
              </Input.Wrapper>
              <Input.Wrapper
                label="Handphone No."
                description="Enter your staff's handphone number"
              >
                <Input
                  placeholder="E.g. 97521726"
                  value={staffDetails.handPhoneNo}
                  onChange={(event) =>
                    setStaffDetails({
                      ...staffDetails,
                      handPhoneNo: event.target.value,
                    })
                  }
                />
              </Input.Wrapper>
              <Input.Wrapper
                label="address"
                description="Enter your staff's address"
              >
                <Input
                  placeholder="85 Jalan Jalan Merah Singapore 487521"
                  value={staffDetails.address}
                  onChange={(event) =>
                    setStaffDetails({
                      ...staffDetails,
                      address: event.target.value,
                    })
                  }
                />
              </Input.Wrapper>
              <Button
                my="md"
                type="submit"
                variant="light"
                color="blue"
                fullWidth
                onClick={() => createStaffMutation()}
              >
                Create new staff
              </Button>
            </form>
          </Stack>
        </BaseModal.Content>
      </BaseModal>
    </Box>
  );
}

export default AddStaffButton;
