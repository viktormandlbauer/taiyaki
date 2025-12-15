<template>
  <div class="container mt-4 mb-5">
    <div class="card admin-card">
      <div class="card-body">
        <div class="d-flex flex-wrap justify-content-between align-items-center mb-3">
          <h1 class="h4 mb-2">Product Management</h1>

          <input
            v-model="search"
            type="text"
            class="form-control form-control-sm"
            placeholder="Search products..."
          />
        </div>

        <!-- Add / edit product form (simple demo) -->
        <section class="mb-4">
          <h2 class="h6 mb-3">Add new product</h2>
          <form @submit.prevent="addProduct">
            <div class="row g-2">
              <div class="col-md-4">
                <input
                  v-model="newProduct.name"
                  type="text"
                  class="form-control form-control-sm"
                  placeholder="Product name"
                  required
                />
              </div>
              <div class="col-md-3">
                <input
                  v-model.number="newProduct.price"
                  type="number"
                  step="0.01"
                  min="0"
                  class="form-control form-control-sm"
                  placeholder="Price (€)"
                  required
                />
              </div>
              <div class="col-md-3">
                <select
                  v-model="newProduct.status"
                  class="form-select form-select-sm"
                  required
                >
                  <option value="active">Active</option>
                  <option value="hidden">Hidden</option>
                  <option value="archived">Archived</option>
                </select>
              </div>
              <div class="col-md-2 d-grid">
                <button type="submit" class="btn btn-sm btn-primary">
                  Add
                </button>
              </div>
            </div>
          </form>
        </section>

        <!-- Product list -->
        <div class="table-responsive">
          <table class="table align-middle mb-0">
            <thead>
            <tr>
              <th scope="col">Product</th>
              <th scope="col" class="text-center">Price</th>
              <th scope="col" class="text-center">Status</th>
              <th scope="col" class="text-end">Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="product in filteredProducts" :key="product.id">
              <td>
                <div class="fw-semibold">{{ product.name }}</div>
                <small class="text-muted">
                  ID: {{ product.id }}
                </small>
              </td>
              <td class="text-center">
                {{ formatPrice(product.price) }}
              </td>
              <td class="text-center">
                  <span
                    class="badge"
                    :class="statusClass(product.status)"
                  >
                    {{ product.status }}
                  </span>
              </td>
              <td class="text-end">
                <button
                  type="button"
                  class="btn btn-sm btn-outline-secondary me-1"
                  @click="editProduct(product)"
                >
                  Edit
                </button>
                <button
                  type="button"
                  class="btn btn-sm btn-outline-danger"
                  @click="deleteProduct(product)"
                >
                  Delete
                </button>
              </td>
            </tr>
            <tr v-if="filteredProducts.length === 0">
              <td colspan="4" class="text-center text-muted py-4">
                No products found.
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";

const search = ref("");

// Demo products data
const products = ref([
  {
    id: 1,
    name: "Cheesy Taiyaki",
    price: 9.99,
    status: "active",
  },
  {
    id: 2,
    name: "Classic Red Bean Taiyaki",
    price: 8.49,
    status: "active",
  },
  {
    id: 3,
    name: "Matcha Cream Taiyaki",
    price: 9.49,
    status: "hidden",
  },
  {
    id: 4,
    name: "Seasonal Strawberry Taiyaki",
    price: 10.99,
    status: "archived",
  },
]);

const newProduct = ref({
  name: "",
  price: null,
  status: "active",
});

const filteredProducts = computed(() => {
  return products.value.filter((p) =>
    !search.value
      ? true
      : p.name.toLowerCase().includes(search.value.toLowerCase())
  );
});

const formatPrice = (value) => `${value.toFixed(2)} €`;

const statusClass = (status) => {
  switch (status) {
    case "active":
      return "bg-success-subtle text-success";
    case "hidden":
      return "bg-warning-subtle text-warning";
    case "archived":
      return "bg-secondary-subtle text-secondary";
    default:
      return "bg-secondary-subtle text-secondary";
  }
};

const addProduct = () => {
  const id =
    products.value.length > 0
      ? Math.max(...products.value.map((p) => p.id)) + 1
      : 1;

  products.value.push({
    id,
    name: newProduct.value.name,
    price: newProduct.value.price || 0,
    status: newProduct.value.status || "active",
  });

  // reset form
  newProduct.value = {
    name: "",
    price: null,
    status: "active",
  };
};

const editProduct = (product) => {
  console.log("Edit product", product);
  alert(`Edit product "${product.name}" (demo).`);
};

const deleteProduct = (product) => {
  if (!confirm(`Delete product "${product.name}"?`)) return;
  products.value = products.value.filter((p) => p.id !== product.id);
};
</script>

<style scoped>
</style>
