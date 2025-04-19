import Logo from "@/assets/Logo";
import {
  Paper,
  TextInput,
  PasswordInput,
  Button,
  Anchor,
  Text,
  Container,
} from "@mantine/core";
import useLogin from "./useLogin.hooks";

function LoginPage() {
  const { loginInfo, loginMutation, setLoginInfo } = useLogin();
  return (
    <Container maw="480px" my={40}>
      <Text ta="center">
        <Logo size={62} />
      </Text>
      <Text c="dimmed" size="sm" ta="center" mt={5}>
        Do not have an account yet?{" "}
        <Anchor size="sm" component="button">
          Create account
        </Anchor>
      </Text>

      <Paper withBorder shadow="md" p={30} mt={30} radius="md">
        <form
          onSubmit={(event) => {
            event.preventDefault();
            loginMutation();
          }}
        >
          <TextInput
            label="Email"
            placeholder="you@mantine.dev"
            required
            onChange={(event) =>
              setLoginInfo({ ...loginInfo, email: event.currentTarget.value })
            }
          />
          <PasswordInput
            label="Password"
            placeholder="Your password"
            required
            mt="md"
            onChange={(event) =>
              setLoginInfo({
                ...loginInfo,
                password: event.currentTarget.value,
              })
            }
          />
          <Button fullWidth mt="xl" type="submit">
            Sign in
          </Button>
        </form>
      </Paper>
    </Container>
  );
}

export default LoginPage;
