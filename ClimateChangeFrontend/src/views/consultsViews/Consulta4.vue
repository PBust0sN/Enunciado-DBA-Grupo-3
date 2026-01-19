<script setup>
import { useRoute } from 'vue-router';
import measurementService from '@/services/measurement.service';
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';
const route = useRoute();

const measurements = ref([]);
const notAuthorized = ref(false);
const router = useRouter();
const isAdmin = () => {
  const userStr = localStorage.getItem('user');
  if (!userStr) return false;

  const user = JSON.parse(userStr);
  return user.role === 'ROLE_ADMIN';
};

const fetchData = async () => {
    try {
        const response = await measurementService.getExtreme();
        measurements.value = response.data;
        console.log('Extreme Events Data:', measurements.value);
    } catch (error) {
        console.error('Error fetching extreme events:', error);
    }
}

onMounted(async () => {
  if (!isAdmin()) {
    notAuthorized.value = true;

    // ⏳ espera 2 segundos y redirige
    setTimeout(() => {
      router.push('/consults');
    }, 5000);

    return; // ⛔ no ejecuta la consulta
  }
  fetchData();
});

 </script>

 <template>
    <v-container>

    <!-- 🚫 Alerta -->
    <v-alert
      v-if="notAuthorized"
      type="error"
      variant="tonal"
      class="mb-4"
    >
      No estás autorizado para acceder a esta información.
      Serás redirigido automáticamente.
    </v-alert>


        <v-card v-if="!notAuthorized" elevation="2" class="pa-4">
        <v-card-title class="text-h6 font-weight-bold">
            4. Detección de Eventos Extremos
        </v-card-title>
        <v-card-text>
            Muestra todos los días en el último año donde la temperatura máxima registrada en cualquier punto de medición superó un umbral de 35°C.
        </v-card-text>
    
        <v-data-table
            :headers="[
            { title: 'Fecha', key: 'fecha' },
            { title: 'Temperatura Máxima', key: 'temperatura_maxima' }
            ]"
            :items="measurements"
            class="elevation-1"
            density="comfortable"
        >
            <template v-slot:item.maxTemperature="{ item }">
            {{ item.maxTemperature !== null ? item.maxTemperature.toFixed(2) : '—' }}
            </template>
        </v-data-table>
        </v-card>
    </v-container>

 </template>