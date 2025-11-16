<template>
  <div class="container mt-4 mb-5">
    <div class="card admin-card">
      <div class="card-body">
        <!-- Header -->
        <div class="d-flex flex-wrap justify-content-between align-items-center mb-3">
          <div>
            <h1 class="h4 mb-1">Admin Dashboard</h1>
            <p class="text-muted mb-0 small">
              Overview of today’s taiyaki business.
            </p>
          </div>

          <div class="d-flex flex-wrap gap-2 mt-2 mt-md-0">
            <RouterLink
              to="/admin/users"
              class="btn btn-sm btn-outline-primary"
            >
              Manage users
            </RouterLink>
            <RouterLink
              to="/admin/products"
              class="btn btn-sm btn-outline-primary"
            >
              Manage products
            </RouterLink>
            <RouterLink
              to="/admin/orders"
              class="btn btn-sm btn-outline-primary"
            >
              Manage orders
            </RouterLink>
          </div>
        </div>

        <!-- Top stats -->
        <div class="row g-3 mb-4">
          <div class="col-12 col-md-4">
            <div class="stat-card p-3 h-100">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <div class="text-muted small">Orders today</div>
                  <div class="fs-4 fw-semibold">{{ ordersToday }}</div>
                </div>
                <span class="stat-icon">📦</span>
              </div>
            </div>
          </div>

          <div class="col-12 col-md-4">
            <div class="stat-card p-3 h-100">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <div class="text-muted small">Revenue today</div>
                  <div class="fs-4 fw-semibold">{{ formatPrice(revenueToday) }}</div>
                </div>
                <span class="stat-icon">💶</span>
              </div>
            </div>
          </div>

          <div class="col-12 col-md-4">
            <div class="stat-card p-3 h-100">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <div class="text-muted small">Active customers</div>
                  <div class="fs-4 fw-semibold">{{ activeCustomers }}</div>
                </div>
                <span class="stat-icon">👤</span>
              </div>
            </div>
          </div>
        </div>

        <div class="row g-4">
          <!-- Recent orders -->
          <div class="col-12 col-lg-8">
            <section>
              <div class="d-flex justify-content-between align-items-center mb-2">
                <h2 class="h5 mb-0">Recent orders</h2>
                <span class="text-muted small">
                  Last {{ recentOrders.length }} orders
                </span>
              </div>

              <div class="table-responsive">
                <table class="table align-middle mb-0">
                  <thead>
                  <tr>
                    <th scope="col">Order</th>
                    <th scope="col">Customer</th>
                    <th scope="col" class="text-center">Items</th>
                    <th scope="col" class="text-center">Date &amp; time</th>
                    <th scope="col" class="text-center">Address</th>
                    <th scope="col" class="text-end">Total</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr
                    v-for="order in recentOrders"
                    :key="order.id"
                  >
                    <td>#{{ order.id }}</td>
                    <td>{{ order.customer }}</td>
                    <td class="text-center">{{ order.items }}</td>
                    <td class="text-center">
                      {{ formatDateTime(order.datetime) }}
                    </td>
                    <td class="text-center">
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-primary"
                        @click="viewAddress(order)"
                      >
                        View address
                      </button>
                    </td>
                    <td class="text-end">
                      {{ formatPrice(order.total) }}
                    </td>
                  </tr>
                  <tr v-if="recentOrders.length === 0">
                    <td colspan="6" class="text-center text-muted py-4">
                      No orders yet.
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </section>
          </div>

          <!-- Quick actions -->
          <div class="col-12 col-lg-4">
            <section>
              <h2 class="h5 mb-2">Quick actions</h2>
              <div class="d-grid gap-2">
                <RouterLink
                  to="/admin/products"
                  class="btn btn-primary btn-sm"
                >
                  ➕ Add new product
                </RouterLink>
                <RouterLink
                  to="/admin/users"
                  class="btn btn-outline-primary btn-sm"
                >
                  👥 View all users
                </RouterLink>
                <RouterLink
                  to="/admin/orders"
                  class="btn btn-outline-primary btn-sm"
                >
                  📦 View all orders
                </RouterLink>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";
import { RouterLink } from "vue-router";

// Dummy data – replace with real API / store data later
const orders = ref([
  {
    id: 1012,
    customer: "Tania K.",
    items: 3,
    total: 42.5,
    today: true,
    datetime: "2025-11-16T10:15:00",
    address: {
      name: "Tania Kaffashbashi",
      street: "Sakura Street 18",
      zip: "10243",
      city: "Berlin",
      country: "Germany",
    },
  },
  {
    id: 1011,
    customer: "Danai A.",
    items: 2,
    total: 29.9,
    today: true,
    datetime: "2025-11-16T09:47:00",
    address: {
      name: "Danai Akrivou",
      street: "Taiyaki Alley 5",
      zip: "10115",
      city: "Berlin",
      country: "Germany",
    },
  },
  {
    id: 1010,
    customer: "Viktor M.",
    items: 1,
    total: 15.0,
    today: false,
    datetime: "2025-11-15T18:32:00",
    address: {
      name: "Viktor Mandlbauer",
      street: "Kreuzbergstraße 12",
      zip: "10965",
      city: "Berlin",
      country: "Germany",
    },
  },
  {
    id: 1009,
    customer: "Test User",
    items: 4,
    total: 55.2,
    today: false,
    datetime: "2025-11-15T14:03:00",
    address: {
      name: "Test Customer",
      street: "Example Road 1",
      zip: "10000",
      city: "Berlin",
      country: "Germany",
    },
  },
]);

const customers = ref([
  { id: 1, name: "Tania", active: true },
  { id: 2, name: "Danai", active: true },
  { id: 3, name: "Viktor", active: true },
  { id: 4, name: "Test Customer", active: false },
]);

const ordersToday = computed(() =>
  orders.value.filter((o) => o.today).length
);

const revenueToday = computed(() =>
  orders.value
    .filter((o) => o.today)
    .reduce((sum, o) => sum + o.total, 0)
);

const activeCustomers = computed(() =>
  customers.value.filter((c) => c.active).length
);

const recentOrders = computed(() =>
  orders.value.slice(0, 4) // in a real app you'd sort by datetime desc
);

const formatPrice = (value) => `${value.toFixed(2)} €`;

const formatDateTime = (value) => {
  if (!value) return "";
  const d = new Date(value);
  return d.toLocaleString("de-DE", {
    dateStyle: "short",
    timeStyle: "short",
  });
};

const viewAddress = (order) => {
  const a = order.address || {};
  const lines = [
    a.name,
    a.street,
    `${a.zip} ${a.city}`,
    a.country,
  ].filter(Boolean);
  alert(`Shipping address for order #${order.id}:\n\n${lines.join("\n")}`);
};
</script>

<style scoped>
.stat-icon {
  font-size: 1.8rem;
}
</style>
