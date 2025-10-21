<script>
import measurementService from '@/services/measurement.service';
import { ref } from 'vue';
const list = ref([]);

try {
    const response = await measurementService.getAnomaly();
    console.log('Anomaly Data:', response.data);
    // Aquí puedes manejar los datos recibidos, por ejemplo, asignarlos a una variable de estado
    list.value = response.data;
} catch (error) {
    console.error('Error fetching Anomaly data:', error);
}


</script>

<template>
  <v-container>
    <v-card elevation="2" class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">
        Data Table
      </v-card-title>

      <v-data-table
        :headers="[
          { title: 'ID', key: 'id' },
          { title: 'Value', key: 'value' }
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

<style scoped>

</style>