<script setup>
import { ref, onMounted } from 'vue';
import L from 'leaflet';
import measurePointsService from '@/services/measurePoints.service';

const list = ref([]);
let map = null;
let bounds = null;

// Colores (se repiten)
const colors = [
  'red',
  'blue',
  'green',
  'orange',
  'purple',
  'brown',
  'black'
];

// Parseo WKT: POINT(lon lat)
const parseWKTPoint = (wkt) => {
  if (!wkt) return null;

  const match = wkt.match(/POINT\s*\(\s*([-0-9.]+)\s+([-0-9.]+)\s*\)/);
  if (!match) return null;

  return {
    lon: parseFloat(match[1]),
    lat: parseFloat(match[2])
  };
};

const initMap = () => {
  map = L.map('map');

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; OpenStreetMap contributors'
  }).addTo(map);
};

const drawPoints = () => {
  bounds = L.latLngBounds([]);

  list.value.forEach((item, index) => {
    const coords = parseWKTPoint(item.co2_geom);
    if (!coords) return;

    const color = colors[index % colors.length];

    L.circleMarker([coords.lat, coords.lon], {
      radius: 6,
      color,
      fillColor: color,
      fillOpacity: 0.8
    })
      .addTo(map)
      .bindPopup(`
        <strong>CO₂ Point ID:</strong> ${item.co2_point_id}<br/>
        <strong>Sensor:</strong> ${item.sensorType}<br/>
        <strong>Distancia:</strong> ${item.distance_meters.toFixed(2)} m
      `);

    // Agregar punto a los límites
    bounds.extend([coords.lat, coords.lon]);
  });

  // Centrar mapa y alejar zoom automáticamente
  if (bounds.isValid()) {
    map.fitBounds(bounds, {
      padding: [80, 80], // margen alrededor
      maxZoom: 12        // menos zoom (más alejado)
    });
  }
};

const fetchData = async () => {
  try {
    const response = await measurePointsService.get50kmCO2Temperature();
    list.value = response.data;
    drawPoints();
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

onMounted(() => {
  initMap();
  fetchData();
});
</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">
        Análisis CO₂ vs Temperatura (Radio 50 km)
      </v-card-title>

      <v-card-text>
        Visualización espacial de los puntos CO₂.
        El mapa se centra automáticamente según los puntos obtenidos.
      </v-card-text>

      <v-divider class="my-4" />

      <div
        id="map"
        style="height: 500px; width: 100%; border-radius: 8px;"
      ></div>
    </v-card>
  </v-container>
</template>

