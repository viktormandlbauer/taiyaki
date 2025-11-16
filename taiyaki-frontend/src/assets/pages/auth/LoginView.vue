<template>
  <div class="d-flex align-items-center justify-content-center min-vh-100">
    <div class="card shadow-sm" style="max-width: 400px; width: 100%;">
      <div class="card-body p-4">
        <h1 class="h4 mb-4 text-center">Login</h1>

        <form @submit.prevent="handleSubmit" novalidate>
          <!-- Email -->
          <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input
              id="email"
              type="email"
              class="form-control"
              v-model="email"
              required
            />
          </div>

          <!-- Password -->
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input
              id="password"
              type="password"
              class="form-control"
              v-model="password"
              required
            />
          </div>

          <!-- Submit -->
          <button type="submit" class="btn btn-primary w-100">
            Sign in
          </button>
        </form>
      </div>
    </div>

    <!-- Error modal -->
    <div
      v-if="showErrorModal"
      class="modal fade show d-block"
      tabindex="-1"
      role="dialog"
      aria-modal="true"
      aria-labelledby="loginErrorTitle"
      @click.self="closeModal"
      style="background-color: rgba(0, 0, 0, 0.5);"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="loginErrorTitle">Login error</h5>
            <button
              type="button"
              class="btn-close"
              aria-label="Close"
              @click="closeModal"
            ></button>
          </div>
          <div class="modal-body">
            {{ error }}
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-primary"
              @click="closeModal"
            >
              OK
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const email = ref("");
const password = ref("");
const error = ref("");
const showErrorModal = ref(false);

const openModal = (message) => {
  error.value = message;
  showErrorModal.value = true;
};

const closeModal = () => {
  showErrorModal.value = false;
};

const handleSubmit = () => {
  // reset
  error.value = "";

  if (!email.value || !password.value) {
    openModal("Please fill out both fields.");
    return;
  }

  // Fake login – replace with your API call
  if (email.value === "test@example.com" && password.value === "password") {
    alert("Login successful!");
    // e.g. router.push('/dashboard')
  } else {
    openModal("Invalid email or password.");
  }
};
</script>
