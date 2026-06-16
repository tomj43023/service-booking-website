<template>
  <section class="page-hero compact-hero">
    <p class="eyebrow">Services</p>
    <h1>Cleaning packages with practical scope and transparent prices.</h1>
    <p>Browse the services, then jump directly into booking with your preferred option selected.</p>
  </section>

  <section class="section">
    <div v-if="store.loadingServices" class="notice">Loading services...</div>
    <div v-else-if="store.error" class="notice notice-error">{{ store.error }}</div>
    <div v-else class="service-grid">
      <ServiceCard v-for="service in store.services" :key="service.id" :service="service" />
    </div>
  </section>
</template>

<script setup>
import { onMounted } from 'vue'
import ServiceCard from '@/components/ServiceCard.vue'
import { useBookingStore } from '@/stores/bookingStore'

const store = useBookingStore()

onMounted(() => {
  if (!store.services.length) {
    store.fetchServices().catch(() => {})
  }
})
</script>

