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
import LoginForm from "@/components/auth/LoginForm.vue";
import RegisterForm from "@/components/auth/RegisterForm.vue";
import AuthTabs from '@/components/auth/AuthTabs.vue'

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

// receives already-validated data from LoginForm
const handleLogin = async ({ email, password }) => {
  loginError.value = "";
  submitting.value = true;
  try {
    // Demo login – replace with API call
    if (email === "test@example.com" && password === "password") {
      emit("auth-success", { mode: "login", email });
      close();
    } else {
      loginError.value = "Invalid email or password.";
    }
  } finally {
    submitting.value = false;
  }
};

// receives already-validated data from RegisterForm
const handleRegister = async (payload) => {
  registerError.value = "";
  submitting.value = true;
  try {
    // Demo register – replace with API call
    emit("auth-success", { mode: "register", ...payload });
    close();
  } finally {
    submitting.value = false;
  }
};
</script>
