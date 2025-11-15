<template>
  <button
    :type="type"
    class="btn"
    :class="`btn-${variant}`"
    @click="$emit('click', $event)"
  >
    <slot />
  </button>
</template>

<script setup>
const props = defineProps({
  type: { type: String, default: 'button' },
  variant: { type: String, default: 'primary' }
});
</script>