<template>
  <form @submit.prevent="onSubmit" novalidate>
    <div class="mb-3">
      <label for="loginIdentifier" class="form-label">Email or username</label>
      <input
        id="loginIdentifier"
        type="text"
        class="form-control"
        v-model.trim="model.identifier"
        :class="{
          'is-invalid': touched.identifier && errors.identifier,
          'is-valid': touched.identifier && !errors.identifier && model.identifier
        }"
        @blur="touch('identifier')"
        autocomplete="username"
      />
      <div v-if="touched.identifier && errors.identifier" class="invalid-feedback">
        {{ errors.identifier }}
      </div>
      <div class="form-text">You can sign in using your email address or username.</div>
    </div>

    <div class="mb-3">
      <label for="loginPassword" class="form-label">Password</label>
      <input
        id="loginPassword"
        type="password"
        class="form-control"
        v-model="model.password"
        :class="{
          'is-invalid': touched.password && errors.password,
          'is-valid': touched.password && !errors.password && model.password
        }"
        @blur="touch('password')"
        autocomplete="current-password"
      />
      <div v-if="touched.password && errors.password" class="invalid-feedback">
        {{ errors.password }}
      </div>
    </div>

    <div v-if="apiError" class="alert alert-danger py-2" role="alert">
      {{ apiError }}
    </div>

    <button type="submit" class="btn btn-primary w-100" :disabled="loading">
      {{ loading ? "Signing in..." : "Login" }}
    </button>
  </form>
</template>

<script setup>
import { reactive } from "vue";
import * as yup from "yup";

const props = defineProps({
  loading: { type: Boolean, default: false },
  apiError: { type: String, default: "" },
  initial: {
    type: Object,
    default: () => ({ identifier: "", password: "" }),
  },
});

const emit = defineEmits(["submit"]); // emits valid {identifier, password}

const model = reactive({
  identifier: props.initial.identifier ?? "",
  password: props.initial.password ?? "",
});

const errors = reactive({ identifier: "", password: "" });
const touched = reactive({ identifier: false, password: false });

const schema = yup.object({
  identifier: yup
    .string()
    .trim()
    .required("Email or username is required.")
    .max(100, "Please keep this under 100 characters."),
  password: yup.string().required("Password is required."),
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
  errors.identifier = "";
  errors.password = "";
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

async function onSubmit() {
  touched.identifier = true;
  touched.password = true;

  const ok = await validateAll();
  if (!ok) return;

  emit("submit", { identifier: model.identifier, password: model.password });
}
</script>
