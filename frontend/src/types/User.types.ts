export type UserAccountDetails = {
  id: number;
  email: string;
  handPhoneNo: string;
  address: string;
  firstName: string;
  lastName: string;
  role: "customer" | "staff" | "";
};

export type UserRegistrationSchema = {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  handPhoneNo: string;
  address: string;
};
