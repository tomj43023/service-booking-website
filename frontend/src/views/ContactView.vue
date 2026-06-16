<template>
  <section class="page-hero compact-hero">
    <p class="eyebrow">Contact</p>
    <h1>Questions before booking? Send a message.</h1>
    <p>Use this simple contact form for quotes, access notes, or recurring cleaning requests.</p>
  </section>

  <section class="section contact-layout">
    <form class="form-panel" @submit.prevent="submitContact">
      <label>
        <span>Name</span>
        <input v-model.trim="form.name" type="text" autocomplete="name" />
        <small v-if="errors.name">{{ errors.name }}</small>
      </label>
      <label>
        <span>Email</span>
        <input v-model.trim="form.email" type="email" autocomplete="email" />
        <small v-if="errors.email">{{ errors.email }}</small>
      </label>
      <label>
        <span>Subject</span>
        <input v-model.trim="form.subject" type="text" />
        <small v-if="errors.subject">{{ errors.subject }}</small>
      </label>
      <label>
        <span>Message</span>
        <textarea v-model.trim="form.message" rows="6"></textarea>
        <small v-if="errors.message">{{ errors.message }}</small>
      </label>

      <div v-if="submitError" class="notice notice-error">{{ submitError }}</div>
      <div v-if="success" class="notice notice-success">Thanks, {{ success.name }}. Your message was received.</div>
      <button class="button" type="submit" :disabled="submitting">
        {{ submitting ? 'Sending...' : 'Send Message' }}
      </button>
    </form>

    <aside class="contact-panel">
      <h2>BrightCare Cleaning</h2>
      <p>Small team, clear communication, and practical scheduling for local homes and offices.</p>
      <div class="contact-detail">
        <span>Email</span>
        <strong>hello@brightcare.example</strong>
      </div>
      <div class="contact-detail">
        <span>Phone</span>
        <strong>(555) 013-4400</strong>
      </div>
      <div class="contact-detail">
        <span>Hours</span>
        <strong>Mon-Sat, 8:00 AM-7:00 PM</strong>
      </div>
    </aside>
  </section>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { api } from '@/services/api'

const submitting = ref(false)
const submitError = ref('')
const success = ref(null)
const errors = reactive({})

const form = reactive({
  name: '',
  email: '',
  subject: '',
  message: '',
})

function validate() {
  Object.keys(errors).forEach((key) => delete errors[key])
  if (!form.name) errors.name = 'Name is required.'
  if (!/^\S+@\S+\.\S+$/.test(form.email)) errors.email = 'Enter a valid email.'
  if (!form.subject) errors.subject = 'Subject is required.'
  if (form.message.length < 10) errors.message = 'Message must be at least 10 characters.'
  return Object.keys(errors).length === 0
}

async function submitContact() {
  submitError.value = ''
  success.value = null
  if (!validate()) return
  submitting.value = true
  try {
    success.value = await api.sendContactMessage({ ...form })
    form.name = ''
    form.email = ''
    form.subject = ''
    form.message = ''
  } catch (error) {
    Object.assign(errors, error.fieldErrors)
    submitError.value = error.message
  } finally {
    submitting.value = false
  }
}
</script>

