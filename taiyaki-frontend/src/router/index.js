import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/assets/pages/HomeView.vue'),
  },

  {
    path: '/products',
    name: 'products',
    component: () => import('@/assets/pages/shopping/ProductsView.vue'),
  },
  {
    path: '/allergies',
    name: 'allergies',
    component: () => import('@/assets/pages/info/AllergiesView.vue'),
  },
  {
    path: '/contact',
    name: 'contact',
    component: () => import('@/assets/pages/info/ContactView.vue'),
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('@/assets/pages/info/AboutView.vue'),
  },
  {
    path: '/imprint',
    name: 'imprint',
    component: () => import('@/assets/pages/info/ImprintView.vue'),
  },
  {
    path: '/help',
    name: 'help',
    component: () => import('@/assets/pages/info/HelpView.vue'),
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('@/assets/pages/user/ProfileView.vue'),
  },
  {
    path: '/cart',
    name: 'cart',
    component: () => import('@/assets/pages/shopping/CartView.vue'),
  },
  {
    path: '/checkout',
    name: 'checkout',
    component: () => import('@/assets/pages/shopping/CheckoutView.vue'),
  },
  {
    path: '/admin',
    name: 'admin-dashboard',
    component: () => import('@/assets/pages/admin/AdminDashboardView.vue'),
  },
  {
    path: '/admin/users',
    name: 'admin-users',
    component: () => import('@/assets/pages/admin/UserManagementView.vue'),
  },
  {
    path: '/admin/products',
    name: 'admin-products',
    component: () => import('@/assets/pages/admin/ProductManagementView.vue'),
  },
  {
    path: '/admin/orders',
    name: 'admin-orders',
    component: () => import('@/assets/pages/admin/OrderManagementView.vue'),
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
