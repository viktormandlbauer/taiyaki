<template>
  <BaseInput
    :label="label"
    :id="id"
    :type="type"
    :placeholder="placeholder"
    v-model="model"
    :autocomplete="autocomplete"
    :error="error"
  />
</template>

<script setup>
import { computed } from 'vue';
import BaseInput from '../atoms/BaseInput.vue';

const props = defineProps({
  modelValue: String,
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
