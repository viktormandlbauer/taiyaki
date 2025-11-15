<template>
  <div class="mb-3">
    <label v-if="label" :for="id" class="form-label">
      {{ label }}
    </label>
    <input
      :id="id"
      :type="type"
      class="form-control"
      :placeholder="placeholder"
      v-model="model"
      :autocomplete="autocomplete"
    />
    <div v-if="error" class="text-danger small mt-1">
      {{ error }}
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  modelValue: [String, Number],
  label: String,
  id: String,
  type: { type: String, default: 'text' },
  placeholder: String,
  autocomplete: String,
  error: String
});

const emit = defineEmits(['update:modelValue']);

const model = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
});
</script>
