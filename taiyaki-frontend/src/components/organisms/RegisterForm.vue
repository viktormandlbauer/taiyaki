<template>
  <form class="card p-4" @submit.prevent="onSubmit">
    <h3 class="mb-3">Register</h3>

    <AuthField
      id="email"
      label="Email"
      type="email"
      placeholder="you@example.com"
      v-model="email"
      autocomplete="email"
    />

    <AuthField
      id="password"
      label="Password"
      type="password"
      placeholder="Password"
      v-model="password"
      autocomplete="new-password"
    />

    <AuthField
      id="confirm"
      label="Confirm Password"
      type="password"
      placeholder="Repeat password"
      v-model="confirm"
      autocomplete="new-password"
    />

    <div v-if="error" class="alert alert-danger py-2">
      {{ error }}
    </div>

    <BaseButton type="submit" variant="success" class="w-100 mt-2">
      Create account
    </BaseButton>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import AuthField from '../molecules/Authfield.vue';
import BaseButton from '../atoms/BaseButton.vue';
import { useAuth } from '../../services/authService';
import { useRouter } from 'vue-router';

const email = ref('');
const password = ref('');
const confirm = ref('');
const error = ref('');
const auth = useAuth();
const router = useRouter();

async function onSubmit() {
  error.value = '';

  if (password.value !== confirm.value) {
    error.value = 'Passwords do not match';
    return;
  }

  try {
    await auth.register(email.value, password.value);
    router.push('/products');
  } catch (e) {
    error.value = e.message || 'Registration failed';
  }
}
</script>
