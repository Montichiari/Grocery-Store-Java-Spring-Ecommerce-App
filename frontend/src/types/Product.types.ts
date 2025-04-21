export type ProductInfo = {
  id: number;
  name: string;
  unitPrice: number;
  stock: number;
  category: string;
  image: string;
};

export type ProductCategories =
  | "Beverages"
  | "Household"
  | "Meat and Poultry"
  | "Prepared Meals"
  | "Fruits"
  | "Vegetables"
  | "Seafood"
  | "Snacks and Pantry";
