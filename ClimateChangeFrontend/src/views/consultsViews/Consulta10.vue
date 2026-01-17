<script setup>
import { ref, onMounted } from 'vue';
import measurePoints from '@/services/measurePoints.service';

// Coordenadas predefinidas: Estación Central
const LATITUDE = -33.4569;
const LONGITUDE = -70.6483;

const result = ref(null);
const loading = ref(false);

const fetchData = async () => {
  loading.value = true;
  try {
    const response = await measurePoints.interpolateByNearestNeighbors(
      LATITUDE,
      LONGITUDE
    );
    result.value = response.data;
  } catch (error) {
    console.error('Error fetching interpolation:', error);
  } finally {
    loading.value = false;
  }
};

onMounted(fetchData);
</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">
        Interpolación por Vecinos Más Cercanos
      </v-card-title>

      <v-card-text>
        Resultado de la interpolación utilizando vecinos más cercanos
        <br />
        <small>
          Latitud: {{ LATITUDE }} | Longitud: {{ LONGITUDE }}
        </small>
      </v-card-text>

      <v-divider class="my-4" />

      <v-card-text class="text-center">
        <v-progress-circular
          v-if="loading"
          indeterminate
          size="40"
        />

        <div v-else>
          <div class="text-subtitle-1 mb-2">
            Valor interpolado
          </div>

          <div class="text-h4 font-weight-bold">
            {{ result !== null ? result.toFixed(4) : '—' }}
          </div>
        </div>
      </v-card-text>
    </v-card>
  </v-container>
</template>
