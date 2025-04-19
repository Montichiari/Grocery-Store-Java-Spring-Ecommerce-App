import { ProductInfo } from "./Product.types";

export type OrderItemInfo = {
  id: number;
  quantity: number;
  unitPrice: number;
  product: ProductInfo;
};
export type OrderInfo = {
  id: number;
  createAt: Date;
  fulfilmentDate: Date;
  status: string;
  paymentMethod: string;
  totalAmount: number;
  orderItems: OrderItemInfo[];
};
