<template>
  <div class="container mt-4 mb-5">
    <div class="card admin-card">
      <div class="card-body">
        <!-- Header -->
        <div class="d-flex flex-wrap justify-content-between align-items-center mb-3">
          <h1 class="h4 mb-2">Order Management</h1>

          <div class="d-flex flex-wrap gap-2">
            <input
              v-model="search"
              type="text"
              class="form-control form-control-sm"
              placeholder="Search by order ID or customer..."
            />
            <select
              v-model="statusFilter"
              class="form-select form-select-sm"
            >
              <option value="">All statuses</option>
              <option value="processing">Processing</option>
              <option value="shipped">Shipped</option>
              <option value="delivered">Delivered</option>
              <option value="cancelled">Cancelled</option>
            </select>
          </div>
        </div>

        <!-- Orders table -->
        <div class="table-responsive">
          <table class="table align-middle mb-0">
            <thead>
            <tr>
              <th scope="col">Order</th>
              <th scope="col">Customer</th>
              <th scope="col" class="text-center">Items</th>
              <th scope="col" class="text-center">Date &amp; time</th>
              <th scope="col" class="text-center">Address</th>
              <th scope="col" class="text-center">Status</th>
              <th scope="col" class="text-end">Total</th>
              <th scope="col" class="text-end">Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr
              v-for="order in filteredOrders"
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
              <td class="text-center">
                  <span
                    class="badge"
                    :class="statusBadgeClass(order.status)"
                  >
                    {{ order.status }}
                  </span>
              </td>
              <td class="text-end">
                {{ formatPrice(order.total) }}
              </td>
              <td class="text-end">
                <div class="d-flex justify-content-end gap-1">
                  <select
                    v-model="order.status"
                    class="form-select form-select-sm w-auto"
                  >
                    <option value="processing">Processing</option>
                    <option value="shipped">Shipped</option>
                    <option value="delivered">Delivered</option>
                    <option value="cancelled">Cancelled</option>
                  </select>
                  <button
                    type="button"
                    class="btn btn-sm btn-primary"
                    @click="saveStatus(order)"
                  >
                    Save
                  </button>
                </div>
              </td>
            </tr>

            <tr v-if="filteredOrders.length === 0">
              <td colspan="8" class="text-center text-muted py-4">
                No orders found.
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

// Filters
const search = ref("");
const statusFilter = ref("");

// Demo orders – replace with API/store data later
const orders = ref([
  {
    id: 1012,
    customer: "Tania K.",
    items: 3,
    total: 42.5,
    status: "shipped",
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
    status: "processing",
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
    status: "delivered",
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
    status: "cancelled",
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

const filteredOrders = computed(() => {
  return orders.value.filter((o) => {
    const matchSearch =
      !search.value ||
      o.customer.toLowerCase().includes(search.value.toLowerCase()) ||
      String(o.id).includes(search.value);

    const matchStatus =
      !statusFilter.value || o.status === statusFilter.value;

    return matchSearch && matchStatus;
  });
});

const formatPrice = (value) => `${value.toFixed(2)} €`;

const formatDateTime = (value) => {
  if (!value) return "";
  const d = new Date(value);
  return d.toLocaleString("de-DE", {
    dateStyle: "short",
    timeStyle: "short",
  });
};

const statusBadgeClass = (status) => {
  switch (status) {
    case "processing":
      return "bg-warning-subtle text-warning";
    case "shipped":
      return "bg-info-subtle text-info";
    case "delivered":
      return "bg-success-subtle text-success";
    case "cancelled":
      return "bg-secondary-subtle text-secondary";
    default:
      return "bg-secondary-subtle text-secondary";
  }
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

const saveStatus = (order) => {
  console.log("Saving status for order", order.id, "→", order.status);
  alert(`Order #${order.id} status saved as "${order.status}" (demo).`);
};
</script>

<style scoped>
</style>
