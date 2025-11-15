<template>
  <div class="min-vh-100 d-flex flex-column bg-light">
    <Navbar />

    <div class="flex-fill d-flex align-items-center justify-content-center">
      <div class="container-fluid">
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
