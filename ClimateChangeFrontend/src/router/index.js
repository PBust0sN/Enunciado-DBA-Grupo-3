import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import MapView from '@/views/MapView.vue'
import DataView from '@/views/DataView.vue'

const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/login', name: 'login', component: LoginView },
  { path: '/register', name: 'register', component: RegisterView },
  { path: '/map', name: 'map', component: MapView },
  { path: '/data', name: 'data', component: DataView }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
