<script setup>
import { useRoute } from 'vue-router';
import measurementService from '@/services/measurement.service';

import { ref, onMounted } from 'vue';
const route = useRoute();

const measurements = ref([]);

const fetchData = async () => {
    try {
        const response = await measurementService.getExtreme();
        measurements.value = response.data;
        console.log('Extreme Events Data:', measurements.value);
    } catch (error) {
        console.error('Error fetching extreme events:', error);
    }
}

onMounted(fetchData);
 </script>

 <template>
    <v-container>
        <v-card elevation="2" class="pa-4">
        <v-card-title class="text-h6 font-weight-bold">
            4. Detección de Eventos Extremos
        </v-card-title>
        <v-card-text>
            Muestra todos los días en el último año donde la temperatura máxima registrada en cualquier punto de medición superó un umbral de 35°C.
        </v-card-text>
    
        <v-data-table
            :headers="[
            { title: 'Fecha', key: 'fecha' },
            { title: 'Temperatura Máxima', key: 'temperatura_maxima' }
            ]"
            :items="measurements"
            class="elevation-1"
            density="comfortable"
        >
            <template v-slot:item.maxTemperature="{ item }">
            {{ item.maxTemperature !== null ? item.maxTemperature.toFixed(2) : '—' }}
            </template>
        </v-data-table>
        </v-card>
    </v-container>

 </template>