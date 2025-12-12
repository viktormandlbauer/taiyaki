import { http } from "./http";

export const usersApi = {
  me() {
    return http.get("/api/users/me");
  },
  updateMe(payload) {
    return http.put("/api/users/me", payload);
  },
  changePassword(payload) {
    return http.put("/api/users/me/password", payload);
  },
};
