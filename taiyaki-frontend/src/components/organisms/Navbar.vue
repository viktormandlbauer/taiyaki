<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container-fluid">
      <RouterLink class="navbar-brand" to="/">Taiyaki Shop</RouterLink>

      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbar"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div id="navbar" class="collapse navbar-collapse">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <RouterLink class="nav-link" to="/products">Products</RouterLink>
          </li>
        </ul>

        <ul class="navbar-nav">
          <li v-if="!isAuthenticated" class="nav-item">
            <RouterLink class="nav-link" to="/login">Login</RouterLink>
          </li>
          <li v-if="!isAuthenticated" class="nav-item">
            <RouterLink class="nav-link" to="/register">Register</RouterLink>
          </li>
          <li v-else class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
            >
              {{ user?.email }}
            </a>
            <ul class="dropdown-menu dropdown-menu-end">
              <li>
                <button class="dropdown-item" @click="logout">
                  Logout
                </button>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { computed } from 'vue';
import { useAuth } from '../../services/authService';
import { RouterLink } from 'vue-router';

const auth = useAuth();
const isAuthenticated = computed(() => auth.isAuthenticated.value);
const user = computed(() => auth.user.value);

function logout() {
  auth.logout();
}
</script>
