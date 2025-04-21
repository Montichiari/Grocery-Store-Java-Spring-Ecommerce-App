import { CheckoutDetails } from "@/types/Checkout.types";
import { create } from "zustand";

interface CheckoutState {
  checkoutDetails: CheckoutDetails | undefined;
  setCheckoutState: (checkoutDetails: CheckoutDetails) => void;
}

export const useCheckoutStore = create<CheckoutState>()((set) => ({
  checkoutDetails: undefined,
  setCheckoutState: (checkoutDetails: CheckoutDetails) =>
    set(() => ({ checkoutDetails: checkoutDetails })),
  clearCheckoutState: () => set(() => ({ checkoutDetails: undefined })),
}));
