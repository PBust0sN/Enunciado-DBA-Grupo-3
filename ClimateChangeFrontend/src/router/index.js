import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import MapView from '@/views/MapView.vue'
import DataView from '@/views/DataView.vue'
import ConsultsView from '@/views/ConsultsView.vue'
import Consulta1 from '@/views/consultsViews/Consulta1.vue'
import Consulta2 from '@/views/consultsViews/Consulta2.vue'


const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/login', name: 'login', component: LoginView },
  { path: '/register', name: 'register', component: RegisterView },
  { path: '/map', name: 'map', component: MapView },
  { path: '/data', name: 'data', component: DataView },
  { path: '/consults', name: 'consults', component: ConsultsView },
  { path: '/consults/consulta1', name: 'consulta1', component: Consulta1 },
  { path: '/consults/consulta2', name: 'consulta2', component: Consulta2 }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
