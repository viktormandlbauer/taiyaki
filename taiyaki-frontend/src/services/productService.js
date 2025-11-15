const MOCK_PRODUCTS = [
  {
    id: 1,
    name: 'Classic Red Bean Taiyaki',
    description: 'Crispy outside, sweet azuki bean inside.',
    price: 2.5
  },
  {
    id: 2,
    name: 'Matcha Cream Taiyaki',
    description: 'Filled with smooth matcha custard.',
    price: 3.0
  },
  {
    id: 3,
    name: 'Chocolate Banana Taiyaki',
    description: 'Rich chocolate with banana chunks.',
    price: 3.2
  }
];

export async function getProducts() {
  // Later: replace with real API call (axios.get...)
  return MOCK_PRODUCTS;
}
