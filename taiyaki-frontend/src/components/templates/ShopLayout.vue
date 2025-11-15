<template>
  <div class="min-vh-100 d-flex flex-column bg-light">
    <Navbar />
    <div class="flex-fill">
      <div class="container-xxl py-4">
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup>
import Navbar from '../organisms/Navbar.vue';
</script>

<style scoped>
.min-vh-100 {
  min-height: 100vh;
}
</style>
