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
  backgroundSize: "cover",
  backgroundPosition: "center",
  backgroundRepeat: "no-repeat",
  backgroundAttachment: "fixed",
};
</script>

<style>

</style>
