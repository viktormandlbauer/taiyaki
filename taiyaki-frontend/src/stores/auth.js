// src/stores/auth.js
import { defineStore } from "pinia"
import * as authApi from "@/api/auth" // <-- adjust path to your auth.js

export const useAuthStore = defineStore("auth", {
  state: () => ({
    token: localStorage.getItem("token") || null,
    user: JSON.parse(localStorage.getItem("user") || "null"),
  }),

  getters: {
    isAuthed: (s) => !!s.token,
  },

  actions: {
    async login(identifier, password) {
      const res = await authApi.login(identifier, password)

      // Adjust these keys to match your backend response:
      // common: res.data.token, res.data.accessToken, res.data.jwt
      const token = res.token

      if (!token) throw new Error("Login response did not include a token")

      this.token = token

      console.log("Storing token:", token) // Debugging line
      localStorage.setItem("token", token)

      // Optional: if your API returns user info
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
