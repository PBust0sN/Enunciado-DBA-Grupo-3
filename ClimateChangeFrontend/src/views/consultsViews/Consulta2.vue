<script setup>
import measurePointsService from '@/services/measurePoints.service';
import { ref } from 'vue';

const list = ref([]);

const fetchData = async () => {
  try {
    const response = await measurePointsService.getPointsWithHighestVariation();
    list.value = response.data;
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

fetchData();

</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">
        2. Identificación de Puntos con Mayor Variación
      </v-card-title>
      <v-card-text>
        Muestra los 10 puntos con mayor desviación estándar en los valores de temperatura de los últimos 5 años.
      </v-card-text>

      <v-data-table
        :headers="[
          { title: 'ID Punto', key: 'idMeasurePoints' },
          { title: 'Valor desviación estándar', key: 'temperatureStddev' }
        ]"
        :items="list"
        class="elevation-1"
        density="comfortable"
      >
        <template v-slot:item.value="{ item }">
          {{ item.value.toFixed(2) }}
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>