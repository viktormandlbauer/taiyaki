import { http } from "./http";

export async function login(identifier, password) {

  return http.post("/api/auth/login", { identifier, password });

}

export async function register(payload) {
  return http.post("/api/auth/register", payload);
}
