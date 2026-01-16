<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'Sidebar',
  data() {
    return {
      menuItems: [
        /*This is just for adjusting the list*/ 
        { label: 'Inicio', to: '/' },
        { label: 'Home', to: '/' },
        { label: 'Filtrar Datasets', to: '/data' },
        { label: 'Mapa', to: '/map' },
        { label: 'Consultas', to: '/consults'}
      ]
    }
  }
});
</script>

<template>
    <div class="sidebar">
        <ul class="sidebar-list">
            <li v-for="(item, index) in menuItems" :key="index">
                <router-link :to="item.to" class="sidebar-button">
                <button>{{ item.label }}</button>
                </router-link>
            </li>
        </ul>
    </div>

</template> 

<style scoped>  
.sidebar {
  width: 250px;
  background-color: whitesmoke; /* dark blue-gray */
  border-right: 1px solid #334155;
  padding: 1rem;
  border-right: 1px solid var(--color-border);
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  overflow-y: auto;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}
.sidebar-list {
  list-style: none;
  padding: 0;
  display: flex;
  flex-direction: column;
}
.sidebar-button button {
  width: 100%;
  padding: 10px 16px;
  border: none;
  background-color: var(--color-primary);
  color: white;
  border-radius: 8px;
  margin: 5px 0;
  text-align: left;
  cursor: pointer;
}
.sidebar-button button:hover {
  background-color: var(--color-primary-dark);
}
</style>