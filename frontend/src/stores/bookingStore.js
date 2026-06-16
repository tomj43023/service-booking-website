import { defineStore } from 'pinia'
import { api } from '@/services/api'

export const useBookingStore = defineStore('booking', {
  state: () => ({
    services: [],
    appointments: [],
    loadingServices: false,
    loadingAppointments: false,
    saving: false,
    error: '',
  }),
  getters: {
    serviceById: (state) => (id) => state.services.find((service) => String(service.id) === String(id)),
  },
  actions: {
    async fetchServices() {
      this.loadingServices = true
      this.error = ''
      try {
        this.services = await api.getServices()
      } catch (error) {
        this.error = error.message
        throw error
      } finally {
        this.loadingServices = false
      }
    },
    async fetchAppointments() {
      this.loadingAppointments = true
      this.error = ''
      try {
        this.appointments = await api.getAppointments()
      } catch (error) {
        this.error = error.message
        throw error
      } finally {
        this.loadingAppointments = false
      }
    },
    async createAppointment(payload) {
      this.saving = true
      this.error = ''
      try {
        const appointment = await api.createAppointment(payload)
        this.appointments = [appointment, ...this.appointments]
        return appointment
      } catch (error) {
        this.error = error.message
        throw error
      } finally {
        this.saving = false
      }
    },
    async updateAppointmentStatus(id, status) {
      const updated = await api.updateAppointmentStatus(id, status)
      this.appointments = this.appointments.map((appointment) =>
        appointment.id === id ? updated : appointment,
      )
      return updated
    },
    async deleteAppointment(id) {
      await api.deleteAppointment(id)
      this.appointments = this.appointments.filter((appointment) => appointment.id !== id)
    },
  },
})

