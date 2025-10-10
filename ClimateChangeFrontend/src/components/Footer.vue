<script setup>
const currentYear = new Date().getFullYear()
</script>

<template>
  <v-footer 
    class="text-center d-flex flex-column bg-surface-variant"
    border
  >    
    <div class="px-4 py-2 text-center w-100">
      <strong class="text-primary font-weight-medium">Sistema de Monitoreo del Cambio Climático</strong>
      
      <div class="d-flex flex-column flex-md-row justify-center mt-2 gap-2">
        <v-btn variant="text" size="small" class="text-caption" to="/">
          Inicio
        </v-btn>
        <v-btn variant="text" size="small" class="text-caption">
          Acerca De
        </v-btn>
        <v-btn variant="text" size="small" class="text-caption">
          Términos y Condiciones
        </v-btn>
        <v-btn variant="text" size="small" class="text-caption">
          Privacidad
        </v-btn>
      </div>

      <div class="mt-2 text-caption text-medium-emphasis">
        &copy; {{ currentYear }} — <strong>Universidad de Santiago de Chile</strong>
      </div>
    </div>
  </v-footer>
</template>