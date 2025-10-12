<script setup lang="ts">
import SignupForm, { type SignupPayload } from '../components/SignupForm.vue';

const emit = defineEmits<{
  (e: 'signup', payload: SignupPayload): void;
}>();

const handleSignup = (payload: SignupPayload) => {
  emit('signup', payload);
};
</script>

<template>
  <SignupForm @signup="handleSignup" />
</template>
