<template>
  <div>
    <h2 class="mb-3">Taiyaki Products</h2>

    <div class="row g-3">
      <div
        v-for="product in products"
        :key="product.id"
        class="col-12 col-sm-6 col-md-4"
      >
        <ProductCard :product="product" @add-to-cart="addToCart" />
      </div>
    </div>

    <div v-if="products.length === 0" class="text-muted mt-3">
      No taiyaki yet. Time to bake some :3
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import ProductCard from '../molecules/ProductCard.vue';
import { getProducts } from '../../services/productService';

const products = ref([]);

onMounted(async () => {
  products.value = await getProducts();
});

function addToCart(product) {
  // For now just log; later we implement a cart service
  console.log('Add to cart:', product);
}
</script>
