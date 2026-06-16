<template>
  <span class="status-badge" :class="`status-badge--${status.toLowerCase()}`">
    {{ label }}
  </span>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  status: {
    type: String,
    required: true,
  },
})

const label = computed(() => {
  const lower = props.status.toLowerCase()
  return lower.charAt(0).toUpperCase() + lower.slice(1)
})
</script>

