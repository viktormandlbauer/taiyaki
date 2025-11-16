<!-- src/components/TaiyakiFooter.vue -->
<template>
  <footer class="taiyaki-footer text-white mt-auto">
    <div class="container py-3 d-flex justify-content-end">
      <div class="d-flex align-items-center gap-3">
        <!-- Internal links -->
        <RouterLink
          to="/imprint"
          class="text-white text-decoration-none"
        >
          Imprint
        </RouterLink>

        <RouterLink
          to="/help"
          class="text-white text-decoration-none"
        >
          Help
        </RouterLink>

        <!-- Social icons -->
        <a
          href="https://www.tiktok.com/@taiyaki"
          target="_blank"
          rel="noopener"
          aria-label="Taiyaki on TikTok"
          class="text-white text-decoration-none"
        >
          <i class="bi bi-tiktok fs-5"></i>
        </a>

        <a
          href="https://www.instagram.com/taiyaki"
          target="_blank"
          rel="noopener"
          aria-label="Taiyaki on Instagram"
          class="text-white text-decoration-none"
        >
          <i class="bi bi-instagram fs-5"></i>
        </a>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { RouterLink } from "vue-router";
</script>

<style scoped>
.taiyaki-footer {
  background-color: rgba(0, 0, 0, 0.7);
}
</style>
