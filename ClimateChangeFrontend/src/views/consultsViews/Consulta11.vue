<script setup>
import { ref, onMounted } from 'vue';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import affectedAreasService from '@/services/affectedAreas.service';

const mapRef = ref(null);
const list = ref([]);
const areas = ref([]);

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
  bounds = L.latLngBounds([]);

  const pointsResponse =
    await affectedAreasService.getMeasurePointsInRiskAreas();
  list.value = pointsResponse.data;

  const areasResponse =
    await affectedAreasService.getValidAreas();
  areas.value = areasResponse.data;

  drawPolygons();
  drawPoints();
};

const fetchAreas = async () => {
  const response = await affectedAreasService.getValidAreas();
  console.log('VALID AREAS:', response.data);
  areas.value = response.data;
};

onMounted(async () => {
  initMap();
  await fetchData();

  // 🔴 FORZAR RECÁLCULO (Vuetify)
  setTimeout(() => {
    map.invalidateSize();
  }, 100);
});


const wktToLatLngs = (wkt) => {
  if (!wkt || !wkt.includes('POLYGON')) return [];

  const match = wkt.match(/\(\((.*)\)\)/);
  if (!match) return [];

  return match[1].split(',').map(pair => {
    const [lng, lat] = pair.trim().split(/\s+/).map(Number);
    return [lat, lng]; // Leaflet usa [lat, lng]
  });
};

const drawPolygons = () => {
  areas.value.forEach((area, index) => {
    if (!area.wkt) return;

    const latLngs = wktToLatLngs(area.wkt);
    if (!latLngs.length) return;

    const color = colors[index % colors.length];

    const polygon = L.polygon(latLngs, {
      color,
      weight: 2,
      fillOpacity: 0.3
    })
      .addTo(map)
      .bindPopup(`
        <strong>Área:</strong> ${area.name}<br/>
        <strong>Tipo:</strong> ${area.areaType}
      `);

    bounds.extend(polygon.getBounds());
  });
};
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
