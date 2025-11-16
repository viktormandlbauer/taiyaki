<template>
  <InfoPage
    title="Allergies & Ingredients Information"
    emoji="⚕️"
    subtitle="Please read carefully before ordering."
  >
    <section class="mb-4">
      <h2 class="h4 mb-3">🍰 We Care About What’s Inside</h2>
      <p>
        At Taiyaki, your safety and satisfaction are our top priorities.
        All our taiyaki are freshly baked in our Berlin kitchen,
        using carefully selected ingredients.
      </p>
      <p>
        Because many of our products contain common allergens,
        we want to make sure you have all the information you need before you order.
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h4 mb-3">⚠️ Allergen Notice</h2>
      <p>Our taiyaki may contain or come into contact with the following allergens:</p>
      <ul>
        <li>Gluten (wheat flour)</li>
        <li>Eggs</li>
        <li>Milk (dairy ingredients)</li>
        <li>Soy</li>
        <li>Tree nuts (in certain fillings such as chocolate or seasonal flavours)</li>
        <li>Sesame (in limited-edition products)</li>
      </ul>
      <p class="mb-0">
        Even if a particular flavour does not list these ingredients,
        please be aware that cross-contamination may occur,
        as all products are made in the same facility.
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h4 mb-3">🧁 Vegan &amp; Dairy-Free Options</h2>
      <p>
        We are proud to offer vegan taiyaki flavours made without eggs or dairy.
        These are baked using a separate recipe, but they are produced in the same kitchen,
        so trace amounts of allergens may still be present.
      </p>
      <p class="mb-0">
        For full details, please check the ingredients list on each product page
        or contact us before ordering.
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h4 mb-3">📦 Ingredient Transparency</h2>
      <p>
        We believe in being open about what goes into every taiyaki.
        You can find a complete list of ingredients and allergens on each product’s page.
      </p>
      <p class="mb-0">
        If you have a severe allergy or need custom information,
        please email us at
        <a href="mailto:allergies@taiyaki.com" class="link-light">
          allergies@taiyaki.com
        </a>.
        We’ll be happy to help and provide detailed ingredient sheets.
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h4 mb-3">❤️ Your Health Matters to Us</h2>
      <p>
        While we take every precaution to minimize risks,
        our kitchen is not certified allergen-free.
      </p>
      <p class="mb-0">
        If you have a serious food allergy, please consult your doctor
        or contact us directly before placing your order.
        Your well-being always comes first.
      </p>
    </section>
  </InfoPage>
</template>

<script setup>
import InfoPage from "@/components/InfoPage.vue";
</script>
