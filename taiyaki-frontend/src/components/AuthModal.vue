<!-- src/components/AuthModal.vue -->
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
          <button
            type="button"
            class="btn-close"
            aria-label="Close"
            @click="close"
          ></button>
        </div>

        <div class="modal-body">
          <!-- Tabs -->
          <ul class="nav nav-pills mb-3">
            <li class="nav-item">
              <button
                class="nav-link"
                :class="{ active: activeTab === 'login' }"
                type="button"
                @click="activeTab = 'login'"
              >
                Login
              </button>
            </li>
            <li class="nav-item">
              <button
                class="nav-link"
                :class="{ active : activeTab === 'register' }"
                type="button"
                @click="activeTab = 'register'"
              >
                Register
              </button>
            </li>
          </ul>

          <!-- Login form -->
          <form v-if="activeTab === 'login'" @submit.prevent="handleLogin" novalidate>
            <div class="mb-3">
              <label for="loginEmail" class="form-label">Email address</label>
              <input
                id="loginEmail"
                type="email"
                class="form-control"
                v-model="loginEmail"
                required
              />
            </div>
            <div class="mb-3">
              <label for="loginPassword" class="form-label">Password</label>
              <input
                id="loginPassword"
                type="password"
                class="form-control"
                v-model="loginPassword"
                required
              />
            </div>

            <div v-if="loginError" class="alert alert-danger py-2" role="alert">
              {{ loginError }}
            </div>

            <button type="submit" class="btn btn-primary w-100">
              Login
            </button>
          </form>

          <!-- Register form -->
          <form v-else @submit.prevent="handleRegister" novalidate>
            <div class="mb-3">
              <label for="registerEmail" class="form-label">Email address</label>
              <input
                id="registerEmail"
                type="email"
                class="form-control"
                v-model="registerEmail"
                required
              />
            </div>
            <div class="mb-3">
              <label for="registerPassword" class="form-label">Password</label>
              <input
                id="registerPassword"
                type="password"
                class="form-control"
                v-model="registerPassword"
                required
              />
            </div>
            <div class="mb-3">
              <label for="registerConfirm" class="form-label">Confirm password</label>
              <input
                id="registerConfirm"
                type="password"
                class="form-control"
                v-model="registerConfirm"
                required
              />
            </div>

            <div v-if="registerError" class="alert alert-danger py-2" role="alert">
              {{ registerError }}
            </div>

            <button type="submit" class="btn btn-primary w-100">
              Register
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  visible: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(["update:visible", "auth-success"]);

// internal state
const activeTab = ref("login");

// login
const loginEmail = ref("");
const loginPassword = ref("");
const loginError = ref("");

// register
const registerEmail = ref("");
const registerPassword = ref("");
const registerConfirm = ref("");
const registerError = ref("");

// Reset errors when opening
watch(
  () => props.visible,
  (value) => {
    if (value) {
      loginError.value = "";
      registerError.value = "";
    }
  }
);

const close = () => {
  emit("update:visible", false);
};

const handleLogin = () => {
  loginError.value = "";

  if (!loginEmail.value || !loginPassword.value) {
    loginError.value = "Please fill out both fields.";
    return;
  }

  // Demo login – replace with real auth API
  if (loginEmail.value === "test@example.com" && loginPassword.value === "password") {
    emit("auth-success", { mode: "login", email: loginEmail.value });
    close();
  } else {
    loginError.value = "Invalid email or password.";
  }
};

const handleRegister = () => {
  registerError.value = "";

  if (!registerEmail.value || !registerPassword.value || !registerConfirm.value) {
    registerError.value = "Please fill out all fields.";
    return;
  }

  if (registerPassword.value !== registerConfirm.value) {
    registerError.value = "Passwords do not match.";
    return;
  }

  // Demo register – replace with real auth API
  emit("auth-success", { mode: "register", email: registerEmail.value });
  close();
};
</script>
