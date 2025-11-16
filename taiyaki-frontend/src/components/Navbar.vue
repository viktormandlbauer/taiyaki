<!-- src/components/TaiyakiNavbar.vue -->
<template>
  <nav class="navbar navbar-expand-lg navbar-dark taiyaki-navbar mb-4">
    <div class="container">
      <RouterLink class="navbar-brand" to="/">Taiyaki</RouterLink>

      <!-- Toggler for mobile -->
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#mainNavbar"
        aria-controls="mainNavbar"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse show" id="mainNavbar">
        <!-- Left: nav links -->
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <RouterLink class="nav-link" to="/allergies">Allergies</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/about">About</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/contact">Contact</RouterLink>
          </li>
        </ul>

        <!-- Middle: search bar -->
        <form class="d-flex me-3" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search..."
            aria-label="Search"
          />
          <button class="btn btn-outline-light" type="submit">
            <i class="bi bi-search"></i>
          </button>
        </form>

        <!-- Right: profile + cart icons -->
        <div class="d-flex align-items-center gap-3">
          <!-- Profile -->
          <RouterLink
            class="nav-link p-0"
            to="/profile"
            aria-label="Profile"
          >
            <i class="bi bi-person fs-4"></i>
          </RouterLink>

          <!-- Cart (with badge) -->
          <RouterLink
            class="nav-link position-relative p-0"
            to="/cart"
            aria-label="Shopping cart"
          >
            <i class="bi bi-cart fs-4"></i>
            <span
              class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
            >
              0
              <span class="visually-hidden">items in cart</span>
            </span>
          </RouterLink>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { RouterLink } from "vue-router";
</script>

<style scoped>
.taiyaki-navbar {
  background-color: #A7333F;
}
</style>
