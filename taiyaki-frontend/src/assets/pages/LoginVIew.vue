<template>
  <div class="d-flex align-items-center justify-content-center min-vh-100 bg-light">
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

          <!-- Error message -->
          <div v-if="error" class="alert alert-danger py-2" role="alert">
            {{ error }}
          </div>

          <!-- Submit -->
          <button type="submit" class="btn btn-primary w-100">
            Sign in
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const email = ref("");
const password = ref("");
const error = ref("");

const handleSubmit = () => {
  error.value = "";

  if (!email.value || !password.value) {
    error.value = "Please fill out both fields.";
    return;
  }

  // Fake login – replace with your API call
  if (email.value === "test@example.com" && password.value === "password") {
    alert("Login successful!");
    // e.g. use router.push('/dashboard') here
  } else {
    error.value = "Invalid email or password.";
  }
};
</script>
