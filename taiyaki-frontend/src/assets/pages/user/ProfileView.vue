<template>
  <div class="container mt-4 mb-5">
    <div class="card profile-card">
      <div class="card-body">
        <h1 class="h4 mb-4">Your Profile</h1>

        <!-- Simple tab navigation controlled by Vue -->
        <ul class="nav nav-pills mb-4 profile-tabs">
          <li class="nav-item">
            <button
              class="nav-link"
              :class="{ active: activeTab === 'details' }"
              type="button"
              @click="activeTab = 'details'"
            >
              Personal details
            </button>
          </li>
          <li class="nav-item">
            <button
              class="nav-link"
              :class="{ active: activeTab === 'payments' }"
              type="button"
              @click="activeTab = 'payments'"
            >
              Payment methods
            </button>
          </li>
          <li class="nav-item">
            <button
              class="nav-link"
              :class="{ active: activeTab === 'password' }"
              type="button"
              @click="activeTab = 'password'"
            >
              Password
            </button>
          </li>
        </ul>

        <!-- PERSONAL DETAILS -->
        <section v-if="activeTab === 'details'">
          <h2 class="h5 mb-3">Personal details</h2>
          <form @submit.prevent="savePersonalDetails">
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName" class="form-label">First name</label>
                <input
                  id="firstName"
                  v-model="firstName"
                  type="text"
                  class="form-control"
                  placeholder="Tania"
                />
              </div>
              <div class="col-md-6 mb-3">
                <label for="lastName" class="form-label">Last name</label>
                <input
                  id="lastName"
                  v-model="lastName"
                  type="text"
                  class="form-control"
                  placeholder="Example"
                />
              </div>
            </div>

            <div class="mb-3">
              <label for="email" class="form-label">Email address</label>
              <input
                id="email"
                v-model="email"
                type="email"
                class="form-control"
                placeholder="you@example.com"
              />
            </div>

            <div class="mb-3">
              <label for="phone" class="form-label">Phone</label>
              <input
                id="phone"
                v-model="phone"
                type="tel"
                class="form-control"
                placeholder="+49 ..."
              />
            </div>

            <div class="mb-3">
              <label for="street" class="form-label">Street &amp; house number</label>
              <input
                id="street"
                v-model="street"
                type="text"
                class="form-control"
                placeholder="Sakura Street 18"
              />
            </div>

            <div class="row">
              <div class="col-md-4 mb-3">
                <label for="zip" class="form-label">Postal code</label>
                <input
                  id="zip"
                  v-model="zip"
                  type="text"
                  class="form-control"
                  placeholder="10243"
                />
              </div>
              <div class="col-md-4 mb-3">
                <label for="city" class="form-label">City</label>
                <input
                  id="city"
                  v-model="city"
                  type="text"
                  class="form-control"
                  placeholder="Berlin"
                />
              </div>
              <div class="col-md-4 mb-3">
                <label for="country" class="form-label">Country</label>
                <input
                  id="country"
                  v-model="country"
                  type="text"
                  class="form-control"
                  placeholder="Germany"
                />
              </div>
            </div>

            <!-- uses .btn-primary → themed in theme.css -->
            <button type="submit" class="btn btn-primary mt-2">
              Save changes
            </button>
          </form>
        </section>

        <!-- PAYMENT METHODS -->
        <section v-else-if="activeTab === 'payments'">
          <h2 class="h5 mb-3">Payment methods</h2>

          <div class="mb-3">
            <p class="text-muted small mb-1">
              Default payment method:
            </p>
            <div class="d-flex align-items-center justify-content-between payment-box">
              <div>
                <div class="fw-semibold">
                  {{ cardBrand }} •••• {{ cardLast4 }}
                </div>
                <small class="text-muted">Expires {{ cardExpiry }}</small>
              </div>
              <!-- switched to outline-primary so it uses theme colors -->
              <button
                type="button"
                class="btn btn-outline-primary btn-sm"
                @click="clearPaymentMethod"
              >
                Remove
              </button>
            </div>
          </div>

          <hr class="my-4" />

          <h3 class="h6 mb-3">Add or update card</h3>
          <form @submit.prevent="savePaymentMethod">
            <div class="mb-3">
              <label for="cardName" class="form-label">Name on card</label>
              <input
                id="cardName"
                v-model="cardName"
                type="text"
                class="form-control"
                placeholder="Maxi Mustermann"
              />
            </div>
            <div class="mb-3">
              <label for="cardNumber" class="form-label">Card number</label>
              <input
                id="cardNumber"
                v-model="cardNumber"
                type="text"
                class="form-control"
                placeholder="•••• •••• •••• ••••"
              />
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="newCardExpiry" class="form-label">Expiry (MM/YY)</label>
                <input
                  id="newCardExpiry"
                  v-model="newCardExpiry"
                  type="text"
                  class="form-control"
                  placeholder="08/28"
                />
              </div>
              <div class="col-md-6 mb-3">
                <label for="newCardCvc" class="form-label">CVC</label>
                <input
                  id="newCardCvc"
                  v-model="newCardCvc"
                  type="text"
                  class="form-control"
                  placeholder="123"
                />
              </div>
            </div>

            <button type="submit" class="btn btn-primary mt-2">
              Save payment method
            </button>
          </form>

          <p class="small text-muted mt-3 mb-0">
            For your security, sensitive card data should be processed and stored
            by a certified payment provider.
          </p>
        </section>

        <!-- PASSWORD -->
        <section v-else>
          <h2 class="h5 mb-3">Change password</h2>
          <form @submit.prevent="changePassword">
            <div class="mb-3">
              <label for="currentPassword" class="form-label">Current password</label>
              <input
                id="currentPassword"
                v-model="currentPassword"
                type="password"
                class="form-control"
              />
            </div>

            <div class="mb-3">
              <label for="newPassword" class="form-label">New password</label>
              <input
                id="newPassword"
                v-model="newPassword"
                type="password"
                class="form-control"
              />
            </div>

            <div class="mb-3">
              <label for="confirmPassword" class="form-label">Confirm new password</label>
              <input
                id="confirmPassword"
                v-model="confirmPassword"
                type="password"
                class="form-control"
              />
            </div>

            <button type="submit" class="btn btn-primary mt-2">
              Update password
            </button>

            <p class="small text-muted mt-3 mb-0">
              Choose a strong password with at least 8 characters, including letters and numbers.
            </p>
          </form>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { usersApi } from "@/api/users";

// tabs
const activeTab = ref("details");

// loading + errors
const loadingProfile = ref(false);
const savingProfile = ref(false);
const changingPw = ref(false);

const profileError = ref("");
const profileSuccess = ref("");

// personal details (bind to inputs)
const firstName = ref("");
const lastName = ref("");
const email = ref("");
const phone = ref("");
const street = ref("");
const zip = ref("");
const city = ref("");
const country = ref("");

// payment methods (still dummy unless you have endpoints)
const cardBrand = ref("Visa");
const cardLast4 = ref("1234");
const cardExpiry = ref("08/28");
const cardName = ref("");
const cardNumber = ref("");
const newCardExpiry = ref("");
const newCardCvc = ref("");

// password change
const currentPassword = ref("");
const newPassword = ref("");
const confirmPassword = ref("");

function setMsg(err = "", ok = "") {
  profileError.value = err;
  profileSuccess.value = ok;
}

async function loadProfile() {
  setMsg("", "");
  loadingProfile.value = true;
  try {
    const { data } = await usersApi.me();

    // Map backend fields -> form fields (adjust names to your backend response)
    firstName.value = data.firstName ?? "";
    lastName.value = data.lastName ?? "";
    email.value = data.email ?? "";
    phone.value = data.phone ?? "";
    street.value = data.street ?? "";
    zip.value = data.postalCode ?? data.zip ?? "";
    city.value = data.city ?? "";
    country.value = data.country ?? "";
  } catch (err) {
    setMsg(
      err.response?.data?.message || "Could not load profile. Are you logged in?",
      ""
    );
  } finally {
    loadingProfile.value = false;
  }
}

onMounted(loadProfile);

const savePersonalDetails = async () => {
  setMsg("", "");
  savingProfile.value = true;

  try {
    // Send DTO-shaped update. Include only fields your backend allows to update.
    const payload = {
      firstName: firstName.value,
      lastName: lastName.value,
      email: email.value,
      phone: phone.value,          // only if backend supports it
      street: street.value,
      postalCode: zip.value,       // backend DTO uses postalCode
      city: city.value,
      country: country.value,
    };

    const { data } = await usersApi.updateMe(payload);

    // optionally re-sync with returned data
    firstName.value = data.firstName ?? firstName.value;
    lastName.value = data.lastName ?? lastName.value;
    email.value = data.email ?? email.value;

    setMsg("", "Personal details saved.");
  } catch (err) {
    setMsg(err.response?.data?.message || "Saving failed.", "");
  } finally {
    savingProfile.value = false;
  }
};

const changePassword = async () => {
  setMsg("", "");

  if (newPassword.value !== confirmPassword.value) {
    setMsg("New password and confirmation do not match.", "");
    return;
  }

  changingPw.value = true;
  try {
    await usersApi.changePassword({
      currentPassword: currentPassword.value,
      newPassword: newPassword.value,
    });

    currentPassword.value = "";
    newPassword.value = "";
    confirmPassword.value = "";

    setMsg("", "Password updated.");
  } catch (err) {
    setMsg(err.response?.data?.message || "Password update failed.", "");
  } finally {
    changingPw.value = false;
  }
};

// keep your payment dummy handlers as-is for now
const savePaymentMethod = () => alert("Payment method saved (demo).");
const clearPaymentMethod = () => {
  cardBrand.value = "No card on file";
  cardLast4.value = "----";
  cardExpiry.value = "--/--";
};
</script>


<style scoped>
.profile-card {
  background-color: rgba(255, 255, 255, 0.95);
  border-radius: 1rem;
  box-shadow: 0 0.75rem 2rem rgba(0, 0, 0, 0.25);
}

/* Tabs using theme colors (vars defined in theme.css) */
.profile-tabs .nav-link {
  color: #4b2b30;
  border-radius: 999px;
  padding-inline: 1.25rem;
}

.profile-tabs .nav-link.active {
  background-color: var(--taiyaki-primary);
  color: #fff;
}

/* Small highlight box for the current payment method */
.payment-box {
  padding: 0.75rem 1rem;
  border-radius: 0.75rem;
  background-color: #fff7f7;
  border: 1px solid rgba(0, 0, 0, 0.05);
}
</style>
