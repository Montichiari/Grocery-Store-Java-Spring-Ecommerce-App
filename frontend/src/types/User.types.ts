export type UserAccountDetails = {
  id: number;
  email: string;
  handPhoneNo: string;
  address: string;
  firstName: string;
  lastName: string;
  role: UserRoles;
};

export type UserRegistrationSchema = {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  handPhoneNo: string;
  address: string;
};

export type UserRoles = "customer" | "staff" | "";
