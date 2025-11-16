<template>
  <InfoPage
    title="Imprint (Impressum)"
    emoji="📄"
  >
    <section class="mb-4">
      <h2 class="h5">Information according to § 5 TMG</h2>
      <p class="mb-1">Taiyaki Co.</p>
      <p class="mb-1">Sakura Street 18</p>
      <p class="mb-1">10243 Berlin</p>
      <p class="mb-0">Germany</p>
    </section>

    <section class="mb-4">
      <h2 class="h5">Represented by</h2>
      <p class="mb-0">
        Tania Kaffashbashi, Danai Akrivou, Viktor Mandlbauer
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h5">Contact</h2>
      <p class="mb-1">Phone: +49 (0)30 98765432</p>
      <p class="mb-1">
        Email:
        <a href="mailto:contact@taiyaki.com" class="link-light">
          contact@taiyaki.com
        </a>
      </p>
      <p class="mb-0">
        Website:
        <a
          href="https://www.taiyaki.com"
          target="_blank"
          rel="noopener"
          class="link-light"
        >
          www.taiyaki.com
        </a>
      </p>
    </section>

    <section class="mb-4">
      <h2 class="h5">Register Entry</h2>
      <p class="mb-1">Entry in the commercial register.</p>
      <p class="mb-1">Register court: Amtsgericht Berlin</p>
      <p class="mb-0">Register number: HRB 987654</p>
    </section>

    <section class="mb-4">
      <h2 class="h5">VAT ID</h2>
      <p class="mb-1">
        VAT identification number according to § 27 a Umsatzsteuergesetz:
      </p>
      <p class="mb-0">DE 987654321</p>
    </section>

    <section class="mb-4">
      <h2 class="h5">Responsible for content according to § 55 Abs. 2 RStV</h2>
      <p class="mb-1">Tania Kaffashbashi</p>
      <p class="mb-1">Sakura Street 18</p>
      <p class="mb-0">10243 Berlin</p>
    </section>
  </InfoPage>
</template>

<script setup>
import InfoPage from "@/components/InfoPage.vue";
</script>
