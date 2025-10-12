<script setup lang="ts">
import LoginForm, { type LoginPayload } from '../components/LoginForm.vue';

const emit = defineEmits<{
  (e: 'login', payload: LoginPayload): void;
}>();

const handleLogin = (payload: LoginPayload) => {
  emit('login', payload);
};
</script>

<template>
  <LoginForm @login="handleLogin" />
</template>
