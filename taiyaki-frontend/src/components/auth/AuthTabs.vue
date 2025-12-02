<!-- src/components/AuthTabs.vue -->
<template>
  <ul class="nav nav-pills mb-3">
    <li class="nav-item">
      <button
        class="nav-link"
        :class="{ active: modelValue === 'login' }"
        type="button"
        @click="$emit('update:modelValue', 'login')"
      >
        Login
      </button>
    </li>
    <li class="nav-item">
      <button
        class="nav-link"
        :class="{ active: modelValue === 'register' }"
        type="button"
        @click="$emit('update:modelValue', 'register')"
      >
        Register
      </button>
    </li>
  </ul>
</template>

<script setup>
defineProps({
  modelValue: {
    type: String,
    default: "login",
    validator: (v) => ["login", "register"].includes(v),
  },
});
defineEmits(["update:modelValue"]);
</script>
