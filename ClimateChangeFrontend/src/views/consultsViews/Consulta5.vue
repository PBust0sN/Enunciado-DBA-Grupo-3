<script setup>
import { useRoute } from 'vue-router';
import datasetService from '@/services/dataset.service';
import { ref } from 'vue';
const idDataset = ref();
const dataFetched = ref(false);
const list = ref([]);
const fetchData = async (idDataset) => {
  try {
    const response = await datasetService.interpolateWeeklyData(idDataset);
    list.value = response.data;
    dataFetched.value = true;
    console.log('Weekly Averages Data:', list.value);
  } catch (error) {
    dataFetched.value = false;
    console.error('Error fetching weekly averages data:', error);
  }
};
</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4 mb-4">
      <v-card-title class="text-h6 font-weight-bold">
        Ingresar id del dataset
      </v-card-title>

      <v-card-text>
        <v-text-field
          v-model="idDataset"
          label="ID del Dataset"
        ></v-text-field>


        <v-btn color="primary" @click="fetchData(idDataset)">Filtrar</v-btn>
      </v-card-text>
    </v-card>
    <v-container v-if="dataFetched">
      <v-card elevation="2" class="pa-4">
        <v-card-title class="text-h6 font-weight-bold">
            5. Simulación de Interpolación de Datos
        </v-card-title>
        <v-card-text>
            Dado un ID de dataset, se muestra el promedio semanal de las mediciones.
        </v-card-text>

      <v-data-table
        :headers="[
          { title: 'ID Measure Points', key: 'idMeasurePoints' },
          { title: 'Semana comienzo', key: 'weekStart' },
          { title: 'Semana término', key: 'weekEnd' },
          { title: 'Promedio semanal', key: 'avgValue' }
        ]"
        :items="list"
        class="elevation-1"
        density="comfortable"
      >
        <template v-slot:item.weekly_average="{ item }">
          {{ item.weekly_average !== null ? item.weekly_average.toFixed(2) : '—' }}
        </template>
      </v-data-table>
    </v-card>
    </v-container>
    
  </v-container>
</template>