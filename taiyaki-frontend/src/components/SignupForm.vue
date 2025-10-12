<script setup lang="ts">
import { reactive, ref } from 'vue';

export interface SignupPayload {
  fullName: string;
  email: string;
  password: string;
  confirmPassword: string;
}

const emit = defineEmits<{
  (e: 'signup', payload: SignupPayload): void;
}>();

const form = reactive<SignupPayload>({
  fullName: '',
  email: '',
  password: '',
  confirmPassword: '',
});

const errors = reactive({
  fullName: '',
  email: '',
  password: '',
  confirmPassword: '',
});

const submitAttempted = ref(false);

const validate = () => {
  errors.fullName = form.fullName.trim() ? '' : 'Full name is required.';
  errors.email = form.email.trim() ? '' : 'Email is required.';
  errors.password =
    form.password.length >= 8 ? '' : 'Password must be at least 8 characters long.';
  errors.confirmPassword =
    form.confirmPassword === form.password ? '' : 'Passwords do not match.';

  return (
    !errors.fullName && !errors.email && !errors.password && !errors.confirmPassword
  );
};

const handleSubmit = () => {
  submitAttempted.value = true;
  if (!validate()) {
    return;
  }

  emit('signup', { ...form });
};
</script>

<template>
  <form class="card" @submit.prevent="handleSubmit" novalidate>
    <h2>Create an account</h2>
    <p class="subtitle">Sign up to access the Taiyaki app.</p>

    <label>
      <span>Full name</span>
      <input v-model="form.fullName" type="text" autocomplete="name" required />
      <span v-if="submitAttempted && errors.fullName" class="error">{{
        errors.fullName
      }}</span>
    </label>

    <label>
      <span>Email</span>
      <input v-model="form.email" type="email" autocomplete="email" required />
      <span v-if="submitAttempted && errors.email" class="error">{{ errors.email }}</span>
    </label>

    <label>
      <span>Password</span>
      <input
        v-model="form.password"
        type="password"
        autocomplete="new-password"
        minlength="8"
        required
      />
      <span v-if="submitAttempted && errors.password" class="error">{{
        errors.password
      }}</span>
    </label>

    <label>
      <span>Confirm password</span>
      <input
        v-model="form.confirmPassword"
        type="password"
        autocomplete="new-password"
        minlength="8"
        required
      />
      <span v-if="submitAttempted && errors.confirmPassword" class="error">{{
        errors.confirmPassword
      }}</span>
    </label>

    <button type="submit">Create account</button>
  </form>
</template>

<style scoped>
.card {
  display: grid;
  gap: 1rem;
  padding: 2rem;
  border-radius: 1rem;
  background: #ffffff;
  box-shadow: 0 20px 60px -30px rgba(15, 23, 42, 0.45);
}

h2 {
  margin: 0;
  font-size: 1.75rem;
  font-weight: 700;
  color: #0f172a;
}

.subtitle {
  margin: 0;
  color: #475569;
  font-size: 1rem;
}

label {
  display: grid;
  gap: 0.5rem;
  font-size: 0.95rem;
  color: #0f172a;
}

label span {
  font-weight: 600;
}

input[type='text'],
input[type='email'],
input[type='password'] {
  padding: 0.75rem 1rem;
  border-radius: 0.75rem;
  border: 1px solid #cbd5f5;
  background: #f8faff;
  font-size: 1rem;
  outline: none;
  transition: border-color 0.2s ease;
}

input[type='text']:focus,
input[type='email']:focus,
input[type='password']:focus {
  border-color: #6366f1;
}

button {
  padding: 0.85rem 1rem;
  border-radius: 0.75rem;
  border: none;
  background: linear-gradient(135deg, #6366f1, #8b5cf6);
  color: #ffffff;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

button:hover {
  transform: translateY(-1px);
  box-shadow: 0 14px 30px -12px rgba(99, 102, 241, 0.65);
}

.error {
  color: #ef4444;
  font-size: 0.85rem;
}
</style>
