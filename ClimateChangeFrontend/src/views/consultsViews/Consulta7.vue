<script setup>
import measurePointsService from '@/services/measurePoints.service';
import { ref } from 'vue';

const list = ref([]);

const fetchData = async () => {
  try {
    const response = await measurePointsService.getPointsWithoutGeoreference();
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
            7. Listado de Medidas sin Georreferenciación
        </v-card-title>
        <v-card-text>
            Muestra todos los puntos de medicion que no tienen una ubicación geográfica válida y la fecha de su última medición.
        </v-card-text>

      <v-data-table
        :headers="[
          { title: 'ID Punto', key: 'idMeasurePoints' },
          { title: 'Fecha última medición', key: 'lastMeasurementDate' }
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