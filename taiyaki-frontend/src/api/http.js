import axios from "axios"
import { useAuthStore } from "@/stores/auth"

export const http = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080",
  headers: { "Content-Type": "application/json" },
})

export function setupHttpInterceptors(pinia) {
  http.interceptors.request.use((config) => {
    const auth = useAuthStore(pinia)
    if (auth.token) {
      config.headers = config.headers ?? {}
      config.headers.Authorization = `Bearer ${auth.token}`
    }
    return config
  })

  http.interceptors.response.use(
    (r) => r,
    (err) => {
      if (err?.response?.status === 401) {
        const auth = useAuthStore(pinia)
        auth.logout()
      }
      return Promise.reject(err)
    }
  )
}
