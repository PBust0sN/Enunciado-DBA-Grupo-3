<script setup>
import measurePointsService from '@/services/measurePoints.service';
import affectedAreasService from '@/services/affectedAreas.service';
import { ref } from 'vue';

const list = ref([]);
const list2 = ref([]);

const fetchData = async () => {
  try {
    const response = await measurePointsService.getInvalidPoints();
    const response2 = await affectedAreasService.getInvalidGeometry();
    list.value = response.data;
    list2.value = response2.data;
    console.log(response);
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

      <v-card-subtitle class="mt-4 mb-2 font-weight-medium">
        Puntos de medición inválidos
      </v-card-subtitle>

      <v-data-table
        :headers="[
          { title: 'ID Punto', key: 'id' },
          { title: 'Geometría', key: 'wkt' },
          { title: 'Latitud', key: 'latitud' },
          { title: 'Longitud', key: 'longitud' }
        ]"
        :items="list"
        class="elevation-1 mb-8"
        density="comfortable"
      >
        <template #item.wkt="{ item }">
          <span v-if="item.wkt === null">NULL</span>
          <span v-else>{{ item.wkt }}</span>
        </template>
        <template #item.latitud="{ item }">
          <span v-if="item.latitud === null">NULL</span>
          <span v-else>{{ item.latitud }}</span>
        </template>
        <template #item.longitud="{ item }">
          <span v-if="item.longitud === null">NULL</span>
          <span v-else>{{ item.longitud }}</span>
        </template>
      </v-data-table>

      <v-card-subtitle class="mt-6 mb-2 font-weight-medium">
        Geometrías corruptas
      </v-card-subtitle>

      <v-data-table
        :headers="[
          { title: 'ID Punto', key: 'id' },
          { title: 'Geometría', key: 'wkt' },
          { title: 'Motivo', key: 'motivo' }
        ]"
        :items="list2"
        class="elevation-1"
        density="comfortable"
      >
        <template #item.wkt="{ item }">
          <span v-if="item.wkt === null">NULL</span>
          <span v-else>{{ item.wkt }}</span>
        </template>
      </v-data-table>

    </v-card>
  </v-container>
</template>
