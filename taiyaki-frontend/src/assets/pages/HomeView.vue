<template>
  <div class="container mt-4 mb-5">
    <!-- Hero / Jumbotron -->
    <section
      class="p-5 mb-4 rounded-3 text-center text-white"
      style="background-color: #A7333F;"
    >
      <h1 class="display-5 fw-bold mb-3">
        Welcome to Taiyaki!
      </h1>
      <div class="container py-5">
        <p class="fs-5 mb-4 mx-auto" style="max-width: 800px;">
          Discover the delightful world of Taiyaki, Japan’s beloved
          fish-shaped cake filled with sweet and savory flavors.
          Each of our Taiyaki is freshly baked with care, combining
          traditional recipes with creative twists. Whether you crave
          classic red bean, creamy custard, or something new and
          surprising, there’s a flavor waiting just for you.
        </p>
        <p class="fs-5 mb-0 mx-auto" style="max-width: 800px;">
          Treat yourself to a warm bite of happiness today!
          Your Taiyaki adventure starts below.
        </p>
      </div>
    </section>

    <!-- Products below hero (page becomes scrollable) -->
    <section class="mb-5">
      <h2 class="text-center mb-4 text-white">
        Our Favorites
      </h2>

      <div class="row g-4">
        <div
          v-for="product in products"
          :key="product.id"
          class="col-12 col-sm-6 col-lg-4"
        >
          <ProductCard
            :title="product.title"
            :price="product.price"
            :rating="product.rating"
            :sizes="product.sizes"
          />
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import ProductCard from "@/components/ProductCard.vue";

const products = [
  {
    id: 1,
    title: "Cheesy Taiyaki",
    price: 9.99,
    rating: 4,
    sizes: ["3 pcs", "9 pcs", "24 pcs"],
  },
  {
    id: 2,
    title: "Classic Red Bean",
    price: 8.49,
    rating: 5,
    sizes: ["3 pcs", "6 pcs", "12 pcs"],
  },
  {
    id: 3,
    title: "Custard Dream",
    price: 8.99,
    rating: 4,
    sizes: ["3 pcs", "9 pcs", "24 pcs"],
  },
];
</script>
