<template>
  <section class="page-hero compact-hero">
    <p class="eyebrow">Book online</p>
    <h1>Reserve a cleaning appointment in a few steps.</h1>
    <p>New requests start as Pending, then the admin team can confirm or cancel from the dashboard.</p>
  </section>

  <section class="section booking-layout">
    <form class="form-panel" @submit.prevent="submitBooking">
      <div class="form-grid">
        <label>
          <span>Name</span>
          <input v-model.trim="form.customerName" type="text" autocomplete="name" />
          <small v-if="errors.customerName">{{ errors.customerName }}</small>
        </label>

        <label>
          <span>Email</span>
          <input v-model.trim="form.email" type="email" autocomplete="email" />
          <small v-if="errors.email">{{ errors.email }}</small>
        </label>

        <label>
          <span>Phone</span>
          <input v-model.trim="form.phone" type="tel" autocomplete="tel" />
          <small v-if="errors.phone">{{ errors.phone }}</small>
        </label>

        <label>
          <span>Service</span>
          <select v-model="form.serviceId">
            <option value="">Choose a service</option>
            <option v-for="service in store.services" :key="service.id" :value="service.id">
              {{ service.name }}
            </option>
          </select>
          <small v-if="errors.serviceId">{{ errors.serviceId }}</small>
        </label>

        <label>
          <span>Date</span>
          <input v-model="form.appointmentDate" type="date" :min="today" />
          <small v-if="errors.appointmentDate">{{ errors.appointmentDate }}</small>
        </label>

        <label>
          <span>Time</span>
          <input v-model="form.appointmentTime" type="time" min="08:00" max="19:00" />
          <small v-if="errors.appointmentTime">{{ errors.appointmentTime }}</small>
        </label>
      </div>

      <label>
        <span>Notes</span>
        <textarea v-model.trim="form.notes" rows="4" placeholder="Access details, priorities, pets, or parking notes"></textarea>
        <small v-if="errors.notes">{{ errors.notes }}</small>
      </label>

      <div v-if="submitError" class="notice notice-error">{{ submitError }}</div>
      <button class="button" type="submit" :disabled="store.saving">
        {{ store.saving ? 'Submitting...' : 'Submit Appointment' }}
      </button>
    </form>

    <aside class="booking-summary">
      <h2>{{ success ? 'Request received' : 'Appointment summary' }}</h2>
      <div v-if="success" class="success-panel">
        <StatusBadge :status="success.status" />
        <p>
          {{ success.customerName }} booked {{ success.serviceName }} for
          {{ formatDate(success.appointmentDate) }} at {{ success.appointmentTime }}.
        </p>
      </div>
      <div v-else>
        <p class="summary-line"><span>Service</span><strong>{{ selectedService?.name || 'Not selected' }}</strong></p>
        <p class="summary-line"><span>Price</span><strong>{{ selectedService ? formatPrice(selectedService.price) : '-' }}</strong></p>
        <p class="summary-line"><span>Duration</span><strong>{{ selectedService ? formatDuration(selectedService.durationMinutes) : '-' }}</strong></p>
        <p class="summary-line"><span>Status</span><strong>Pending after submit</strong></p>
      </div>
    </aside>
  </section>
</template>

<script setup>
import { computed, onMounted, reactive, ref, watch } from 'vue'
import { useRoute } from 'vue-router'
import StatusBadge from '@/components/StatusBadge.vue'
import { useBookingStore } from '@/stores/bookingStore'

const route = useRoute()
const store = useBookingStore()
const today = new Date().toISOString().slice(0, 10)
const submitError = ref('')
const success = ref(null)
const errors = reactive({})

const form = reactive({
  customerName: '',
  email: '',
  phone: '',
  serviceId: '',
  appointmentDate: '',
  appointmentTime: '',
  notes: '',
})

const selectedService = computed(() => store.serviceById(form.serviceId))

onMounted(async () => {
  if (!store.services.length) {
    await store.fetchServices().catch(() => {})
  }
  if (route.query.service) {
    form.serviceId = Number(route.query.service)
  }
})

watch(
  () => route.query.service,
  (serviceId) => {
    if (serviceId) {
      form.serviceId = Number(serviceId)
    }
  },
)

function validate() {
  Object.keys(errors).forEach((key) => delete errors[key])
  if (!form.customerName) errors.customerName = 'Name is required.'
  if (!/^\S+@\S+\.\S+$/.test(form.email)) errors.email = 'Enter a valid email.'
  if (!/^[0-9+()\-\s]{7,24}$/.test(form.phone)) errors.phone = 'Enter a valid phone number.'
  if (!form.serviceId) errors.serviceId = 'Choose a service.'
  if (!form.appointmentDate) errors.appointmentDate = 'Choose a date.'
  if (form.appointmentDate && form.appointmentDate < today) errors.appointmentDate = 'Date cannot be in the past.'
  if (!form.appointmentTime) errors.appointmentTime = 'Choose a time.'
  return Object.keys(errors).length === 0
}

async function submitBooking() {
  submitError.value = ''
  success.value = null
  if (!validate()) return

  try {
    success.value = await store.createAppointment({
      ...form,
      serviceId: Number(form.serviceId),
    })
    form.customerName = ''
    form.email = ''
    form.phone = ''
    form.notes = ''
  } catch (error) {
    Object.assign(errors, error.fieldErrors)
    submitError.value = error.message
  }
}

function formatPrice(value) {
  return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(value)
}

function formatDuration(minutes) {
  const hours = Math.floor(minutes / 60)
  const rest = minutes % 60
  return rest ? `${hours}h ${rest}m` : `${hours}h`
}

function formatDate(value) {
  return new Intl.DateTimeFormat('en-US', { month: 'short', day: 'numeric', year: 'numeric' }).format(new Date(`${value}T00:00:00`))
}
</script>

