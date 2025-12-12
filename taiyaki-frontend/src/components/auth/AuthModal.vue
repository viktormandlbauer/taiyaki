<template>
  <div
    v-if="visible"
    class="modal fade show d-block"
    tabindex="-1"
    role="dialog"
    aria-modal="true"
    aria-labelledby="authModalTitle"
    @click.self="close"
    style="background-color: rgba(0, 0, 0, 0.5);"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header border-0 pb-0">
          <h5 class="modal-title" id="authModalTitle">
            {{ activeTab === 'login' ? 'Sign in to Taiyaki' : 'Create your Taiyaki account' }}
          </h5>
          <button type="button" class="btn-close" aria-label="Close" @click="close"></button>
        </div>

        <div class="modal-body">

          <AuthTabs v-model="activeTab" />

          <LoginForm
            v-if="activeTab === 'login'"
            :loading="submitting"
            :apiError="loginError"
            @submit="handleLogin"
          />

          <RegisterForm
            v-else
            :loading="submitting"
            :apiError="registerError"
            @submit="handleRegister"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import { login, register } from "@/api/auth";

import LoginForm from "@/components/auth/LoginForm.vue";
import RegisterForm from "@/components/auth/RegisterForm.vue";
import AuthTabs from "@/components/auth/AuthTabs.vue";

const props = defineProps({
  visible: { type: Boolean, default: false },
});
const emit = defineEmits(["update:visible", "auth-success"]);

const activeTab = ref("login");
const submitting = ref(false);

const loginError = ref("");
const registerError = ref("");

watch(
  () => props.visible,
  (v) => {
    if (v) {
      loginError.value = "";
      registerError.value = "";
    }
  }
);

watch(activeTab, () => {
  loginError.value = "";
  registerError.value = "";
});

const close = () => emit("update:visible", false);

const handleLogin = async ({ identifier, password }) => {
  loginError.value = "";
  submitting.value = true;

  try {
    const res = await login(identifier, password);

    // common patterns:
    // 1) backend returns token
    const token = res.data?.token;
    if (token) localStorage.setItem("token", token);

    emit("auth-success", { mode: "login", user: res.data?.user, identifier });
    close();
  } catch (err) {
    // show backend message if present
    loginError.value =
      err.response?.data?.message ||
      err.response?.data?.error ||
      "Login failed. Please try again.";
  } finally {
    submitting.value = false;
  }
};

const handleRegister = async (payload) => {
  registerError.value = "";
  submitting.value = true;

  try {
    const res = await register(payload);
    emit("auth-success", { mode: "register", user: res.data?.user, ...payload });
    close();
  } catch (err) {
    registerError.value =
      err.response?.data?.message ||
      err.response?.data?.error ||
      "Registration failed. Please try again.";
  } finally {
    submitting.value = false;
  }
};
</script>
