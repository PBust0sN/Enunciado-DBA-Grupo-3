<script setup>
const dummyMeasure = {
  typeMeasurement: 'Temperature',
  location: {
    lat: -33.456789,
    lng: -70.123456
  }
}

const dummyMeasurements = [
  { valueMeasurement: 27.1, dateMeasurement: '2023-08-01' },
  { valueMeasurement: 30.2, dateMeasurement: '2023-09-01' },
  { valueMeasurement: 26.8, dateMeasurement: '2024-01-15' },
  { valueMeasurement: 28.0, dateMeasurement: '2024-06-10' },
  { valueMeasurement: 25.4, dateMeasurement: '2023-06-15' },
  { valueMeasurement: 29.5, dateMeasurement: '2023-07-18' }
];
</script>

<template>
  <v-container fluid>
    <v-row>
      <!-- Map column -->
      <v-col cols="12" md="7">
        <section class="map">
          <h1>Map View</h1>
          <div class="map-container">
            <img class="map-image" src="@/assets/map3.png" alt="Mapa Placeholder" />
          </div>
        </section>
      </v-col>

      <!-- Cards column -->
      <v-col cols="12" md="5">
        <!-- Measure Point Card -->
        <v-card elevation="3" class="pa-4 mb-4">
          <v-card-title class="text-h6 font-weight-bold">
            Measure Point 1
          </v-card-title>

          <v-card-text>
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    <strong>Tipo:</strong> {{ dummyMeasure.typeMeasurement }}
                  </v-list-item-title>
                  <v-list-item-title>
                    <strong>Latitud:</strong> {{ dummyMeasure.location.lat }}
                  </v-list-item-title>
                  <v-list-item-title>
                    <strong>Longitud:</strong> {{ dummyMeasure.location.lng }}
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card-text>
        </v-card>

        <!-- Recent Measurements Card -->
        <v-card elevation="3" class="pa-4 mb-4">
          <v-card-title class="text-h6 font-weight-bold">
            Recent Measurements
          </v-card-title>

          <v-card-text>
            <v-list>
              <v-list-item
                v-for="(measurement, index) in dummyMeasurements"
                :key="index"
              >
                <v-list-item-content>
                  <v-list-item-title>
                    Value: {{ measurement.valueMeasurement }} - Date: {{ measurement.dateMeasurement }}
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.map {
  background-color: var(--color-background-mute);
  border-radius: 12px;
  padding: 2rem;
  margin-bottom: 1rem;
}

.map h1 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: var(--color-heading);
  font-size: 2.5rem;
}

.map-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 550px;
  border-radius: 12px;
  
}

.map-image {
  max-width: 100%;
  max-height: 100%;
  border-radius: 12px;
  object-fit: cover;
}
</style>

