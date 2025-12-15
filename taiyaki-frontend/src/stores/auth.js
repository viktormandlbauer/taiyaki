import { defineStore } from "pinia"
import * as authApi from "@/api/auth" // <-- adjust path to your auth.js
import { decodeJwtPayload } from "@/api/jwt"

export const useAuthStore = defineStore("auth", {
  state: () => ({
    token: localStorage.getItem("token") || null,
    user: JSON.parse(localStorage.getItem("user") || "null"),
  }),

  getters: {
    isAuthed: (s) => !!s.token,
    isAdmin: (s) => {
      if (!s.token) return false

      const payload = decodeJwtPayload(s.token) || {}
      const role = payload.role || payload.roles || payload.authorities
      const asString = Array.isArray(role) ? role.join(" ") : String(role || "")
      return asString.includes("ROLE_ADMIN") || asString.includes("ADMIN")
    },
  },

  actions: {
    async login(identifier, password) {
      const res = await authApi.login(identifier, password)

      const token = res.data?.token || res.data?.accessToken || res.data?.jwt
      if (!token) throw new Error("Login response did not include a token")

      this.token = token
      localStorage.setItem("token", token)

      // optional user info (wenn Backend das nicht liefert, bleibt user null)
      if (res.data?.user) {
        this.user = res.data.user
        localStorage.setItem("user", JSON.stringify(res.data.user))
      } else {
        this.user = null
        localStorage.removeItem("user")
      }

      return res
    },

    async register(payload) {
      return authApi.register(payload)
    },

    logout() {
      this.token = null
      this.user = null
      localStorage.removeItem("token")
      localStorage.removeItem("user")
    },
  },
})
