<template>
  <form class="card p-4" @submit.prevent="onSubmit">
    <h3 class="mb-3">Login</h3>

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
      autocomplete="current-password"
    />

    <div v-if="error" class="alert alert-danger py-2">
      {{ error }}
    </div>

    <BaseButton type="submit" variant="primary" class="w-100 mt-2">
      Login
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
const error = ref('');
const auth = useAuth();
const router = useRouter();

async function onSubmit() {
  error.value = '';
  try {
    await auth.login(email.value, password.value);
    router.push('/products');
  } catch (e) {
    error.value = e.message || 'Login failed';
  }
}
</script>
