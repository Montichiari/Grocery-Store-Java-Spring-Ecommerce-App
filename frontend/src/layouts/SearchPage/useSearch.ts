import { ProductInfo } from "@/types/Product.types";
import api from "@/utils/API";
import { useDebouncedValue } from "@mantine/hooks";
import { useQuery } from "@tanstack/react-query";
import { useState } from "react";

export default function useSearch() {
  const [search, setSearch] = useState("");
  const [debounced] = useDebouncedValue(search, 500);
  const { data: searchProductList, isLoading } = useQuery({
    queryKey: ["search-products", debounced],
    queryFn: async () =>
      await api.get<ProductInfo[]>("product/search", { input: debounced }),
  });

  return { isLoading, searchProductList, search, setSearch };
}
