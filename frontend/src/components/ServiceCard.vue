<template>
  <article class="service-card">
    <div class="service-card__topline">
      <span>{{ service.category }}</span>
      <strong>{{ formatDuration(service.durationMinutes) }}</strong>
    </div>
    <h3>{{ service.name }}</h3>
    <p>{{ service.description }}</p>
    <p class="service-highlight">{{ service.highlight }}</p>
    <div class="service-card__footer">
      <strong class="price">{{ formatPrice(service.price) }}</strong>
      <RouterLink class="button button-small" :to="{ name: 'booking', query: { service: service.id } }">
        Book
      </RouterLink>
    </div>
  </article>
</template>

<script setup>
defineProps({
  service: {
    type: Object,
    required: true,
  },
})

function formatPrice(value) {
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    maximumFractionDigits: 0,
  }).format(value)
}

function formatDuration(minutes) {
  const hours = Math.floor(minutes / 60)
  const rest = minutes % 60
  return rest ? `${hours}h ${rest}m` : `${hours}h`
}
</script>

