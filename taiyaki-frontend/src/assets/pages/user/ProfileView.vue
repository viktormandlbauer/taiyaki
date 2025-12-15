<!-- ProfileView.vue -->
<template>
  <div class="container mt-4 mb-5">
    <div class="card profile-card">
      <div class="card-body">
        <h1 class="h4 mb-4">Your Profile</h1>

        <!-- Loading overlay (shows while backend data is being fetched) -->
        <div v-if="loadingProfile" class="loading-overlay" aria-live="polite" aria-busy="true">
          <div class="text-center">
            <div class="spinner-border" role="status">
              <span class="visually-hidden">Loading...</span>
            </div>
            <div class="mt-2 small text-muted">Loading profile…</div>
          </div>
        </div>

        <!-- Content (dimmed/disabled while loading) -->
        <div :class="{ 'content-disabled': loadingProfile }">
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

            <!-- Read-only account info (top, not inputs) -->
            <div class="account-summary mb-4">
              <div class="row g-3">
                <div class="col-md-6">
                  <div class="text-muted small">Username</div>
                  <div class="fw-semibold">{{ username || "—" }}</div>
                </div>
                <div class="col-md-6">
                  <div class="text-muted small">Email</div>
                  <div class="fw-semibold">{{ email || "—" }}</div>
                </div>
              </div>
            </div>

            <form @submit.prevent="savePersonalDetails">
              <div class="row">
                <!-- Salutation -->
                <div class="col-md-4 mb-3">
                  <label for="salutation" class="form-label">Salutation</label>
                  <select id="salutation" v-model="salutation" class="form-select">
                    <option value="">Select…</option>
                    <option value="MALE">Mr</option>
                    <option value="FEMALE">Ms</option>
                    <option value="DIVERSE">Diverse</option>
                  </select>
                </div>

                <div class="col-md-4 mb-3">
                  <label for="firstName" class="form-label">First name</label>
                  <input
                    id="firstName"
                    v-model="firstName"
                    type="text"
                    class="form-control"
                    placeholder="Viktor"
                  />
                </div>

                <div class="col-md-4 mb-3">
                  <label for="lastName" class="form-label">Last name</label>
                  <input
                    id="lastName"
                    v-model="lastName"
                    type="text"
                    class="form-control"
                    placeholder="Mandlbauer"
                  />
                </div>
              </div>

              <div class="mb-3">
                <label for="street" class="form-label">Street &amp; house number</label>
                <input
                  id="street"
                  v-model="street"
                  type="text"
                  class="form-control"
                  placeholder="Westgasse 2"
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
                    placeholder="2201"
                  />
                </div>

                <div class="col-md-4 mb-3">
                  <label for="city" class="form-label">City</label>
                  <input
                    id="city"
                    v-model="city"
                    type="text"
                    class="form-control"
                    placeholder="Gerasdorf"
                  />
                </div>

                <div class="col-md-4 mb-3">
                  <label for="country" class="form-label">Country</label>
                  <input
                    id="country"
                    v-model="country"
                    type="text"
                    class="form-control"
                    placeholder="AT"
                  />
                </div>
              </div>

              <button type="submit" class="btn btn-primary mt-2" :disabled="savingProfile">
                <span v-if="savingProfile" class="spinner-border spinner-border-sm me-2"></span>
                Save changes
              </button>
            </form>
          </section>

          <!-- PAYMENT METHODS -->
          <section v-else-if="activeTab === 'payments'">
            <h2 class="h5 mb-3">Payment methods</h2>

            <div class="mb-3">
              <p class="text-muted small mb-1">Default payment method:</p>

              <div class="d-flex align-items-center justify-content-between payment-box">
                <div>
                  <div class="fw-semibold">
                    {{ cardBrand }} •••• {{ cardLast4 }}
                  </div>
                  <small class="text-muted">Expires {{ cardExpiry }}</small>
                </div>

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

              <button type="submit" class="btn btn-primary mt-2" :disabled="changingPw">
                <span v-if="changingPw" class="spinner-border spinner-border-sm me-2"></span>
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

    <!-- Error modal (auto hides) -->
    <div class="modal fade" ref="errorModalEl" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Something went wrong</h5>
            <button
              type="button"
              class="btn-close"
              aria-label="Close"
              @click="hideErrorModal"
            ></button>
          </div>

          <div class="modal-body">
            {{ profileError }}
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-outline-primary" @click="hideErrorModal">
              OK
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch, onBeforeUnmount } from "vue";
import { profile } from "@/api/users";
import { Modal } from "bootstrap";

// tabs
const activeTab = ref("details");

// loading + errors
const loadingProfile = ref(false);
const savingProfile = ref(false);
const changingPw = ref(false);

const profileError = ref("");
const profileSuccess = ref("");

// personal details
const salutation = ref("");
const firstName = ref("");
const lastName = ref("");
const username = ref("");
const email = ref("");
const street = ref("");
const zip = ref("");
const city = ref("");
const country = ref("");

// payment methods (demo)
const cardBrand = ref("Visa");
const cardLast4 = ref("1234");
const cardExpiry = ref("08/28");
const cardName = ref("");
const cardNumber = ref("");
const newCardExpiry = ref("");
const newCardCvc = ref("");

// password
const currentPassword = ref("");
const newPassword = ref("");
const confirmPassword = ref("");

function setMsg(err = "", ok = "") {
  profileError.value = err;
  profileSuccess.value = ok;
}

const errorModalEl = ref(null);
let errorModalInstance = null;
let errorModalTimer = null;
const AUTO_HIDE_MS = 4000;

function ensureModal() {
  if (!errorModalInstance && errorModalEl.value) {
    errorModalInstance = new Modal(errorModalEl.value, {
      backdrop: true,
      keyboard: true,
      focus: true,
    });
  }
}

function showErrorModal() {
  ensureModal();
  errorModalInstance?.show();

  clearTimeout(errorModalTimer);
  errorModalTimer = setTimeout(() => {
    hideErrorModal();
  }, AUTO_HIDE_MS);
}

function hideErrorModal() {
  errorModalInstance?.hide();
  clearTimeout(errorModalTimer);
  errorModalTimer = null;
  profileError.value = "";
}

watch(profileError, (val) => {
  if (val) showErrorModal();
});

onBeforeUnmount(() => {
  clearTimeout(errorModalTimer);
  errorModalTimer = null;
  errorModalInstance?.dispose?.();
  errorModalInstance = null;
});

/** ===== Load profile ===== */
async function loadProfile() {
  setMsg("", "");
  loadingProfile.value = true;

  try {
    const data = await profile.me();

    salutation.value = data.salutation ?? "";
    firstName.value = data.firstName ?? "";
    lastName.value = data.lastName ?? "";
    username.value = data.username ?? "";
    email.value = data.email ?? "";
    street.value = data.street ?? "";
    zip.value = data.postalCode ?? data.zip ?? "";
    city.value = data.city ?? "";
    country.value = data.country ?? "";
  } catch (err) {
    setMsg(err.response?.data?.message || "Could not load profile. Are you logged in?", "");
  } finally {
    loadingProfile.value = false;
  }
}

onMounted(loadProfile);

const savePersonalDetails = async () => {
  setMsg("", "");
  savingProfile.value = true;

  try {
    const payload = {
      salutation: salutation.value,
      firstName: firstName.value,
      lastName: lastName.value,
      street: street.value,
      postalCode: zip.value,
      city: city.value,
      country: country.value,
    };

    const res = await profile.updateMe(payload);
    const data = res?.data?.data ?? res?.data ?? res;

    salutation.value = data.salutation ?? salutation.value;
    firstName.value = data.firstName ?? firstName.value;
    lastName.value = data.lastName ?? lastName.value;
    street.value = data.street ?? street.value;
    zip.value = (data.postalCode ?? data.zip) ?? zip.value;
    city.value = data.city ?? city.value;
    country.value = data.country ?? country.value;

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
    await profile.changePassword({
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

// demo payment handlers
const savePaymentMethod = () => setMsg("Payment method save is demo-only (no endpoint).", "");
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

/* Make overlay position relative to card body */
.profile-card .card-body {
  position: relative;
}

/* Loading overlay */
.loading-overlay {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.75);
  backdrop-filter: blur(2px);
  border-radius: 1rem;
  z-index: 10;
}

/* Disable interaction + dim content while loading */
.content-disabled {
  pointer-events: none;
  opacity: 0.6;
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

/* Account summary box (username/email) */
.account-summary {
  padding: 0.9rem 1rem;
  border-radius: 0.9rem;
  background-color: #fff7f7;
  border: 1px solid rgba(0, 0, 0, 0.05);
}

/* Small highlight box for the current payment method */
.payment-box {
  padding: 0.75rem 1rem;
  border-radius: 0.75rem;
  background-color: #fff7f7;
  border: 1px solid rgba(0, 0, 0, 0.05);
}
</style>
