import ComponentLoader from "@/components/ComponentLoader/ComponentLoader";
import Product from "@/components/ProductList/Product";
import { Stack, Group, Input, SimpleGrid, Text } from "@mantine/core";
import { IconSearch } from "@tabler/icons-react";
import useSearch from "./useSearch";

function SearchPage() {
  const { isLoading, search, searchProductList, setSearch } = useSearch();
  if (isLoading) return <ComponentLoader />;
  if (
    !searchProductList ||
    !searchProductList.data ||
    searchProductList.data.length === 0
  )
    return (
      <Stack>
        <Group>
          <Input
            placeholder="Search products..."
            value={search}
            leftSection={<IconSearch size={16} />}
            w="100%"
            size="lg"
            onChange={(event) => setSearch(event.target.value)}
          />
        </Group>
        <Text>No results found.</Text>
      </Stack>
    );

  return (
    <Stack>
      <Group>
        <Input
          placeholder="Search products..."
          value={search}
          leftSection={<IconSearch size={16} />}
          w="100%"
          size="lg"
          onChange={(event) => setSearch(event.target.value)}
        />
      </Group>
      <SimpleGrid cols={4}>
        {searchProductList.data.map((product) => (
          <Product {...product} />
        ))}
      </SimpleGrid>
    </Stack>
  );
}

export default SearchPage;
