<template>
  <form @submit.prevent="onSubmit" novalidate>
    <!-- First name -->
    <div class="mb-3">
      <label for="firstName" class="form-label">First name</label>
      <input
        id="firstName"
        type="text"
        maxlength="50"
        class="form-control"
        v-model.trim="model.firstName"
        :class="{
          'is-invalid': touched.firstName && errors.firstName,
          'is-valid': touched.firstName && !errors.firstName && model.firstName
        }"
        @blur="touch('firstName')"
      />
      <div v-if="touched.firstName && errors.firstName" class="invalid-feedback">
        {{ errors.firstName }}
      </div>
    </div>

    <!-- Last name -->
    <div class="mb-3">
      <label for="lastName" class="form-label">Last name</label>
      <input
        id="lastName"
        type="text"
        maxlength="50"
        class="form-control"
        v-model.trim="model.lastName"
        :class="{
          'is-invalid': touched.lastName && errors.lastName,
          'is-valid': touched.lastName && !errors.lastName && model.lastName
        }"
        @blur="touch('lastName')"
      />
      <div v-if="touched.lastName && errors.lastName" class="invalid-feedback">
        {{ errors.lastName }}
      </div>
    </div>

    <!-- Email -->
    <div class="mb-3">
      <label for="registerEmail" class="form-label">Email address</label>
      <input
        id="registerEmail"
        type="email"
        maxlength="100"
        class="form-control"
        v-model.trim="model.email"
        :class="{
          'is-invalid': touched.email && errors.email,
          'is-valid': touched.email && !errors.email && model.email
        }"
        @blur="touch('email')"
      />
      <div v-if="touched.email && errors.email" class="invalid-feedback">
        {{ errors.email }}
      </div>
    </div>

    <!-- Username -->
    <div class="mb-3">
      <label for="registerUsername" class="form-label">Username</label>
      <input
        id="registerUsername"
        type="text"
        maxlength="30"
        class="form-control"
        v-model.trim="model.username"
        :class="{
          'is-invalid': touched.username && errors.username,
          'is-valid': touched.username && !errors.username && model.username
        }"
        @blur="touch('username')"
      />
      <div v-if="touched.username && errors.username" class="invalid-feedback">
        {{ errors.username }}
      </div>
    </div>

    <!-- Password -->
    <div class="mb-3">
      <label for="registerPassword" class="form-label">Password</label>
      <input
        id="registerPassword"
        type="password"
        maxlength="100"
        class="form-control"
        v-model="model.password"
        :class="{
          'is-invalid': touched.password && errors.password,
          'is-valid': touched.password && !errors.password && model.password
        }"
        @blur="touch('password')"
      />
      <div v-if="touched.password && errors.password" class="invalid-feedback">
        {{ errors.password }}
      </div>
      <div class="form-text">Max 100 characters.</div>
    </div>

    <!-- Repeat password (client-only) -->
    <div class="mb-3">
      <label for="registerConfirm" class="form-label">Repeat password</label>
      <input
        id="registerConfirm"
        type="password"
        class="form-control"
        v-model="model.passwordRepeat"
        :class="{
          'is-invalid': touched.passwordRepeat && errors.passwordRepeat,
          'is-valid': touched.passwordRepeat && !errors.passwordRepeat && model.passwordRepeat
        }"
        @blur="touch('passwordRepeat')"
      />
      <div v-if="touched.passwordRepeat && errors.passwordRepeat" class="invalid-feedback">
        {{ errors.passwordRepeat }}
      </div>
    </div>

    <!-- Street -->
    <div class="mb-3">
      <label for="street" class="form-label">Street</label>
      <input
        id="street"
        type="text"
        maxlength="100"
        class="form-control"
        v-model.trim="model.street"
        :class="{
          'is-invalid': touched.street && errors.street,
          'is-valid': touched.street && !errors.street && model.street
        }"
        @blur="touch('street')"
      />
      <div v-if="touched.street && errors.street" class="invalid-feedback">
        {{ errors.street }}
      </div>
    </div>

    <!-- City -->
    <div class="mb-3">
      <label for="city" class="form-label">City</label>
      <input
        id="city"
        type="text"
        maxlength="50"
        class="form-control"
        v-model.trim="model.city"
        :class="{
          'is-invalid': touched.city && errors.city,
          'is-valid': touched.city && !errors.city && model.city
        }"
        @blur="touch('city')"
      />
      <div v-if="touched.city && errors.city" class="invalid-feedback">
        {{ errors.city }}
      </div>
    </div>

    <!-- Postal code -->
    <div class="mb-3">
      <label for="postalCode" class="form-label">Postal code</label>
      <input
        id="postalCode"
        type="text"
        maxlength="10"
        class="form-control"
        v-model.trim="model.postalCode"
        :class="{
          'is-invalid': touched.postalCode && errors.postalCode,
          'is-valid': touched.postalCode && !errors.postalCode && model.postalCode
        }"
        @blur="touch('postalCode')"
      />
      <div v-if="touched.postalCode && errors.postalCode" class="invalid-feedback">
        {{ errors.postalCode }}
      </div>
    </div>

    <!-- Country -->
    <div class="mb-3">
      <label for="registerCountry" class="form-label">Country</label>
      <select
        id="registerCountry"
        class="form-select"
        v-model="model.country"
        :class="{
          'is-invalid': touched.country && errors.country,
          'is-valid': touched.country && !errors.country && model.country
        }"
        @blur="touch('country')"
        @change="touch('country')"
      >
        <option disabled value="">Select country</option>

        <optgroup label="DACH">
          <option v-for="c in dachCountries" :key="c.code" :value="c.code">
            {{ c.name }}
          </option>
        </optgroup>

        <optgroup label="All countries">
          <option v-for="c in otherCountries" :key="c.code" :value="c.code">
            {{ c.name }}
          </option>
        </optgroup>
      </select>

      <div v-if="touched.country && errors.country" class="invalid-feedback">
        {{ errors.country }}
      </div>
    </div>

    <div v-if="apiError" class="alert alert-danger py-2" role="alert">
      {{ apiError }}
    </div>

    <button type="submit" class="btn btn-primary w-100" :disabled="loading">
      {{ loading ? "Creating..." : "Register" }}
    </button>
  </form>
</template>

<script setup>
import { computed, reactive, watch } from "vue";
import * as yup from "yup";

import isoCountries from "i18n-iso-countries";
import enLocale from "i18n-iso-countries/langs/en.json";
isoCountries.registerLocale(enLocale);

const props = defineProps({
  loading: { type: Boolean, default: false },
  apiError: { type: String, default: "" },
  initial: {
    type: Object,
    default: () => ({
      firstName: "",
      lastName: "",
      username: "",
      password: "",
      passwordRepeat: "",
      email: "",
      street: "",
      city: "",
      postalCode: "",
      country: "",
    }),
  },
});

const emit = defineEmits(["submit"]);

const model = reactive({
  firstName: props.initial.firstName ?? "",
  lastName: props.initial.lastName ?? "",
  username: props.initial.username ?? "",
  password: props.initial.password ?? "",
  passwordRepeat: props.initial.passwordRepeat ?? "",
  email: props.initial.email ?? "",
  street: props.initial.street ?? "",
  city: props.initial.city ?? "",
  postalCode: props.initial.postalCode ?? "",
  country: props.initial.country ?? "",
});

// countries
const DACH = ["DE", "AT", "CH"];
const countryList = computed(() => {
  const names = isoCountries.getNames("en", { select: "official" });
  return Object.entries(names)
    .map(([code, name]) => ({ code, name }))
    .sort((a, b) => a.name.localeCompare(b.name));
});
const dachCountries = computed(() => {
  const map = new Map(countryList.value.map((c) => [c.code, c.name]));
  return DACH.filter((code) => map.has(code)).map((code) => ({ code, name: map.get(code) }));
});
const otherCountries = computed(() => countryList.value.filter((c) => !DACH.includes(c.code)));

const errors = reactive({
  firstName: "",
  lastName: "",
  username: "",
  password: "",
  passwordRepeat: "",
  email: "",
  street: "",
  city: "",
  postalCode: "",
  country: "",
});

const touched = reactive({
  firstName: false,
  lastName: false,
  username: false,
  password: false,
  passwordRepeat: false,
  email: false,
  street: false,
  city: false,
  postalCode: false,
  country: false,
});

const schema = yup.object({
  firstName: yup.string().trim().required("First name is required.").max(50, "Max 50 characters."),
  lastName: yup.string().trim().required("Last name is required.").max(50, "Max 50 characters."),
  username: yup.string().trim().required("Username is required.").max(30, "Max 30 characters."),
  password: yup.string().required("Password is required.").max(100, "Max 100 characters."),
  passwordRepeat: yup
    .string()
    .required("Please repeat your password.")
    .oneOf([yup.ref("password")], "Passwords do not match."),
  email: yup
    .string()
    .trim()
    .required("Email is required.")
    .email("Please enter a valid email address.")
    .max(100, "Max 100 characters."),
  street: yup.string().trim().required("Street is required.").max(100, "Max 100 characters."),
  city: yup.string().trim().required("City is required.").max(50, "Max 50 characters."),
  postalCode: yup.string().trim().required("Postal code is required.").max(10, "Max 10 characters."),
  country: yup
    .string()
    .required("Please select a country.")
    .max(50, "Max 50 characters.")
    .test("is-known-country", "Please select a valid country.", (code) => !!code && isoCountries.isValid(code)),
});

function touch(field) {
  touched[field] = true;
  validateField(field);
}

async function validateField(field) {
  errors[field] = "";
  try {
    await schema.validateAt(field, model);
  } catch (e) {
    errors[field] = e.message || "Invalid value.";
  }
}

async function validateAll() {
  Object.keys(errors).forEach((k) => (errors[k] = ""));
  try {
    await schema.validate(model, { abortEarly: false });
    return true;
  } catch (e) {
    if (e?.inner?.length) {
      for (const err of e.inner) {
        if (err.path && !errors[err.path]) errors[err.path] = err.message;
      }
    }
    return false;
  }
}

// revalidate confirm if password changes and confirm already touched
watch(
  () => model.password,
  () => {
    if (touched.passwordRepeat) validateField("passwordRepeat");
  }
);

async function onSubmit() {
  Object.keys(touched).forEach((k) => (touched[k] = true));
  const ok = await validateAll();
  if (!ok) return;

  // IMPORTANT: emit DTO-shaped payload (no passwordRepeat)
  emit("submit", {
    firstName: model.firstName,
    lastName: model.lastName,
    username: model.username,
    password: model.password,
    email: model.email,
    street: model.street,
    city: model.city,
    postalCode: model.postalCode,
    country: model.country,
  });
}
</script>
