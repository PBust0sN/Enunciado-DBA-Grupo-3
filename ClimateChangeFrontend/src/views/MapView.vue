<script>
import { defineComponent } from 'vue';
import Sidebar from '@/components/Sidebar.vue';
export default defineComponent({
  name: 'MapView',
});
</script>

<template>
  <div>
    <section class="map">
        <h1>Map View</h1>
        <div class="map-container">
            
            <img class="map-image" src="@/assets/map.png" alt="Mapa Placeholder" />
        </div>

    </section>
  </div>
  <Sidebar />
</template>

<style scoped>

h1 {
  color: blue;
}

.map {
  background-color: var(--color-background-mute);
  border-radius: 12px;
  padding: 2rem;
  margin-bottom: 3rem;
}

.map h1 {
  text-align: center;
  margin-bottom: 2rem;
  color: var(--color-heading);
    font-size: 2.5rem;
}
.map-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 550px; /* Ajusta la altura según tus necesidades */

  border: 2px dashed var(--color-border);
  border-radius: 100px;
  background-color: var(--color-background);
}

.map-image {
  max-width: 100%;
  max-height: 100%;
  border-radius: 12px;
  object-fit: cover;
}
</style>