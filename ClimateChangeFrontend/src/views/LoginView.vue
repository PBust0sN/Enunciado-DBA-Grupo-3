<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import auth from '../services/auth'

const router = useRouter()
const rut = ref('')
const password = ref('')
const message = ref(null)
const loading = ref(false)
const form = ref(null)

const rules = {
  required: v => !!v || 'Este campo es requerido',
  rut: v => /^[0-9]{7,8}-[0-9K]$/.test(v) || 'Formato RUT válido: 12345678-9',
  minLength: v => (v && v.length >= 8) || 'Mínimo 8 caracteres'
}

const submit = async () => {
  const { valid } = await form.value.validate()
  if (!valid) return
  
  message.value = null
  loading.value = true
  try {
    const res = await auth.login({ rut: rut.value, password: password.value })
    message.value = { type: 'success', text: 'Inicio de sesión exitoso' }
    localStorage.setItem('user', JSON.stringify(res.data))
    router.push({ name: 'home' })
    setTimeout(() => {
      window.location.reload();
    }, 500);
  } catch (err) {
    const text = err?.response?.data?.message || err.message || 'Login failed'
    message.value = { type: 'error', text }
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="login-bg">
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="6" lg="5">
          <v-card 
            class="login-card"
            variant="outlined"
            rounded="lg"
            elevation="8"
          >
            <div class="login-banner">
              <div class="login-overlay">
                <h2 class="text-h4 font-weight-bold text-white">Bienvenido</h2>
                <p class="text-body-1 text-white">Sistema de Monitoreo del Cambio Climático</p>
              </div>
            </div>

            <v-card-item class="pt-6">
              <v-card-title class="text-h5 text-primary mb-4">
                <v-icon icon="mdi-login" class="mr-2"></v-icon>
                Iniciar Sesión
              </v-card-title>

              <v-card-text>
                <v-form ref="form" @submit.prevent="submit" class="pt-2">
                  <v-text-field
                    v-model="rut"
                    :rules="[rules.required, rules.rut]"
                    label="RUT"
                    placeholder="12345678-9"
                    prepend-inner-icon="mdi-account-card-details"
                    variant="outlined"
                    density="comfortable"
                    color="primary"
                    bg-color="surface"
                    class="rounded-lg input-field"
                  ></v-text-field>

                  <v-text-field
                    v-model="password"
                    :rules="[rules.required, rules.minLength]"
                    label="Contraseña"
                    type="password"
                    prepend-inner-icon="mdi-lock"
                    variant="outlined"
                    density="comfortable"
                    hint="La contraseña debe tener al menos 8 caracteres"
                    persistent-hint
                    color="primary"
                    bg-color="surface"
                    class="rounded-lg input-field"
                  ></v-text-field>

                  <v-slide-y-transition>
                    <v-alert
                      v-if="message"
                      :type="message.type"
                      variant="tonal"
                      class="mt-4"
                      border="start"
                      density="comfortable"
                      closable
                      rounded="lg"
                    >
                      <div class="d-flex align-center">
                        <v-icon
                          :icon="message.type === 'success' ? 'mdi-check-circle' : 'mdi-alert-circle'"
                          start
                          class="mr-2"
                        ></v-icon>
                        {{ message.text }}
                      </div>
                    </v-alert>
                  </v-slide-y-transition>

                  <v-btn
                    class="mt-6 login-btn"
                    size="large"
                    block
                    type="submit"
                    :loading="loading"
                    :disabled="loading"
                    color="primary"
                    rounded="lg"
                    elevation="2"
                  >
                    <v-icon start icon="mdi-login-variant" class="mr-1"></v-icon>
                    {{ loading ? 'Iniciando sesión...' : 'Iniciar Sesión' }}
                  </v-btn>
                </v-form>
              </v-card-text>

              <v-card-text class="text-center pt-0">
                <div class="divider-container">
                  <v-divider thickness="2"></v-divider>
                </div>
                <p class="text-body-2 mt-4">
                  ¿No tienes una cuenta?
                  <v-btn
                    variant="text"
                    color="primary"
                    density="comfortable"
                    to="/register"
                    class="px-1 font-weight-bold text-decoration-underline"
                  >
                    Regístrate
                    <v-icon end icon="mdi-arrow-right" size="small"></v-icon>
                  </v-btn>
                </p>
              </v-card-text>
            </v-card-item>
          </v-card>
          
          <div class="text-center mt-4 text-caption text-disabled">
            &copy; 2025 Sistema de Monitoreo del Cambio Climático
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped>
.login-bg {
  min-height: 100vh;
  background: linear-gradient(135deg, rgba(240, 244, 247, 0.8) 0%, rgba(214, 234, 248, 0.8) 100%);
  background-size: cover;
  background-attachment: fixed;
}

.login-card {
  border: none;
  overflow: hidden;
}

.login-banner {
  background-image: url('https://images.unsplash.com/photo-1532601224476-15c79f2f7a51?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80');
  background-size: cover;
  background-position: center;
  height: 180px;
  position: relative;
}

.login-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(to right, rgba(0, 123, 255, 0.8), rgba(46, 204, 113, 0.8));
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.input-field {
  transition: all 0.3s ease;
  margin-bottom: 1rem;
}

.input-field:hover {
  transform: translateY(-2px);
}

.login-btn {
  transition: all 0.3s ease;
  letter-spacing: 0.5px;
  font-weight: 500;
}

.login-btn:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.divider-container {
  margin: 24px 0 16px;
}
</style>