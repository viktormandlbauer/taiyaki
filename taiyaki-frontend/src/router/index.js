import { createMemoryHistory, createRouter } from 'vue-router'

import HomeView from '../assets/pages/HomeView.vue'

const LoginView = () => import('../assets/pages/LoginView.vue');

const routes = [
  { path: '/', component: HomeView },
  { path: '/login', component: LoginView },
]

export const router = createRouter({
  history: createMemoryHistory(),
  routes,
})
