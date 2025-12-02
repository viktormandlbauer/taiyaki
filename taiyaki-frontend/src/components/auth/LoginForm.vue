<!-- src/components/auth/LoginForm.vue -->
<template>
  <form @submit.prevent="onSubmit" novalidate>
    <div class="mb-3">
      <label for="loginEmail" class="form-label">Email address</label>
      <input
        id="loginEmail"
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
  apiError: { type: String, default: "" }, // error coming from parent (API/auth)
  initial: {
    type: Object,
    default: () => ({ email: "", password: "" }),
  },
});

const emit = defineEmits(["submit"]); // emits valid {email, password}

const model = reactive({
  email: props.initial.email ?? "",
  password: props.initial.password ?? "",
});

const errors = reactive({ email: "", password: "" });
const touched = reactive({ email: false, password: false });

const schema = yup.object({
  email: yup.string().trim().required("Email is required.").email("Please enter a valid email address."),
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
  errors.email = "";
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
  touched.email = true;
  touched.password = true;

  const ok = await validateAll();
  if (!ok) return;

  emit("submit", { email: model.email, password: model.password });
}
</script>
