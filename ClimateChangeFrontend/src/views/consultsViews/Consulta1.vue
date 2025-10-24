<script setup>
import measurementService from '@/services/measurement.service';
import { ref, onMounted } from 'vue';

const list = ref([]);

onMounted(async () => {
  try {
    const response = await measurementService.getAnomaly();
    console.log('Anomaly Data:', response.data);
    list.value = response.data;
  } catch (error) {
    console.error('Error fetching Anomaly data:', error);
  }
});
</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">
        1. Cálculo de Anomalía de Temperatura
      </v-card-title>
      <v-card-text>
        Muestra para todos los puntos, la diferencia (anomalía) entre la temperatura promedio del ultimo año con su promedio historico.
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
        <!-- handle null values safely -->
        <template v-slot:item.anomalia="{ item }">
          {{ item.anomalia !== null ? item.anomalia.toFixed(2) : '—' }}
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>

<style scoped>
</style>
