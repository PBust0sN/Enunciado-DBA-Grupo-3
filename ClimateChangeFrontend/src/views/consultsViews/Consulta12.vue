<script setup>
import measurePointsService from '@/services/measurePoints.service';
import { ref } from 'vue';

const list = ref([]);

const fetchData = async () => {
  try {
    const response = await measurePointsService.getInvalidPoints();
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
            4.2. Validación de Geometrías
        </v-card-title>
        <v-card-text>
            Consulta para detectar puntos de medición con coordenadas inválidas o geometrías corruptas.
        </v-card-text>

      <v-data-table
        :headers="[
          { title: 'ID Punto', key: 'id' },
          { title: 'Geometría', key: 'wkt' }
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