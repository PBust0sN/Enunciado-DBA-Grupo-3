<script setup>
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'
import auth from '../services/auth'

const props = defineProps({
  userName: {
    type: String,
    default: 'Invitado'
  }
})

const router = useRouter()
const drawer = ref(false)

const isLoggedIn = computed(() => {
  return localStorage.getItem('user') !== null
})

const logout = async () => {
  await auth.logout();
  router.push('/login')
    window.location.reload();
}

const items = [
  { title: 'Iniciar Sesión', icon: 'mdi-login', to: '/login', show: computed(() => !isLoggedIn.value) },
  { title: 'Registrarse', icon: 'mdi-account-plus', to: '/register', show: computed(() => !isLoggedIn.value) },
  { title: 'Cerrar Sesión', icon: 'mdi-logout', action: logout, show: computed(() => isLoggedIn.value) },
]

const menuItems = [
        /*This is just for adjusting the list*/ 
        { label: 'Home', to: '/' },
        { label: 'Filtrar Datasets', to: '/data' },
        { label: 'Mapa', to: '/map' },
        { label: 'Consultas', to: '/consults'}
]


const filteredItems = computed(() => {
  return items.filter(item => !item.show || item.show.value)
})
</script>

<template>
  <div>
    <v-app-bar 
      color="white" 
      elevation="1"
      class="px-2"
      height="70"
    >
      <div class="d-flex align-center">
        <v-avatar color="primary" class="mr-2" size="42">
          <v-icon icon="mdi-earth" size="large" color="white"></v-icon>
        </v-avatar>
        <v-app-bar-title class="text-primary font-weight-bold">
          Cambio Climático
        </v-app-bar-title>
      </div>
      <v-spacer >
            <div class="d-flex justify-center align-center">
              <v-btn
                v-for="(item, index) in menuItems"  
                :key="index"
                :to="item.to"
                color="primary"
                variant="tonal"
                class="mx-2"
              >
                {{ item.label }}
              </v-btn>
            </div>
      </v-spacer>



      <div class="d-none d-md-flex">
        <v-btn 
          v-for="(item, i) in filteredItems" 
          :key="i" 
          :to="item.to" 
          :prepend-icon="item.icon"
          variant="text" 
          class="mx-1"
          @click="item.action ? item.action() : null"
        >
          {{ item.title }}
        </v-btn>
      </div>

      <v-menu open-on-hover location="bottom">
        <template v-slot:activator="{ props }">
          <v-btn 
            color="primary" 
            variant="tonal"
            class="ml-4 rounded-pill d-none d-md-flex" 
            v-bind="props"
          >
            <v-icon left>mdi-account-circle</v-icon>
            <span class="ml-2">{{ props.userName }}</span>
          </v-btn>
        </template>
        <v-card min-width="200">
          <v-list>
            <v-list-item>
              <template v-slot:prepend>
                <v-avatar color="primary" size="small">
                  <span class="text-h6 text-white">{{ props.userName.charAt(0) }}</span>
                </v-avatar>
              </template>
              <v-list-item-title>{{ props.userName }}</v-list-item-title>
            </v-list-item>
            <v-divider></v-divider>
            <v-list-item v-if="isLoggedIn" @click="logout">
              <template v-slot:prepend>
                <v-icon>mdi-logout</v-icon>
              </template>
              <v-list-item-title>Cerrar sesión</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-card>
      </v-menu>

      <v-app-bar-nav-icon 
        class="d-flex d-md-none" 
        @click="drawer = !drawer"
      ></v-app-bar-nav-icon>
    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      temporary
      location="right"
    >
      <v-list>
        <v-list-item>
          <template v-slot:prepend>
            <v-avatar color="primary">
              <span class="text-h6 text-white">{{ props.userName.charAt(0) }}</span>
            </v-avatar>
          </template>
          <v-list-item-title>{{ props.userName }}</v-list-item-title>
        </v-list-item>
      </v-list>
      
      <v-divider></v-divider>
      
      <v-list density="compact" nav>
        <v-list-item 
          v-for="(item, i) in filteredItems" 
          :key="i" 
          :to="item.to" 
          :prepend-icon="item.icon"
          @click="item.action ? item.action() : null"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>