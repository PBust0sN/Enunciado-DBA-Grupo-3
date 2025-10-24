<script setup>

import { ref } from 'vue';
import monthlyTendencyService from '@/services/monthlyTendency.service';

const list = ref([]);

const fetchData = async () => {
    try {
        const response = await monthlyTendencyService.getMonthlyTendencies();
        list.value = response.data;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

fetchData();

</script>

<template>
    <v-container>
        <v-card elevation="2" class="pa-4">
        <v-card-title class="text-h6 font-weight-bold">
            8. Análisis de Tendencia Histórica
        </v-card-title>
        <v-card-text>
            Muestra el valor promedio de cada tipo de medición por mes, desde el inicio del registro de datos.
        </v-card-text>
    
        <v-data-table
            :headers="[
            { title: 'Tipo de sensor', key: 'sensorType' },
            { title: 'Año', key: 'year' },
            { title: 'Mes', key: 'month' },
            { title: 'Valor Promedio', key: 'average' }
            ]"
            :items="list"
            class="elevation-1"
            density="comfortable"
        >
            <template v-slot:item.valor_promedio="{ item }">
            {{ item.valor_promedio !== null ? item.valor_promedio.toFixed(2) : '—' }}
            </template>
        </v-data-table>
        </v-card>
    </v-container>

</template>