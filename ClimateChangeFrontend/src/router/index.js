import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import MapView from '@/views/MapView.vue'
import DataView from '@/views/DataView.vue'
import ConsultsView from '@/views/ConsultsView.vue'
import Consulta1 from '@/views/consultsViews/Consulta1.vue'
import Consulta2 from '@/views/consultsViews/Consulta2.vue'
import Consulta3 from '@/views/consultsViews/Consulta3.vue'
import Consulta4 from '@/views/consultsViews/Consulta4.vue'
import Consulta5 from '@/views/consultsViews/Consulta5.vue'
import Consulta6 from '@/views/consultsViews/Consulta6.vue'
import Consulta7 from '@/views/consultsViews/Consulta7.vue'
import Consulta8 from '@/views/consultsViews/Consulta8.vue'

const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/login', name: 'login', component: LoginView },
  { path: '/register', name: 'register', component: RegisterView },
  { path: '/map', name: 'map', component: MapView },
  { path: '/data', name: 'data', component: DataView },
  { path: '/consults', name: 'consults', component: ConsultsView },
  { path: '/consults/consulta1', name: 'consulta1', component: Consulta1 },
  { path: '/consults/consulta2', name: 'consulta2', component: Consulta2 },
  { path: '/consults/consulta3', name: 'consulta3', component: Consulta3 },
  { path: '/consults/consulta4', name: 'consulta4', component: Consulta4 },
  { path: '/consults/consulta5', name: 'consulta5', component: Consulta5 },
  { path: '/consults/consulta6', name: 'consulta6', component: Consulta6 },
  { path: '/consults/consulta7', name: 'consulta7', component: Consulta7 },
  { path: '/consults/consulta8', name: 'consulta8', component: Consulta8 }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
