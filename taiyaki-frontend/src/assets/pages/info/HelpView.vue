<template>
  <InfoPage
    title="Help & FAQ"
    emoji="❓"
    subtitle="Find answers to common questions about orders, shipping, and more."
  >
    <section class="mb-4">
      <h2 class="h5 mb-2">Orders & Shipping</h2>
      <p class="mb-0">
        For questions about your order status, shipping times, or tracking,
        please visit your account page or contact our support team at
        <a href="mailto:support@taiyaki.com" class="link-light">
          support@taiyaki.com
        </a>.
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h5 mb-2">Returns & Exchanges</h2>
      <p class="mb-0">
        If there’s an issue with your delivery, please email
        <a href="mailto:returns@taiyaki.com" class="link-light">
          returns@taiyaki.com
        </a>
        within 7 days of receiving your order.
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h5 mb-2">Allergies & Ingredients</h2>
      <p class="mb-0">
        For detailed allergen and ingredient information, please see our
        <RouterLink to="/allergies" class="link-light">
          Allergies page
        </RouterLink>
        or email
        <a href="mailto:allergies@taiyaki.com" class="link-light">
          allergies@taiyaki.com
        </a>.
      </p>
    </section>
  </InfoPage>
</template>

<script setup>
import InfoPage from "@/components/InfoPage.vue";
</script>
