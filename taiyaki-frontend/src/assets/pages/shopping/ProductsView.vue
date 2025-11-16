<template>
  <div class="container py-4">
    <h1 class="h3 mb-4">Products</h1>

    <div class="row g-3">
      <div class="col-md-4" v-for="product in products" :key="product.id">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">{{ product.name }}</h5>
            <p class="card-text mb-1">{{ product.description }}</p>
            <p class="fw-bold mb-0">{{ product.price.toFixed(2) }} €</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const products = [
  { id: 1, name: "Product A", description: "Description A", price: 9.99 },
  { id: 2, name: "Product B", description: "Description B", price: 19.99 },
  { id: 3, name: "Product C", description: "Description C", price: 29.99 },
];
</script>
