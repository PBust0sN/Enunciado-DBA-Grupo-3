<script setup>
import { useRoute } from 'vue-router';
import datasetService from '@/services/dataset.service';
import { ref } from 'vue';
const list = ref([]);
const idDataset = ref();
const startDate = ref();
const endDate = ref();  
const dataFetched = ref(false);

const fetchData = async () => {
  try {
    const response = await datasetService.timeSeriesMeasure(idDataset.value, startDate.value, endDate.value);
    list.value = response.data;
    dataFetched.value = true;
    console.log('Datasets Data:', list.value);
  } catch (error) {
    dataFetched.value = false;
    console.error('Error fetching datasets:', error);
  }
};
</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4 mb-4">
      <v-card-title class="text-h6 font-weight-bold">
        Ingresar id del dataset y rango de fechas
      </v-card-title>

      <v-card-text>
        <v-text-field
          v-model="idDataset"
          label="ID del Dataset"
        ></v-text-field>

        <v-text-field
          v-model="startDate"
          label="Fecha de inicio (YYYY-MM-DD)"
        ></v-text-field>

        <v-text-field
          v-model="endDate"
          label="Fecha de término (YYYY-MM-DD)"
        ></v-text-field>

        <v-btn color="primary" @click="fetchData">Filtrar</v-btn>
      </v-card-text>
    </v-card>
    <v-container v-if="dataFetched">
      <v-card elevation="2" class="pa-4">
        <v-card-title class="text-h6 font-weight-bold">
            6. Agregación de Datos para Visualización
        </v-card-title>
        <v-card-text>
            Dado un ID de dataset y un rango de fechas, muestra una serie temporal agregada de las mediciones.
        </v-card-text>

      <v-data-table
        :headers="[
          { title: 'Fecha', key: 'periodStart' },
          { title: 'Valor', key: 'avgValue' }
        ]"
        :items="list"
        class="elevation-1"
        density="comfortable"
      >
        <template v-slot:item.avgValue="{ item }">
          {{ item.avgValue !== null ? item.avgValue.toFixed(2) : '—' }}
        </template>
      </v-data-table>
    </v-card>
    </v-container>
  </v-container>
</template>
