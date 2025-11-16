<template>
  <div class="d-flex align-items-center justify-content-center min-vh-100 bg-light">
    <div class="card shadow-sm" style="max-width: 400px; width: 100%;">
      <div class="card-body p-4">
        <h1 class="h4 mb-4 text-center">Register</h1>

        <form @submit.prevent="handleRegister">
          <div class="mb-3">
            <label class="form-label" for="name">Name</label>
            <input id="name" v-model="name" type="text" class="form-control" required />
          </div>

          <div class="mb-3">
            <label class="form-label" for="email">Email</label>
            <input id="email" v-model="email" type="email" class="form-control" required />
          </div>

          <div class="mb-3">
            <label class="form-label" for="password">Password</label>
            <input id="password" v-model="password" type="password" class="form-control" required />
          </div>

          <button class="btn btn-primary w-100" type="submit">
            Create account
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const name = ref("");
const email = ref("");
const password = ref("");

const handleRegister = () => {
  // replace with API call
  alert(`Registered: ${name.value} (${email.value})`);
};
</script>
