<script setup>
import measurementService from '@/services/measurement.service';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const list = ref([]);
const notAuthorized = ref(false);
const router = useRouter();

const isAdmin = () => {
  return localStorage.getItem('role') === 'ADMIN';
};

onMounted(async () => {
  if (!isAdmin()) {
    notAuthorized.value = true;

    // ⏳ espera 2 segundos y redirige
    setTimeout(() => {
      router.push('/consults');
    }, 5000);

    return; // ⛔ no ejecuta la consulta
  }

  try {
    const response = await measurementService.getAnomaly();
    list.value = response.data;
  } catch (error) {
    console.error('Error fetching Anomaly data:', error);
  }
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

    <!-- Contenido solo si está autorizado -->
    <v-card v-if="!notAuthorized" elevation="2" class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">
        1. Cálculo de Anomalía de Temperatura
      </v-card-title>

      <v-card-text>
        Muestra para todos los puntos, la diferencia (anomalía) entre la temperatura
        promedio del último año con su promedio histórico.
      </v-card-text>

      <v-data-table
        :headers="[
          { title: 'ID Measure Points', key: 'id_measure_points' },
          { title: 'Anomalía', key: 'anomalia' }
        ]"
        :items="list"
        class="elevation-1"
        density="comfortable"
      >
        <template #item.anomalia="{ item }">
          {{ item.anomalia !== null ? item.anomalia.toFixed(2) : '—' }}
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>

<style scoped>
</style>
