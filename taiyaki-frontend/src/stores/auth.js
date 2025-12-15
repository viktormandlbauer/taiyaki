import { defineStore } from "pinia";
import * as authApi from "@/api/auth";
import { decodeJwtPayload, getAccessToken } from "@/api/jwt";

const TOKEN_KEY = "token";
const USER_KEY = "user";

function loadUserFromStorage() {
  try {
    return JSON.parse(localStorage.getItem(USER_KEY) || "null");
  } catch {
    return null;
  }
}

function extractToken(res) {
  return res?.data?.token || res?.data?.accessToken || res?.data?.jwt || null;
}

function hasAdminRole(token) {
  const payload = decodeJwtPayload(token);
  if (!payload) return false;

  const raw =
    payload.roles ??
    payload.role ??
    payload.authorities ??
    payload.scope ??
    "";

  const roles = Array.isArray(raw)
    ? raw
    : typeof raw === "string"
      ? raw.includes(" ")
        ? raw.split(" ").filter(Boolean)
        : raw.includes(",")
          ? raw.split(",").map(s => s.trim()).filter(Boolean)
          : raw
            ? [raw]
            : []
      : [];

  return roles.includes("ROLE_ADMIN") || roles.includes("ADMIN");
}

export const useAuthStore = defineStore("auth", {
  state: () => ({
    token: getAccessToken?.() ?? localStorage.getItem(TOKEN_KEY) ?? null,
    user: loadUserFromStorage(),
  }),

  getters: {
    isAuthed: (s) => !!s.token,
    isAdmin: (s) => (s.token ? hasAdminRole(s.token) : false),
  },

  actions: {
    setSession(token, user = null) {
      this.token = token;
      localStorage.setItem(TOKEN_KEY, token);

      this.user = user;
      if (user) localStorage.setItem(USER_KEY, JSON.stringify(user));
      else localStorage.removeItem(USER_KEY);
    },

    async login(identifier, password) {
      const res = await authApi.login(identifier, password);

      const token = extractToken(res);
      if (!token) throw new Error("Login response did not include a token");

      this.setSession(token, res.data?.user ?? null);
      return res;
    },

    async register(payload) {
      const res = await authApi.register(payload);

      const token = extractToken(res);
      if (!token) throw new Error("Register response did not include a token");

      this.setSession(token, res.data?.user ?? null);
      return res;
    },

    logout() {
      this.token = null;
      this.user = null;
      localStorage.removeItem(TOKEN_KEY);
      localStorage.removeItem(USER_KEY);
    },
  },
});
