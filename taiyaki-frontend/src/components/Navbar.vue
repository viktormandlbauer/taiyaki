<!-- src/components/Navbar.vue -->
<template>
  <nav
    class="navbar navbar-expand-lg navbar-dark taiyaki-navbar mb-4"
    :class="isAdmin ? 'taiyaki-navbar--admin' : 'taiyaki-navbar--default'"
  >
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
          <li v-for="item in leftNavItems" :key="item.to" class="nav-item">
            <RouterLink class="nav-link" :to="item.to">{{ item.label }}</RouterLink>
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
          <button
            type="button"
            class="btn p-0 border-0 bg-transparent nav-link"
            aria-label="Profile"
            @click="handleProfileClick"
          >
            <i class="bi bi-person fs-4"></i>
          </button>

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

  <AuthModal
    v-model:visible="showAuthModal"
    @auth-success="handleAuthSuccess"
  />
</template>

<script setup>
import { computed, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import AuthModal from "@/components/auth/AuthModal.vue";
import { useAuthStore } from "@/stores/auth";

const router = useRouter();
const auth = useAuthStore();

const showAuthModal = ref(false);

const isLoggedIn = computed(() => auth.isAuthed);
const isAdmin = computed(() => auth.isAdmin);

const leftNavItems = computed(() => {
  if (isAdmin.value) {
    return [
      { to: "/admin", label: "Dashboard" },
      { to: "/admin/orders", label: "Orders" },
      { to: "/admin/products", label: "Products" },
      { to: "/admin/users", label: "Users" },
    ];
  }

  return [
    { to: "/allergies", label: "Allergies" },
    { to: "/about", label: "About" },
    { to: "/contact", label: "Contact" },
  ];
});

const handleProfileClick = () => {
  if (isLoggedIn.value) router.push(isAdmin.value ? "/admin" : "/profile");
  else showAuthModal.value = true;
};

const handleAuthSuccess = () => {
  router.push(auth.isAdmin ? "/admin" : "/profile");
};
</script>

<style scoped>
.taiyaki-navbar--default { background-color: var(--taiyaki-primary); } /* rot */
.taiyaki-navbar--admin   { background-color: #0d6efd; } /* blau */
</style>
