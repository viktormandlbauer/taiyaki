<template>
  <div class="container mt-4 mb-5">
    <div class="card admin-card">
      <div class="card-body">
        <div class="d-flex flex-wrap justify-content-between align-items-center mb-3">
          <h1 class="h4 mb-2">User Management</h1>

          <div class="d-flex gap-2">
            <input
              v-model="search"
              type="text"
              class="form-control form-control-sm"
              placeholder="Search users..."
            />
            <select
              v-model="roleFilter"
              class="form-select form-select-sm"
            >
              <option value="">All roles</option>
              <option value="admin">Admin</option>
              <option value="customer">Customer</option>
            </select>
          </div>
        </div>

        <div class="table-responsive">
          <table class="table align-middle mb-0">
            <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Email</th>
              <th scope="col" class="text-center">Role</th>
              <th scope="col" class="text-center">Status</th>
              <th scope="col" class="text-end">Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="user in filteredUsers" :key="user.id">
              <td>{{ user.name }}</td>
              <td>{{ user.email }}</td>
              <td class="text-center">
                  <span
                    class="badge"
                    :class="user.role === 'admin' ? 'bg-danger-subtle text-danger' : 'bg-secondary-subtle text-secondary'"
                  >
                    {{ user.role === 'admin' ? 'Admin' : 'Customer' }}
                  </span>
              </td>
              <td class="text-center">
                  <span
                    class="badge"
                    :class="user.active ? 'bg-success-subtle text-success' : 'bg-warning-subtle text-warning'"
                  >
                    {{ user.active ? 'Active' : 'Blocked' }}
                  </span>
              </td>
              <td class="text-end">
                <button
                  type="button"
                  class="btn btn-sm btn-outline-secondary me-1"
                  @click="editUser(user)"
                >
                  Edit
                </button>
                <button
                  type="button"
                  class="btn btn-sm"
                  :class="user.active ? 'btn-outline-warning' : 'btn-outline-success'"
                  @click="toggleActive(user)"
                >
                  {{ user.active ? 'Block' : 'Unblock' }}
                </button>
              </td>
            </tr>
            <tr v-if="filteredUsers.length === 0">
              <td colspan="5" class="text-center text-muted py-4">
                No users found.
              </td>
            </tr>
            </tbody>
          </table>
        </div>

        <p class="small text-muted mt-3 mb-0">
          This is a demo admin view. Hook these actions into your backend or store logic later.
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";

const search = ref("");
const roleFilter = ref("");

// Demo user data – replace with real API / store later
const users = ref([
  {
    id: 1,
    name: "Tania Kaffashbashi",
    email: "tania@example.com",
    role: "admin",
    active: true,
  },
  {
    id: 2,
    name: "Danai Akrivou",
    email: "danai@example.com",
    role: "admin",
    active: true,
  },
  {
    id: 3,
    name: "Viktor Mandlbauer",
    email: "viktor@example.com",
    role: "customer",
    active: true,
  },
  {
    id: 4,
    name: "Test Customer",
    email: "customer@example.com",
    role: "customer",
    active: false,
  },
]);

const filteredUsers = computed(() => {
  return users.value.filter((u) => {
    const matchesSearch =
      !search.value ||
      u.name.toLowerCase().includes(search.value.toLowerCase()) ||
      u.email.toLowerCase().includes(search.value.toLowerCase());

    const matchesRole =
      !roleFilter.value || u.role === roleFilter.value;

    return matchesSearch && matchesRole;
  });
});

const editUser = (user) => {
  console.log("Edit user", user);
  alert(`Edit user "${user.name}" (demo).`);
};

const toggleActive = (user) => {
  user.active = !user.active;
};
</script>

<style scoped>
</style>
