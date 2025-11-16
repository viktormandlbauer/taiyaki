<template>
  <div class="container mt-4 mb-5">
    <div class="card cart-card">
      <div class="card-body">
        <h1 class="h3 mb-4">Your Cart</h1>

        <!-- Empty state -->
        <div v-if="items.length === 0" class="text-muted">
          Your cart is empty. Go treat yourself to some taiyaki! 🐟
        </div>

        <!-- Cart items -->
        <div v-else>
          <div class="table-responsive mb-3">
            <table class="table align-middle mb-0">
              <thead>
              <tr>
                <th scope="col">Product</th>
                <th scope="col">Variant</th>
                <th scope="col" class="text-center">Quantity</th>
                <th scope="col" class="text-end">Price</th>
                <th scope="col" class="text-end">Subtotal</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in items" :key="item.id">
                <td>{{ item.name }}</td>
                <td>{{ item.variant }}</td>
                <td class="text-center">{{ item.quantity }}</td>
                <td class="text-end">{{ formatPrice(item.price) }}</td>
                <td class="text-end">
                  {{ formatPrice(item.price * item.quantity) }}
                </td>
              </tr>
              </tbody>
            </table>
          </div>

          <!-- Total + action -->
          <div class="d-flex justify-content-end mt-4">
            <div class="text-end">
              <div class="fw-semibold fs-5">
                Total: {{ formatPrice(total) }}
              </div>
              <div class="text-muted small">
                Taxes and shipping are calculated at checkout.
              </div>

              <!-- Link to CheckoutView -->
              <RouterLink
                to="/checkout"
                class="btn btn-primary btn-lg mt-3"
              >
                Proceed to checkout
              </RouterLink>
            </div>
          </div>


        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";

// Example cart data (replace later with store/API)
const items = ref([
  {
    id: 1,
    name: "Cheesy Taiyaki",
    variant: "3 pcs",
    quantity: 1,
    price: 9.99,
  },
  {
    id: 2,
    name: "Classic Red Bean Taiyaki",
    variant: "9 pcs",
    quantity: 2,
    price: 18.5,
  },
]);

const total = computed(() =>
  items.value.reduce((sum, item) => sum + item.price * item.quantity, 0)
);

const formatPrice = (value) => `${value.toFixed(2)} €`;
</script>

<style scoped>
.cart-card {
  background-color: rgba(255, 255, 255, 0.95);
  border-radius: 1rem;
  box-shadow: 0 0.75rem 2rem rgba(0, 0, 0, 0.25);
}

/* Optional: slightly nicer table look */
.table thead th {
  border-bottom-width: 2px;
}
</style>
