<template>
  <BaseCard>
    <template #header>
      <h5 class="mb-0">{{ product.name }}</h5>
    </template>

    <p class="card-text mb-1">{{ product.description }}</p>
    <p class="fw-bold mb-2">{{ formattedPrice }}</p>

    <template #footer>
      <BaseButton variant="success" @click="$emit('add-to-cart', product)">
        Add to cart
      </BaseButton>
    </template>
  </BaseCard>
</template>

<script setup>
import { computed } from 'vue';
import BaseCard from '../atoms/BaseCard.vue';
import BaseButton from '../atoms/BaseButton.vue';

const props = defineProps({
  product: {
    type: Object,
    required: true
  }
});

const formattedPrice = computed(
  () => `${props.product.price.toFixed(2)} €`
);
</script>
