<!-- src/components/auth/RegisterForm.vue -->
<template>
  <form @submit.prevent="onSubmit" novalidate>
    <!-- Salutation -->
    <div class="mb-3">
      <label for="salutation" class="form-label">Salutation (Anrede)</label>
      <select
        id="salutation"
        class="form-select"
        v-model="model.salutation"
        :class="{
          'is-invalid': touched.salutation && errors.salutation,
          'is-valid': touched.salutation && !errors.salutation && model.salutation
        }"
        @blur="touch('salutation')"
        @change="touch('salutation')"
      >
        <option disabled value="">Select salutation</option>
        <option value="male">male</option>
        <option value="female">female</option>
        <option value="other">other</option>
      </select>
      <div v-if="touched.salutation && errors.salutation" class="invalid-feedback">
        {{ errors.salutation }}
      </div>
    </div>

    <!-- Salutation other -->
    <div v-if="model.salutation === 'other'" class="mb-3">
      <label for="salutationOther" class="form-label">Please specify</label>
      <input
        id="salutationOther"
        type="text"
        maxlength="30"
        class="form-control"
        v-model.trim="model.salutationOther"
        :class="{
          'is-invalid': touched.salutationOther && errors.salutationOther,
          'is-valid': touched.salutationOther && !errors.salutationOther && model.salutationOther
        }"
        @blur="touch('salutationOther')"
      />
      <div class="form-text">Max 30 characters</div>
      <div v-if="touched.salutationOther && errors.salutationOther" class="invalid-feedback">
        {{ errors.salutationOther }}
      </div>
    </div>

    <!-- Email -->
    <div class="mb-3">
      <label for="registerEmail" class="form-label">Email address</label>
      <input
        id="registerEmail"
        type="email"
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
      <div class="form-text">At least 12 characters; must include upper/lowercase, number, and symbol.</div>
    </div>

    <!-- Repeat -->
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
  apiError: { type: String, default: "" }, // from parent
  initial: {
    type: Object,
    default: () => ({
      salutation: "",
      salutationOther: "",
      email: "",
      username: "",
      password: "",
      passwordRepeat: "",
      country: "",
    }),
  },
});

const emit = defineEmits(["submit"]); // emits valid payload

const model = reactive({
  salutation: props.initial.salutation ?? "",
  salutationOther: props.initial.salutationOther ?? "",
  email: props.initial.email ?? "",
  username: props.initial.username ?? "",
  password: props.initial.password ?? "",
  passwordRepeat: props.initial.passwordRepeat ?? "",
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
  salutation: "",
  salutationOther: "",
  email: "",
  username: "",
  password: "",
  passwordRepeat: "",
  country: "",
});

const touched = reactive({
  salutation: false,
  salutationOther: false,
  email: false,
  username: false,
  password: false,
  passwordRepeat: false,
  country: false,
});

const schema = yup.object({
  salutation: yup
    .string()
    .oneOf(["male", "female", "other"], "Please select a valid salutation.")
    .required("Please select a salutation."),
  salutationOther: yup
    .string()
    .trim()
    .max(30, "Please keep this under 30 characters.")
    .when("salutation", {
      is: "other",
      then: (s) => s.required("Please specify your salutation (max 30 characters)."),
      otherwise: (s) => s.notRequired(),
    }),
  email: yup.string().trim().required("Email is required.").email("Please enter a valid email address."),
  username: yup.string().trim().required("Username is required."),
  password: yup
    .string()
    .required("Password is required.")
    .min(12, "Password must be at least 12 characters long.")
    .matches(/[A-Z]/, "Password must include at least one uppercase letter.")
    .matches(/[a-z]/, "Password must include at least one lowercase letter.")
    .matches(/[0-9]/, "Password must include at least one number.")
    .matches(/[^A-Za-z0-9]/, "Password must include at least one symbol."),
  passwordRepeat: yup
    .string()
    .required("Please repeat your password.")
    .oneOf([yup.ref("password")], "Passwords do not match."),
  country: yup
    .string()
    .required("Please select a country.")
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

// keep conditional field clean
watch(
  () => model.salutation,
  (val) => {
    if (val !== "other") {
      model.salutationOther = "";
      errors.salutationOther = "";
      touched.salutationOther = false;
    }
  }
);

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

  emit("submit", { ...model, salutationOther: model.salutation === "other" ? model.salutationOther : "" });
}
</script>
