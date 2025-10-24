<script>
import { defineComponent, ref } from 'vue';
import { VContainer, VRow, VCol, VCard, VCardTitle, VCardText, VBtn, VTextField, VSelect } from 'vuetify/components';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'Consults',
  setup() {
    const router = useRouter(); 

  }
});
</script>

<template>
  <v-container class="home-container" fluid>
    <header class="climate-header">
      <h1>Consultas sobre datos climáticos</h1>
    </header>

    <v-row class="climate-dashboard" dense>
      <!-- Consulta 1 -->
      <v-col md="3" >
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            1. Cálculo de Anomalía de Temperatura
          </v-card-title>
          <v-card-text>
            Muestra para todos los puntos, la diferencia (anomalía) entre la temperatura promedio del ultimo año con su promedio historico.
          </v-card-text>
          <v-card-text>
            <v-btn color="primary" href="/consults/consulta1">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 2 -->
      <v-col md="3">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            2. Identificación de Puntos con Mayor Variación
          </v-card-title>
          <v-card-text>
            Muestra los 10 puntos con mayor desviación estándar en los valores de temperatura de los últimos 5 años.
          </v-card-text>
          <v-card-text>
            <v-btn class="mt-5" color="primary" href="/consults/consulta2">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 3 -->
      <v-col md="3">
        <v-card class="climate-card">
            <v-card-title class="multiline-title">
                3. Análisis de Correlación Espacial
            </v-card-title>
            <v-card-text>
              Muestra todos los puntos de medición de CO2 que están a menos de 50 km de un punto de medición de temperatura específico dado por sus coordenadas.
            </v-card-text>
            <v-card-text>
            <v-btn class="mt-3" color="primary" href="/consults/consulta3">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 4 -->
      <v-col md="3">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            4. Detección de Eventos Extremos
          </v-card-title>
          <v-card-text>
            Muestra todos los días en el último año donde la temperatura máxima registrada en cualquier punto de medición superó un umbral de 35°C.
          </v-card-text>
          <v-card-text>
            <v-btn class="mt-3" color="primary" href="/consults/consulta4">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>


    <v-row class="climate-dashboard" dense>
      <!-- Consulta 5 -->
      <v-col md="3">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            5. Simulación de Interpolación de Datos
          </v-card-title>
          <v-card-text>
            Dado un ID de dataset, se muestra el promedio semanal de las mediciones.
          </v-card-text>
          <v-card-text>
            <v-btn class="mt-5" color="primary" href="/consults/consulta5">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 6 -->
      <v-col md="3">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            6. Agregación de Datos para Visualización
          </v-card-title>
          <v-card-text>
            Dado un ID de dataset y un rango de fechas, muestra una serie temporal agregada de las mediciones.
          </v-card-text>
          <v-card-text>
            <v-btn color="primary" href="/consults/consulta6">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 7 -->
      <v-col md="3">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            7. Listado de Medidas sin Georreferenciación
          </v-card-title>
          <v-card-text>
            Muestra todos los puntos de medicion que no tienen una ubicación geográfica válida y la fecha de su última medición.
          </v-card-text>
          <v-card-text>
            <v-btn color="primary" href="/consults/consulta7">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 8 -->
      <v-col md="3">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            8. Análisis de Tendencia Histórica
          </v-card-title>
          <v-card-text>
            Muestra el valor promedio de cada tipo de medición por mes, desde el inicio del registro de datos.
          </v-card-text>
          <v-card-text>
            <v-btn class="mt-8" color="primary" href="/consults/consulta8">Consultar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.home-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem 1rem;
}

.climate-header {
  text-align: center;
  margin-bottom: 3rem;
}

.climate-dashboard {
  margin-bottom: 1rem;
}

.climate-card {
  text-align: center;
  padding: 1.5rem;
}

.multiline-title {
  white-space: normal; /* Permite que el texto se quiebre en varias líneas */
  word-break: break-word; /* Evita que palabras largas se desborden */
}

</style>