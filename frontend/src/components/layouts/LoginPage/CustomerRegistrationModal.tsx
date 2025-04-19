import { BaseModal } from "@/components/BaseModal/BaseModal";
import { Anchor, Button, Input, Stack } from "@mantine/core";
import useRegisterCustomer from "./useRegisterCustomer.hooks";

function CustomerRegistrationModal() {
  const { createCustomerMutation, customerDetails, setCustomerDetails } =
    useRegisterCustomer();
  return (
    <BaseModal>
      <BaseModal.ClickTarget>
        <Anchor size="sm" component="button">
          Create account
        </Anchor>
      </BaseModal.ClickTarget>
      <BaseModal.Content title="Account Registration">
        <Stack>
          <form onSubmit={(e) => e.preventDefault()}>
            <Input.Wrapper
              label="First Name"
              description="Enter your first name"
            >
              <Input
                placeholder="E.g. Jessica"
                value={customerDetails.firstName}
                onChange={(event) =>
                  setCustomerDetails({
                    ...customerDetails,
                    firstName: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper label="Last Name" description="Enter your last name">
              <Input
                placeholder="E.g. Tan"
                value={customerDetails.lastName}
                onChange={(event) =>
                  setCustomerDetails({
                    ...customerDetails,
                    lastName: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper label="Email" description="Enter your email">
              <Input
                placeholder="E.g. harold@gmail.com"
                value={customerDetails.email}
                onChange={(event) =>
                  setCustomerDetails({
                    ...customerDetails,
                    email: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper label="Password" description="Enter your password">
              <Input
                type="password"
                value={customerDetails.password}
                placeholder="E.g. thisIsMyPassword123!"
                onChange={(event) =>
                  setCustomerDetails({
                    ...customerDetails,
                    password: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper
              label="Handphone No."
              description="Enter your handphone number"
            >
              <Input
                placeholder="E.g. 97521726"
                value={customerDetails.handPhoneNo}
                onChange={(event) =>
                  setCustomerDetails({
                    ...customerDetails,
                    handPhoneNo: event.target.value,
                  })
                }
              />
            </Input.Wrapper>
            <Input.Wrapper label="address" description="Enter your address">
              <Input
                placeholder="85 Jalan Jalan Merah Singapore 487521"
                value={customerDetails.address}
                onChange={(event) =>
                  setCustomerDetails({
                    ...customerDetails,
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
              onClick={() => createCustomerMutation()}
            >
              Create new account
            </Button>
          </form>
        </Stack>
      </BaseModal.Content>
    </BaseModal>
  );
}

export default CustomerRegistrationModal;
