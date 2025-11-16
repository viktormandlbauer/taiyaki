<template>
  <div class="container mt-4 mb-5">
    <div class="info-page">
      <header class="mb-4 border-bottom pb-3">
        <h1 class="mb-1">
          <span v-if="emoji" class="me-2">{{ emoji }}</span>{{ title }}
        </h1>
        <p v-if="subtitle" class="mb-0 fs-6 subtitle-text">
          {{ subtitle }}
        </p>
      </header>

      <div class="info-content">
        <!-- Page-specific content -->
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  title: {
    type: String,
    required: true,
  },
  emoji: {
    type: String,
    default: "",
  },
  subtitle: {
    type: String,
    default: "",
  },
});
</script>

<style scoped>
.info-page {
  background-color: rgba(255, 255, 255, 0.92);
  border-radius: 1rem;
  padding: 2rem;
  backdrop-filter: blur(4px);
  box-shadow: 0 0.75rem 2rem rgba(0, 0, 0, 0.25);
  color: #4b2b30; /* friendly base text color */
}

/* Heading + subtitle colors */
h1 {
  color: var(--taiyaki-primary);
}

.subtitle-text {
  color: #7f4b52; /* softer warm tone */
}

/* Improve readability of body text */
.info-content p,
.info-content li {
  line-height: 1.7;
  font-size: 0.98rem;
  color: #4b2b30; /* warm, friendly text color */
}

/* Nice spacing for lists */
.info-content ul {
  padding-left: 1.25rem;
  margin-bottom: 1rem;
}

/* Links inside info pages */
.info-content a {
  color: var(--taiyaki-primary);
  text-decoration: underline;
}
.info-content a:hover {
  text-decoration: none;
}

@media (max-width: 576px) {
  .info-page {
    padding: 1.5rem;
  }
}
</style>
