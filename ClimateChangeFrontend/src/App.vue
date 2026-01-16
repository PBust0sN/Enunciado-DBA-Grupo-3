<script setup>
import { RouterView } from 'vue-router'
import HeaderComponent from './components/Header.vue'
import FooterComponent from './components/Footer.vue'
import Sidebar from './components/Sidebar.vue'

const user = JSON.parse(localStorage.getItem('user'))
const first_name = user?.first_name || ''
const last_name = user?.last_name || ''
const userName = first_name && last_name ? `${first_name} ${last_name}` : 'Invitado'
</script>

<template>
  <v-app>
    <HeaderComponent :user-name="userName" />

    <v-main>
      <v-container class="pa-0" fluid>
        <RouterView />
      </v-container>
    </v-main>
    
    <FooterComponent />
  </v-app>
</template>

<style>
/* Podemos mantener algunas variables CSS globales */
:root {
  --color-primary: #3498db;
  --color-primary-dark: #2980b9;
  --color-secondary: #2ecc71;
  --color-background: #f0f0f0;
  --color-background-soft: #ffffff;
  --color-background-mute: #f1f5f9;
  --color-border: #e2e8f0;
  --color-heading: #1e293b;
  --color-text: #334155;
  --color-text-light: #64748b;
}

/* Algunas personalizaciones globales */
.v-application {
  background-color: var(--color-background) !important;
}

.text-primary {
  color: var(--color-primary) !important;
}

.bg-primary {
  background-color: var(--color-primary) !important;
}

/* Estilos para tipografía */
body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu,
    Cantarell, 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>