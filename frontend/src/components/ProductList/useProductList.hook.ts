import { ProductInfo } from "@/types/Product.types";
import api from "@/utils/API";
import { useQuery } from "@tanstack/react-query";
import { useState, useEffect } from "react";
import { useParams } from "react-router";

export default function useProductList() {
  const { category } = useParams();
  const [productList, setProductList] = useState<ProductInfo[]>([]);
  const { data, isLoading } = useQuery({
    queryKey: ["all-products"],
    queryFn: async () => await api.get<ProductInfo[]>("product/all"),
  });

  useEffect(() => {
    if (!data?.data) return;
    if (category === "all") setProductList(data.data);
    else
      setProductList(
        data.data.filter(
          (product) =>
            product.category.toLowerCase() ===
            category?.replace(/-/g, " ").toLowerCase()
        )
      );
  }, [category, data]);

  return { isLoading, productList };
}
