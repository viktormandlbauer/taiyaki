import axios from "axios"
import { useAuthStore } from "@/stores/auth"

// Axios instance for API calls
export const http = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080",
  headers: { "Content-Type": "application/json" },
})

// Add auth token to authorization header
export function setupHttpInterceptors(pinia) {
  http.interceptors.request.use((config) => {
    const auth = useAuthStore(pinia)
    if (auth.token) {
      config.headers ??= {}
      config.headers.Authorization = `Bearer ${auth.token}`
    }
    return config
  })

  // Auto-logout on 401 (pass pinia so the store works outside components)
  http.interceptors.response.use(
    (r) => r,
    (err) => {
      if (err?.response?.status === 401) useAuthStore(pinia).logout()
      return Promise.reject(err)
    }
  )
}
