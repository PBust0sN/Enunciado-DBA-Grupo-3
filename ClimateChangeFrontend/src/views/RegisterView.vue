<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import auth from '../services/auth'

const router = useRouter()
const rut = ref('')
const first_name = ref('')
const last_name = ref('')
const email = ref('')
const password = ref('')
const message = ref(null)
const loading = ref(false)
const form = ref(null)

const rules = {
  required: v => !!v || 'Este campo es requerido',
  email: v => /.+@.+\..+/.test(v) || 'El email debe ser válido',
  rut: v => /^[0-9]{7,8}-[0-9K]$/.test(v) || 'Formato RUT válido: 12345678-9',
  minLength: v => (v && v.length >= 8) || 'Mínimo 8 caracteres'
}

const submit = async () => {
  const { valid } = await form.value.validate()
  if (!valid) return

  message.value = null
  loading.value = true
  try {
    const payload = {
      rut: rut.value,
      first_name: first_name.value,
      last_name: last_name.value,
      email: email.value,
      password: password.value
    }
    await auth.register(payload)
    message.value = { type: 'success', text: 'Cuenta creada exitosamente' }
    setTimeout(() => {
      router.push({ name: 'login' })
    }, 1500)
  } catch (err) {
    const text = err?.response?.data?.message || err.message || 'Error en el registro'
    message.value = { type: 'error', text }
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="register-bg">
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="10" md="8" lg="7">
          <v-card
            class="register-card"
            variant="outlined"
            rounded="lg"
            elevation="8"
          >
            <v-row no-gutters>
              <v-col cols="12" md="5" class="d-none d-md-flex">
                <div class="register-image">
                  <div class="register-overlay">
                    <h2 class="text-h4 text-white font-weight-bold">Únete a nosotros</h2>
                    <p class="text-body-1 text-white mt-2">
                      Contribuye al monitoreo y análisis del cambio climático
                    </p>
                  </div>
                </div>
              </v-col>
              
              <v-col cols="12" md="7">
                <v-card-item>
                  <v-card-title class="text-h5 text-primary d-flex align-center mb-4">
                    <v-icon icon="mdi-account-plus" class="mr-2"></v-icon>
                    Crear nueva cuenta
                  </v-card-title>

                  <v-card-text>
                    <v-form ref="form" @submit.prevent="submit">
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

                      <v-row>
                        <v-col cols="12" sm="6">
                          <v-text-field
                            v-model="first_name"
                            :rules="[rules.required]"
                            label="Nombre"
                            prepend-inner-icon="mdi-account"
                            variant="outlined"
                            density="comfortable"
                            color="primary"
                            bg-color="surface"
                            class="rounded-lg input-field"
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                          <v-text-field
                            v-model="last_name"
                            :rules="[rules.required]"
                            label="Apellido"
                            prepend-inner-icon="mdi-account"
                            variant="outlined"
                            density="comfortable"
                            color="primary"
                            bg-color="surface"
                            class="rounded-lg input-field"
                          ></v-text-field>
                        </v-col>
                      </v-row>

                      <v-text-field
                        v-model="email"
                        :rules="[rules.required, rules.email]"
                        label="Correo electrónico"
                        prepend-inner-icon="mdi-email"
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
                        class="mt-6 register-btn"
                        size="large"
                        block
                        type="submit"
                        :loading="loading"
                        :disabled="loading"
                        color="primary"
                        rounded="lg"
                        elevation="2"
                      >
                        <v-icon start icon="mdi-account-check" class="mr-1"></v-icon>
                        {{ loading ? 'Creando cuenta...' : 'Crear cuenta' }}
                      </v-btn>

                      <div class="text-center mt-4">
                        <v-divider class="my-4"></v-divider>
                        <p class="text-body-2">
                          ¿Ya tienes una cuenta?
                          <v-btn
                            variant="text"
                            color="primary"
                            density="comfortable"
                            to="/login"
                            class="px-1 font-weight-bold text-decoration-underline"
                          >
                            Inicia sesión
                            <v-icon end icon="mdi-arrow-right" size="small"></v-icon>
                          </v-btn>
                        </p>
                      </div>
                    </v-form>
                  </v-card-text>
                </v-card-item>
              </v-col>
            </v-row>
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
.register-bg {
  min-height: 100vh;
  background: linear-gradient(135deg, rgba(240, 244, 247, 0.8) 0%, rgba(214, 234, 248, 0.8) 100%);
  background-size: cover;
  background-attachment: fixed;
}

.register-card {
  border: none;
  overflow: hidden;
}

.register-image {
  background-image: url('https://images.unsplash.com/photo-1440342359743-84fcb8c21f21?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80');
  background-size: cover;
  background-position: center;
  height: 100%;
  position: relative;
}

.register-overlay {
  position: relative;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(45deg, rgba(46, 204, 113, 0.85), rgba(0, 123, 255, 0.85));
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
}

.input-field {
  transition: all 0.3s ease;
  margin-bottom: 0.5rem;
}

.input-field:hover {
  transform: translateY(-2px);
}

.register-btn {
  transition: all 0.3s ease;
  letter-spacing: 0.5px;
  font-weight: 500;
}

.register-btn:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>