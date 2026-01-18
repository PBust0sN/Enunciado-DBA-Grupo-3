import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import MapView from '@/views/MapView.vue'
//import DataView from '@/views/DataView.vue'
import ConsultsView from '@/views/ConsultsView.vue'
import Consulta1 from '@/views/consultsViews/Consulta1.vue'
import Consulta2 from '@/views/consultsViews/Consulta2.vue'
import Consulta3 from '@/views/consultsViews/Consulta3.vue'
import Consulta4 from '@/views/consultsViews/Consulta4.vue'
import Consulta5 from '@/views/consultsViews/Consulta5.vue'
import Consulta6 from '@/views/consultsViews/Consulta6.vue'
import Consulta7 from '@/views/consultsViews/Consulta7.vue'
import Consulta8 from '@/views/consultsViews/Consulta8.vue'
import Consulta9 from '@/views/consultsViews/Consulta9.vue'
import Consulta10 from '@/views/consultsViews/Consulta10.vue'
import Consulta11 from '@/views/consultsViews/Consulta11.vue'
import Consulta12 from '@/views/consultsViews/Consulta12.vue'
const routes = [
  { path: '/', name: 'home', component: HomeView},
  { path: '/login', name: 'login', component: LoginView },
  { path: '/register', name: 'register', component: RegisterView },

  { path: '/map', name: 'map', component: MapView, meta: { requiresAuth: true } },
  //{ path: '/data', name: 'data', component: DataView, meta: { requiresAuth: true }},
  { path: '/consults', name: 'consults', component: ConsultsView, meta: { requiresAuth: true } },
  { path: '/consults/consulta1', name: 'consulta1', component: Consulta1, meta: { requiresAuth: true } },
  { path: '/consults/consulta2', name: 'consulta2', component: Consulta2, meta: { requiresAuth: true } },
  { path: '/consults/consulta3', name: 'consulta3', component: Consulta3, meta: { requiresAuth: true } },
  { path: '/consults/consulta4', name: 'consulta4', component: Consulta4, meta: { requiresAuth: true } },
  { path: '/consults/consulta5', name: 'consulta5', component: Consulta5, meta: { requiresAuth: true } },
  { path: '/consults/consulta6', name: 'consulta6', component: Consulta6, meta: { requiresAuth: true } },
  { path: '/consults/consulta7', name: 'consulta7', component: Consulta7, meta: { requiresAuth: true } },
  { path: '/consults/consulta8', name: 'consulta8', component: Consulta8, meta: { requiresAuth: true } },
  { path: '/consults/consulta9', name: 'consulta9', component: Consulta9, meta: { requiresAuth: true } },
  { path: '/consults/consulta10', name: 'consulta10', component: Consulta10, meta: { requiresAuth: true} },
  { path: '/consults/consulta11', name: 'consulta11', component: Consulta11, meta: { requiresAuth: true} },
  { path: '/consults/consulta12', name: 'consulta12', component: Consulta12, meta: { requiresAuth: true} }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

const isAuthenticated = () => {
  return localStorage.getItem('user') !== null;
}

router.beforeEach((to, from, next) => {
  if (to.meta && to.meta.requiresAuth && !isAuthenticated()) {
    next({ name: 'login', query: { redirect: to.fullPath } })
    return
  }

  if ((to.name === 'login' || to.name === 'register') && isAuthenticated()) {
    next({ name: 'home' })
    return
  }

  next()
})

export default router
