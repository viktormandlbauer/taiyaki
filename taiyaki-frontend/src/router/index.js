import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../assets/pages/LoginView.vue';
import RegisterPage from '../assets/pages/RegisterView.vue';
import ProductsPage from '../assets/pages/ProductsView.vue';

const routes = [
  { path: '/', redirect: '/products' },
  { path: '/login', component: LoginPage },
  { path: '/register', component: RegisterPage },
  { path: '/products', component: ProductsPage }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
