import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../assets/pages/LoginView.vue";
import RegisterView from "../assets/pages/RegisterView.vue";
import ProductsView from "../assets/pages/ProductsView.vue";

const routes = [
  {
    path: "/",
    redirect: "/login", // default route
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
  },
  {
    path: "/products",
    name: "products",
    component: ProductsView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
