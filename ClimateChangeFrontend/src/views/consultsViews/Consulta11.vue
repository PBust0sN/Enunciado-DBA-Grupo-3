<script setup>
import { ref, onMounted } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import affectedAreasService from '@/services/affectedAreas.service';

const mapRef = ref(null);
const list = ref([]);

let map = null;
let bounds = null;

// Colores simples
const colors = ['red', 'blue', 'green', 'orange', 'purple', 'brown'];

const initMap = () => {
  // 🔴 USAMOS EL ELEMENTO REAL, NO UN ID
  map = L.map(mapRef.value);

  // 🔴 VISTA INICIAL OBLIGATORIA
  map.setView([-33.4569, -70.6483], 10); // Santiago

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; OpenStreetMap contributors'
  }).addTo(map);
};

const drawPoints = () => {
  bounds = L.latLngBounds([]);

  list.value.forEach((item, index) => {
    if (
      typeof item.latitud !== 'number' ||
      typeof item.longitud !== 'number'
    ) {
      return;
    }

    const color = colors[index % colors.length];

    L.circleMarker([item.latitud, item.longitud], {
      radius: 6,
      color,
      fillColor: color,
      fillOpacity: 0.8
    })
      .addTo(map)
      .bindPopup(`
        <strong>ID Punto:</strong> ${item.idMeasurePoints}<br/>
        <strong>Área:</strong> ${item.areaName}
      `);

    bounds.extend([item.latitud, item.longitud]);
  });

  // Ajustar vista solo si hay puntos
  if (bounds.isValid()) {
    map.fitBounds(bounds, {
      padding: [80, 80],
      maxZoom: 12
    });
  }
};

const fetchData = async () => {
  const response = await affectedAreasService.getMeasurePointsInRiskAreas();
  console.log('RAW DATA FROM BACKEND:', response.data);

  list.value = response.data;
  drawPoints();
};

onMounted(async () => {
  initMap();
  await fetchData();

  // 🔴 FORZAR RECÁLCULO (Vuetify)
  setTimeout(() => {
    map.invalidateSize();
  }, 100);
});
</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">
        Puntos de Medición en Áreas de Riesgo
      </v-card-title>

      <v-card-text>
        Mapa de puntos de medición ubicados dentro de áreas de riesgo.
      </v-card-text>

      <v-divider class="my-4" />

      <!-- 🔴 ref en lugar de id -->
      <div
        ref="mapRef"
        style="height: 500px; width: 100%; border-radius: 8px;"
      ></div>
    </v-card>
  </v-container>
</template>
