import { http } from "./http";
import { getSub } from "@/api/jwt.js";

export const profile = {
  async me() {
    const id = getSub();
    if (!id) throw new Error("No JWT subject (sub) found. Are you logged in?");

    const res = await http.get(`/api/users/${encodeURIComponent(id)}/profile`);
    return res.data.data;

  },
  async updateMe(payload) {
    const id = getSub();
    if (!id) throw new Error("No JWT subject (sub) found. Are you logged in?");
    const res = await http.put(`/api/users/${encodeURIComponent(id)}/profile`, payload);
    return res.data.data;
  },
};
