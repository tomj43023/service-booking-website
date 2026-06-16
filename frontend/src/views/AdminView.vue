<template>
  <section class="page-hero compact-hero admin-hero">
    <p class="eyebrow">Admin demo</p>
    <h1>Manage appointment requests clearly.</h1>
    <p>This portfolio admin view shows booking data, status controls, and delete actions without authentication.</p>
  </section>

  <section class="section admin-section">
    <div class="admin-toolbar">
      <div>
        <h2>Appointments</h2>
        <p>{{ store.appointments.length }} records loaded</p>
      </div>
      <button class="button button-secondary" type="button" @click="loadAppointments">
        Refresh
      </button>
    </div>

    <div v-if="store.loadingAppointments" class="notice">Loading appointments...</div>
    <div v-else-if="store.error" class="notice notice-error">{{ store.error }}</div>
    <div v-else class="appointment-list">
      <article v-for="appointment in store.appointments" :key="appointment.id" class="appointment-card">
        <div>
          <div class="appointment-title">
            <h3>{{ appointment.customerName }}</h3>
            <StatusBadge :status="appointment.status" />
          </div>
          <p>{{ appointment.serviceName }}</p>
          <p class="appointment-meta">
            {{ formatDate(appointment.appointmentDate) }} at {{ appointment.appointmentTime }} |
            {{ appointment.email }} | {{ appointment.phone }}
          </p>
          <p v-if="appointment.notes" class="appointment-notes">{{ appointment.notes }}</p>
        </div>
        <div class="appointment-actions">
          <select :value="appointment.status" @change="updateStatus(appointment.id, $event.target.value)">
            <option value="PENDING">Pending</option>
            <option value="CONFIRMED">Confirmed</option>
            <option value="CANCELLED">Cancelled</option>
          </select>
          <button class="icon-button danger" type="button" title="Delete appointment" @click="deleteAppointment(appointment)">
            Delete
          </button>
        </div>
      </article>
    </div>
  </section>
</template>

<script setup>
import { onMounted } from 'vue'
import StatusBadge from '@/components/StatusBadge.vue'
import { useBookingStore } from '@/stores/bookingStore'

const store = useBookingStore()

onMounted(loadAppointments)

function loadAppointments() {
  store.fetchAppointments().catch(() => {})
}

function updateStatus(id, status) {
  store.updateAppointmentStatus(id, status).catch((error) => {
    store.error = error.message
  })
}

function deleteAppointment(appointment) {
  const confirmed = window.confirm(`Delete appointment for ${appointment.customerName}?`)
  if (!confirmed) return
  store.deleteAppointment(appointment.id).catch((error) => {
    store.error = error.message
  })
}

function formatDate(value) {
  return new Intl.DateTimeFormat('en-US', { month: 'short', day: 'numeric', year: 'numeric' }).format(new Date(`${value}T00:00:00`))
}
</script>

