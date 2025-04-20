import api from "@/utils/API";
import notify from "@/utils/NotificationSystem";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { useState } from "react";

type NewProductSchema = {
  name: string;
  unitPrice: string;
  stock: number;
  category: string;
  image: string;
};

export default function useAddProduct() {
  const queryClient = useQueryClient();
  const { mutateAsync: createProductMutation } = useMutation({
    mutationFn: async () =>
      await api.post<NewProductSchema, void>(
        "admin/product",
        {},
        productDetails
      ),
    onSuccess: (data) => {
      if (!data.data)
        notify.error(
          "Unable To Add Product!",
          "Something has gone wrong, the product was not added to the database."
        );
      else {
        notify.success(
          "New Product Added!",
          `Your new product has successfully been added!`
        );
        queryClient.invalidateQueries({
          queryKey: ["admin-product-list"],
        });
      }
    },
  });
  const [productDetails, setProductDetails] = useState<NewProductSchema>({
    name: "",
    unitPrice: "",
    stock: 0,
    category: "",
    image: "",
  });

  return { setProductDetails, productDetails, createProductMutation };
}
