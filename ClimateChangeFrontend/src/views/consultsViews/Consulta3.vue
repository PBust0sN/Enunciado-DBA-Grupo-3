<script setup>
import { useRoute } from 'vue-router';
import measurePointsService from '@/services/measurePoints.service';
import { ref } from 'vue';

// reactive variables
const route = useRoute();
const lat = ref(parseFloat(route.query.lat) || 0.0);
const lon = ref(parseFloat(route.query.lon) || 0.0);
const list = ref([]);
const isLoading = ref(false);
const dataFetched = ref(false);

// fetch data from API
const fetchData = async () => {
  isLoading.value = true;
  try {
    const response = await measurePointsService.getLessThan50(lat.value, lon.value);
    list.value = response.data;
    dataFetched.value = true;
    console.log('Data fetched:', list.value);
  } catch (error) {
    console.error('Error fetching data:', error);
  } finally {
    isLoading.value = false;
  }
};
</script>

<template>
  <v-container>
    <!-- Filter card -->
    <v-card elevation="2" class="pa-4 mb-4">
      <v-card-title class="text-h6 font-weight-bold">
        Filtrar por Coordenadas
      </v-card-title>

      <v-card-text>
        <v-text-field
          v-model="lat"
          label="Latitud"
          step="0.000001"
        ></v-text-field>

        <v-text-field
          v-model="lon"
          label="Longitud"
          step="0.000001"
        ></v-text-field>

        <v-btn color="primary" @click="fetchData">Filtrar</v-btn>
      </v-card-text>
    </v-card>

    <!-- Loading indicator -->
    <v-progress-circular
      v-if="isLoading"
      indeterminate
      color="primary"
      size="50"
      class="my-4"
    ></v-progress-circular>

    <!-- Data table (only shown after fetching) -->
    <v-container v-if="dataFetched && !isLoading">
      <v-card elevation="2" class="pa-4">
        <v-card-title class="text-h6 font-weight-bold">
          3. Análisis de Correlación Espacial
        </v-card-title>
        <v-card-text>
          Muestra todos los puntos de medición de CO2 que están a menos de 50 km de un punto de medición de temperatura específico dado por sus coordenadas.
        </v-card-text>

        <v-data-table
          :headers="[
            { title: 'ID Measure Points', key: 'idMeasurePoints' },
            { title: 'Latitud', key: 'latitud' },
            { title: 'Longitud', key: 'longitud' },
            { title: 'Tipo de sensor', key: 'sensorType' }
          ]"
          :items="list"
          class="elevation-1"
          density="comfortable"
        >
          <!-- Format numeric fields to show as doubles -->
          <template v-slot:item.latitud="{ item }">
            {{ item.latitud !== null ? item.latitud.toFixed(6) : '—' }}
          </template>

          <template v-slot:item.longitud="{ item }">
            {{ item.longitud !== null ? item.longitud.toFixed(6) : '—' }}
          </template>
        </v-data-table>
      </v-card>
    </v-container>
  </v-container>
</template>

<style scoped>
</style>
