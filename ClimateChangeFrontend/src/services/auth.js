import api from './http-common'

function login(payload) {
  return api.post('/auth/login', payload)
}

function register(payload) {
  return api.post('/auth/register', payload)
}

function logout() {
  localStorage.removeItem('user')
  return api.post('/auth/logout')
}

export default { login, register, logout }
