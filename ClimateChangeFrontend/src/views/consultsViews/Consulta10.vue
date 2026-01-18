<script setup>
import { ref, onMounted } from 'vue';
import measurePoints from '@/services/measurePoints.service';

// Lista de puntos sin sensor
const points = ref([]);

// Punto seleccionado
const selectedPoint = ref(null);

// Resultado interpolación
const result = ref(null);
const loading = ref(false);

// 1️⃣ Obtener puntos sin sensor
const fetchNoSensorPoints = async () => {
  try {
    const response = await measurePoints.getNoSensor();
    points.value = response.data;
  } catch (error) {
    console.error('Error obteniendo puntos sin sensor:', error);
  }
};

// 2️⃣ Interpolar usando el punto seleccionado
const interpolate = async () => {
  if (!selectedPoint.value) return;

  loading.value = true;
  result.value = null;

  try {
    const { latitud, longitud } = selectedPoint.value;

    const response = await measurePoints.interpolateByNearestNeighbors(
      latitud,
      longitud
    );

    result.value = response.data;
  } catch (error) {
    console.error('Error en interpolación:', error);
  } finally {
    loading.value = false;
  }
};

// Al montar la vista
onMounted(fetchNoSensorPoints);
</script>


<template>
  <v-container>
    <v-card elevation="2" class="pa-4">

      <v-card-title class="text-h6 font-weight-bold">
        Interpolación por Vecinos Más Cercanos
      </v-card-title>

      <v-card-text>
        Seleccione un punto sin sensor para realizar la interpolación
      </v-card-text>

      <v-divider class="my-4" />

      <!-- LISTA DE PUNTOS -->
      <v-select
        v-model="selectedPoint"
        :items="points"
        item-title="id"
        return-object
        label="Puntos sin sensor"
        @update:modelValue="interpolate"
      >
        <template #item="{ item, props }">
          <v-list-item
            v-bind="props"
            :title="`ID: ${item.raw.idMeasurePoints}`"
            :subtitle="`Lat: ${item.raw.latitud}, Lon: ${item.raw.longitud}`"
          />
        </template>

        <template #selection="{ item }">
          ID {{ item.raw.idMeasurePoints }} ({{ item.raw.latitud }}, {{ item.raw.longitud }})
        </template>
      </v-select>

      <v-divider class="my-6" />

      <!-- RESULTADO -->
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

