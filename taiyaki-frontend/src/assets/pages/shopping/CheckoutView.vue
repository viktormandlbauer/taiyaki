<template>
  <div class="container mt-4 mb-5">
    <div class="checkout-card card">
      <div class="card-body">
        <h1 class="h3 mb-4">Checkout</h1>

        <div class="row g-4">
          <!-- Left: customer + shipping + payment -->
          <div class="col-12 col-lg-7">
            <!-- Contact details -->
            <section class="mb-4">
              <h2 class="h5 mb-3">Contact details</h2>
              <form @submit.prevent>
                <div class="mb-3">
                  <label for="fullName" class="form-label">Full name</label>
                  <input
                    v-model="fullName"
                    type="text"
                    class="form-control"
                    id="fullName"
                    placeholder="Tania Example"
                  />
                </div>

                <div class="mb-3">
                  <label for="email" class="form-label">Email address</label>
                  <input
                    v-model="email"
                    type="email"
                    class="form-control"
                    id="email"
                    placeholder="you@example.com"
                  />
                </div>

                <div class="mb-3">
                  <label for="phone" class="form-label">Phone</label>
                  <input
                    v-model="phone"
                    type="tel"
                    class="form-control"
                    id="phone"
                    placeholder="+49 ..."
                  />
                </div>
              </form>
            </section>

            <!-- Shipping address -->
            <section class="mb-4">
              <h2 class="h5 mb-3">Shipping address</h2>
              <form @submit.prevent>
                <div class="mb-3">
                  <label for="street" class="form-label">Street &amp; house number</label>
                  <input
                    v-model="street"
                    type="text"
                    class="form-control"
                    id="street"
                    placeholder="Sakura Street 18"
                  />
                </div>

                <div class="row">
                  <div class="col-md-6 mb-3">
                    <label for="zip" class="form-label">Postal code</label>
                    <input
                      v-model="zip"
                      type="text"
                      class="form-control"
                      id="zip"
                      placeholder="10243"
                    />
                  </div>
                  <div class="col-md-6 mb-3">
                    <label for="city" class="form-label">City</label>
                    <input
                      v-model="city"
                      type="text"
                      class="form-control"
                      id="city"
                      placeholder="Berlin"
                    />
                  </div>
                </div>

                <div class="mb-3">
                  <label for="country" class="form-label">Country</label>
                  <input
                    v-model="country"
                    type="text"
                    class="form-control"
                    id="country"
                    placeholder="Germany"
                  />
                </div>

                <div class="form-check mb-2">
                  <input
                    v-model="saveAddress"
                    class="form-check-input"
                    type="checkbox"
                    id="saveAddress"
                  />
                  <label class="form-check-label" for="saveAddress">
                    Save this address for next time
                  </label>
                </div>
              </form>
            </section>

            <!-- Payment -->
            <section>
              <h2 class="h5 mb-3">Payment</h2>

              <div class="mb-3">
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="radio"
                    id="paymentCard"
                    value="card"
                    v-model="paymentMethod"
                  />
                  <label class="form-check-label" for="paymentCard">
                    Credit / Debit card
                  </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="radio"
                    id="paymentPaypal"
                    value="paypal"
                    v-model="paymentMethod"
                  />
                  <label class="form-check-label" for="paymentPaypal">
                    PayPal
                  </label>
                </div>
              </div>

              <div v-if="paymentMethod === 'card'" class="row">
                <div class="col-12 mb-3">
                  <label for="cardNumber" class="form-label">Card number</label>
                  <input
                    v-model="cardNumber"
                    type="text"
                    class="form-control"
                    id="cardNumber"
                    placeholder="•••• •••• •••• ••••"
                  />
                </div>
                <div class="col-6 mb-3">
                  <label for="cardExpiry" class="form-label">Expiry (MM/YY)</label>
                  <input
                    v-model="cardExpiry"
                    type="text"
                    class="form-control"
                    id="cardExpiry"
                    placeholder="08/28"
                  />
                </div>
                <div class="col-6 mb-3">
                  <label for="cardCvc" class="form-label">CVC</label>
                  <input
                    v-model="cardCvc"
                    type="text"
                    class="form-control"
                    id="cardCvc"
                    placeholder="123"
                  />
                </div>
              </div>
            </section>
          </div>

          <!-- Right: order summary -->
          <div class="col-12 col-lg-5">
            <aside class="summary-card p-3 p-md-4">
              <h2 class="h5 mb-3">Order summary</h2>

              <ul class="list-unstyled mb-3">
                <li
                  v-for="item in items"
                  :key="item.id"
                  class="d-flex justify-content-between align-items-center mb-2"
                >
                  <div>
                    <div class="fw-semibold">{{ item.name }}</div>
                    <small class="text-muted">
                      {{ item.variant }} &times; {{ item.quantity }}
                    </small>
                  </div>
                  <div class="fw-semibold">
                    {{ formatPrice(item.price * item.quantity) }}
                  </div>
                </li>
              </ul>

              <div class="border-top pt-3 mb-2">
                <div class="d-flex justify-content-between mb-1">
                  <span class="text-muted">Subtotal</span>
                  <span>{{ formatPrice(subtotal) }}</span>
                </div>
                <div class="d-flex justify-content-between mb-1">
                  <span class="text-muted">Shipping</span>
                  <span>{{ shippingCost > 0 ? formatPrice(shippingCost) : "Free" }}</span>
                </div>
              </div>

              <div class="d-flex justify-content-between align-items-baseline mb-3">
                <span class="fw-semibold">Total</span>
                <span class="fw-bold fs-5">{{ formatPrice(total) }}</span>
              </div>

              <button
                class="btn btn-primary w-100"
                type="button"
                @click="placeOrder"
              >
                Place order
              </button>

              <p class="small text-muted mt-2 mb-0">
                By placing your order, you agree to our terms and conditions.
              </p>
            </aside>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";

// Dummy data – later you can replace this with shared cart state
const items = ref([
  {
    id: 1,
    name: "Cheesy Taiyaki",
    variant: "3 pcs",
    quantity: 1,
    price: 9.99,
  },
  {
    id: 2,
    name: "Classic Red Bean Taiyaki",
    variant: "9 pcs",
    quantity: 2,
    price: 18.5,
  },
]);

const subtotal = computed(() =>
  items.value.reduce((sum, item) => sum + item.price * item.quantity, 0)
);

// Example: free shipping over 30 €
const shippingCost = computed(() => (subtotal.value >= 30 ? 0 : 4.9));

const total = computed(() => subtotal.value + shippingCost.value);

const formatPrice = (value) => `${value.toFixed(2)} €`;

// Simple form state (placeholder – connect to backend later)
const fullName = ref("");
const email = ref("");
const phone = ref("");
const street = ref("");
const zip = ref("");
const city = ref("");
const country = ref("Germany");
const saveAddress = ref(true);

const paymentMethod = ref("card");
const cardNumber = ref("");
const cardExpiry = ref("");
const cardCvc = ref("");

const placeOrder = () => {
  // For now, just log – later you can send this to your backend
  console.log("Placing order with:", {
    fullName: fullName.value,
    email: email.value,
    phone: phone.value,
    address: {
      street: street.value,
      zip: zip.value,
      city: city.value,
      country: country.value,
    },
    paymentMethod: paymentMethod.value,
    items: items.value,
    total: total.value,
  });
  alert("Thank you! Your order has been placed (demo).");
};
</script>

<style scoped>
.checkout-card {
  background-color: rgba(255, 255, 255, 0.95);
  border-radius: 1rem;
  box-shadow: 0 0.75rem 2rem rgba(0, 0, 0, 0.25);
}

.summary-card {
  background-color: #fff7f7;
  border-radius: 0.75rem;
  border: 1px solid rgba(0, 0, 0, 0.05);
}
</style>
