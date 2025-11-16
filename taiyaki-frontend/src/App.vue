<template>
  <div
    class="app-background min-vh-100 d-flex flex-column"
    :style="appStyle"
  >
    <TaiyakiNavbar />

    <!-- Main content -->
    <main class="flex-grow-1">
      <RouterView />
    </main>

    <TaiyakiFooter />
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import TaiyakiNavbar from "@/components/Navbar.vue";
import TaiyakiFooter from "@/components/Footer.vue";
import appBackground from "@/assets/images/taiyaki-hero.jpg";

const appStyle = {
  backgroundImage: `url(${appBackground})`,
  backgroundSize: "440px 440px",
  backgroundRepeat: "repeat",
  backgroundPosition: "top left",
  backgroundAttachment: "fixed",
};
</script>

<style>
.app-background {
  background-color: #fff5f5; /* fallback color under tiles */
}
</style>
