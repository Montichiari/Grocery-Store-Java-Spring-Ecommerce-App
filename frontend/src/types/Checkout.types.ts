import { OrderItemInfo } from "./Order.types";

export type CheckoutDetails = {
  id: number;
  createAt: Date;
  fulfilmentDate: Date;
  status: string;
  paymentMethod: AllowedPaymentMethods;
  orderItems: OrderItemInfo;
};

export type AllowedPaymentMethods = "Credit Card" | "Paypal" | "Bank Transfer";
