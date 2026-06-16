const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api'

async function request(path, options = {}) {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
    ...options,
  })

  if (response.status === 204) {
    return null
  }

  const data = await response.json().catch(() => null)

  if (!response.ok) {
    const error = new Error(data?.message || 'Request failed')
    error.status = response.status
    error.fieldErrors = data?.fieldErrors || {}
    throw error
  }

  return data
}

export const api = {
  getServices() {
    return request('/services')
  },
  getAppointments() {
    return request('/appointments')
  },
  createAppointment(payload) {
    return request('/appointments', {
      method: 'POST',
      body: JSON.stringify(payload),
    })
  },
  updateAppointmentStatus(id, status) {
    return request(`/appointments/${id}/status`, {
      method: 'PATCH',
      body: JSON.stringify({ status }),
    })
  },
  deleteAppointment(id) {
    return request(`/appointments/${id}`, {
      method: 'DELETE',
    })
  },
  sendContactMessage(payload) {
    return request('/contact', {
      method: 'POST',
      body: JSON.stringify(payload),
    })
  },
}

