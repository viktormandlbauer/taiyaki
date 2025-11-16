<template>
  <div class="card product-card h-100 border-0 shadow-sm">
    <!-- Image + rating -->
    <div class="product-image-wrapper position-relative">
      <img
        :src="image"
        :alt="title"
        class="product-image w-100"
      />

      <!-- Rating overlay -->
      <div
        class="rating-box bg-white px-3 py-2 position-absolute translate-middle-y shadow-sm d-inline-flex"
        style="left: 0.75rem; bottom: 0;"
      >
        <i
          v-for="i in 5"
          :key="i"
          class="bi me-1"
          :class="i <= rating ? 'bi-star-fill text-warning' : 'bi-star-fill text-secondary opacity-25'"
        ></i>
      </div>
    </div>

    <div class="card-body">
      <div class="d-flex justify-content-between align-items-baseline mb-2">
        <span class="fs-5">{{ title }}</span>
        <span class="fw-semibold fs-5">{{ price.toFixed(2) }} €</span>
      </div>

      <div class="d-flex flex-wrap gap-2 mt-3">
        <button
          v-for="(size, index) in sizes"
          :key="index"
          type="button"
          class="btn btn-sm size-pill"
        >
          {{ size }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import defaultProductImage from "@/assets/images/taiyaki-default-product.jpg";

const { title, price, rating, sizes, image } = defineProps({
  title: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  rating: {
    type: Number,
    default: 4, // 0–5
  },
  sizes: {
    type: Array,
    default: () => ["3 pcs", "9 pcs", "24 pcs"],
  },
  // optional override, but defaults to the Taiyaki image
  image: {
    type: String,
    default: defaultProductImage,
  },
});
</script>

<style scoped>
.product-image-wrapper {
  background-color: #f3f3f3;
}

.product-image {
  height: 220px; /* adjust as you like */
  object-fit: cover;
  display: block;
}

.size-pill {
  border-radius: 999px;
  background-color: #e5e5e5;
  border: none;
  padding-inline: 1.25rem;
}
</style>
