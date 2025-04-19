import { create } from "zustand";

export type User = {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  role: "customer" | "staff" | "";
};

interface UserState {
  user: User;
  setUserState: (user: User) => void;
}

export const useUserStore = create<UserState>()((set) => ({
  user: {
    firstName: "",
    lastName: "",
    id: 0,
    email: "",
    role: "",
  },
  setUserState: (user: User) => set(() => ({ user: user })),
}));
