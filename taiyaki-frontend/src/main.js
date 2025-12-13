import { createApp } from "vue"
import App from "./App.vue"
import router from "./router"
import { createPinia } from "pinia"

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import "bootstrap-icons/font/bootstrap-icons.css"
import "@/assets/styles/theme.css"

import { setupHttpInterceptors } from "@/api/http" // adjust path if needed

const app = createApp(App)

const pinia = createPinia()
app.use(pinia)

setupHttpInterceptors(pinia)

app.use(router).mount("#app")
