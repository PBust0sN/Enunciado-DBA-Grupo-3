<script>
import { defineComponent, ref } from 'vue';
import { VContainer, VRow, VCol, VCard, VCardTitle, VCardText, VBtn, VTextField, VSelect } from 'vuetify/components';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'Consults',
  setup() {
    const router = useRouter();

    // Campos de entrada
    const idDataset = ref('');
    const latitude = ref('');
    const longitude = ref('');

    // Función para navegar a consulta3View con parámetros
    const goToConsulta3 = () => {
      router.push({
        name: '/consultas/consulta3',
        query: {
          lat: latitude.value,
          lon: longitude.value
        }
      });
    };

    const goToConsulta5 = () => {
      router.push({
        name: 'consulta5',
        query: {
          id: idDataset.value
        }
      });
    };

    const goToConsulta6 = () => {
      router.push({
        name: 'consulta6',
        query: {
          id:  idDataset.value,
          lat: latitude.value,
          lon: longitude.value
        }
      });
    };

    return { idDataset, latitude, longitude, goToConsulta3, goToConsulta5, goToConsulta6 };
  }
});
</script>

<template>
  <v-container class="home-container" fluid>
    <header class="climate-header">
      <h1>Visualización de Datos Climáticos</h1>
      <p>Filtrar datos por parámetros</p>
    </header>

    <v-row class="climate-dashboard" dense>
      <!-- Consulta 1 -->
      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            Cálculo de Anomalía de Temperatura
          </v-card-title>
          
          <v-card-text>
            <v-btn class="mt-2" color="primary" href="/consults/consulta1">Filtrar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 2 -->
      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            Identificación de Puntos con Mayor Variación
          </v-card-title>
          
          <v-card-text>
            <v-btn class="mt-2" color="primary" href="/consults/consulta2">Filtrar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 4 -->
      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            Detección de Eventos Extremos
          </v-card-title>
          
          <v-card-text>
            <v-btn class="mt-10" color="primary" href="/consults/consulta4">Filtrar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            Listado de Medidas sin Georreferenciación
          </v-card-title>
          
          <v-card-text>
            <v-btn class="mt-2" color="primary" href="/consults/consulta7">Filtrar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            Análisis de Tendencia Histórica
          </v-card-title>
          
          <v-card-text>
            <v-btn class="mt-10" color="primary" href="/consults/consulta7">Filtrar</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

    </v-row>

    <v-row class="climate-dashboard" dense>
      <!-- Consulta 5 -->
      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            Simulación de Interpolación de Datos
          </v-card-title>
          
          <v-card-text>
            <v-text-field
                v-model="latitude"
                label="ID dataset"
                type="number"
                class="mt-1"
                ></v-text-field>

          <v-btn
            color="primary"
            @click="goToConsulta5"
            >
            Filtrar
            </v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Consulta 6 -->
      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
          <v-card-title class="multiline-title">
            Agregación de Datos para Visualización
          </v-card-title>
          <v-text-field
                v-model="latitude"
                label="ID dataset"
                type="number"
                class="mt-1"
            ></v-text-field>

          <v-text-field
              v-model="selectedDate"
              type="date"
              label="Fecha Inicio"
              class="date-input"
            ></v-text-field>

          <v-text-field
              v-model="selectedDate"
              type="date"
              label="Fecha Termino"
              class="date-input"
            ></v-text-field>

          <v-btn
            color="primary"
            @click="goToConsulta6"
            >
            Filtrar
            </v-btn>
        </v-card>
      </v-col>

      <!-- Consulta 3 -->
      <v-col cols="12" sm="6" md="4">
        <v-card class="climate-card">
            <v-card-title class="multiline-title">
                Análisis de Correlación Espacial
            </v-card-title>
            <v-card-text>
                <v-text-field
                v-model="latitude"
                label="Latitud"
                type="number"
                class="mt-1"
                ></v-text-field>

                <v-text-field
                v-model="longitude"
                label="Longitud"
                type="number"              
                ></v-text-field>

                <v-btn
                color="primary"
                @click="goToConsulta3"
                >
                Filtrar
                </v-btn>
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
  margin-bottom: 3rem;
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