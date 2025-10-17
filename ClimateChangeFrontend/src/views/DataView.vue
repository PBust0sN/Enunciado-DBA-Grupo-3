<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'DataView',
});
</script>
<template>
  <div class="home-container">
    <header class="climate-header">
      <h1>Visualización de Datos Climáticos</h1>
      <p>Filtrar datos por parámetros</p>
    </header>

    <section class="climate-dashboard">
      <div class="climate-card">
        <div class="card-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="48" height="48">
            <path fill="#00000000" d="M20 10V7C20 5.89543 19.1046 5 18 5H6C4.89543 5 4 5.89543 4 7V10M20 10V19C20 20.1046 19.1046 21 18 21H6C4.89543 21 4 20.1046 4 19V10M20 10H4M8 3V7M16 3V7" stroke="#000000" stroke-linecap="round"/>
          </svg>
        </div>
        <div class="card-content">
          <h3>Fecha</h3>
          <p class="reading">Ingresa Fecha</p>
          <input class="date-input" type="date" />
            <p class="description">Selecciona la fecha para filtrar los datos</p>
            <button class="filter-button">Filtrar</button>
        </div>
      </div>

      <div class="climate-card">
        <div class="card-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="48" height="48">
            <path fill="#00000000" d="M15 4H20M15 8H20M17 12H20M8 15.9998C7.44772 15.9998 7 16.4475 7 16.9998C7 17.5521 7.44772 17.9998 8 17.9998C8.55228 17.9998 9 17.5521 9 16.9998C9 16.4475 8.55228 15.9998 8 15.9998ZM8 15.9998V9M8 16.9998L8.00707 17.0069M12 16.9998C12 19.209 10.2091 20.9998 8 20.9998C5.79086 20.9998 4 19.209 4 16.9998C4 15.9854 4.37764 15.0591 5 14.354L5 6C5 4.34315 6.34315 3 8 3C9.65685 3 11 4.34315 11 6V14.354C11.6224 15.0591 12 15.9854 12 16.9998Z" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path>
          </svg>
        </div>
        <div class="card-content">
          <h3>Tipo de medición</h3>
          <p class="reading">Selecciona el tipo de medición</p>
          <select class="type-input" aria-placeholder="Selecciona tipo de medición">
            <option value="" disabled selected class="placeholder-text">Selecciona tipo de medición</option>
            <option value="temperature">Temperatura</option>
            <option value="co2">Emisiones de CO₂</option>
            <option value="sea-level">Nivel del Mar</option>
            <option value="ice-mass">Masa de Hielo</option>
          </select>
            
            <button class="filter-button">Filtrar</button>
        </div>
      </div>

      <div class="climate-card">
        <div class="card-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="48" height="48">
            <path clip-rule="evenodd" d="M5 9.5C5 6.09371 8.00993 3 12 3C15.9901 3 19 6.09371 19 9.5C19 11.6449 17.6877 14.0406 15.9606 16.2611C14.5957 18.016 13.0773 19.5329 12 20.5944C10.9227 19.5329 9.40427 18.016 8.03935 16.2611C6.31229 14.0406 5 11.6449 5 9.5ZM12 1C6.99007 1 3 4.90629 3 9.5C3 12.3551 4.68771 15.2094 6.46065 17.4889C7.99487 19.4615 9.7194 21.1574 10.7973 22.2173C10.9831 22.4001 11.1498 22.564 11.2929 22.7071C11.4804 22.8946 11.7348 23 12 23C12.2652 23 12.5196 22.8946 12.7071 22.7071C12.8502 22.564 13.0169 22.4001 13.2027 22.2174L13.2028 22.2173C14.2806 21.1573 16.0051 19.4615 17.5394 17.4889C19.3123 15.2094 21 12.3551 21 9.5C21 4.90629 17.0099 1 12 1ZM12 12.5C13.3807 12.5 14.5 11.3807 14.5 10C14.5 8.61929 13.3807 7.5 12 7.5C10.6193 7.5 9.5 8.61929 9.5 10C9.5 11.3807 10.6193 12.5 12 12.5Z"></path>
          </svg>
        </div>
        <div class="card-content">
          <h3>Región geográfica</h3>
          <p class="reading">Selecciona Región</p>
            <select class="type-input" aria-placeholder="Selecciona región" >
            <option value="" disabled selected>Selecciona región</option>
              <option value="global">Global</option>
              <option value="north-america">Norteamérica</option>
              <option value="south-america">Sudamérica</option>
              <option value="europe">Europa</option>
              <option value="asia">Asia</option>
              <option value="africa">África</option>
              <option value="oceania">Oceanía</option>
            </select>
            <button class="filter-button">Filtrar</button>
        </div>
      </div>
      
    </section>


  </div>
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

.climate-header h1 {
  font-size: 2.5rem;
  color: var(--color-heading);
  margin-bottom: 0.5rem;
}

.climate-header p {
  font-size: 1.2rem;
  color: var(--color-text-light);
}

.climate-dashboard {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 1.5rem;
  margin-bottom: 3rem;
}

.climate-card {
  background: var(--color-background-soft, #ffffff);
  border-radius: 12px;
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.climate-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.08);
}

.card-icon {
  margin-bottom: 1rem;
  color: var(--color-primary, #3498db);
}

.card-content h3 {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
  color: var(--color-heading);
}

.reading {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: var(--color-text);
}

.trend {
  font-size: 0.9rem;
  margin-bottom: 0.5rem;
}

.description {
  font-size: 0.85rem;
  color: var(--color-text-light);
}

.critical {
  color: #e74c3c;
  font-weight: 500;
}

.warning {
  color: #f39c12;
}

.positive {
  color: var(--color-secondary);
}

.climate-trends {
  background-color: var(--color-background-mute);
  border-radius: 12px;
  padding: 2rem;
  margin-bottom: 3rem;
}

.climate-trends h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: var(--color-heading);
}

.trends-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 1.5rem;
}

.trend-item {
  background: var(--color-background-soft);
  border-radius: 10px;
  padding: 1.5rem;
  display: flex;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.trend-icon {
  margin-right: 1rem;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background-color: rgba(243, 156, 18, 0.1);
}

.trend-content h3 {
  font-size: 1rem;
  margin-bottom: 0.25rem;
  color: var(--color-heading);
}

.trend-content p {
  font-size: 0.9rem;
  color: var(--color-text);
}

.welcome-message {
  text-align: center;
  margin-top: 2rem;
  padding: 2rem;
  background: linear-gradient(135deg, rgba(52, 152, 219, 0.05) 0%, rgba(46, 204, 113, 0.05) 100%);
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  border: 1px solid var(--color-border);
}

.welcome-message h2 {
  font-size: 1.8rem;
  margin-bottom: 1rem;
  color: var(--color-heading);
}

.welcome-message p {
  font-size: 1.1rem;
  margin-bottom: 2rem;
  color: var(--color-text);
}

.action-buttons {
  display: flex;
  justify-content: center;
  gap: 1rem;
}

.btn {
  display: inline-block;
  padding: 0.8rem 1.5rem;
  border-radius: 6px;
  font-size: 1rem;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.3s ease;
}

.btn-primary {
  background-color: var(--color-primary, #3498db);
  color: white;
}

.btn-primary:hover {
  background-color: var(--color-primary-dark, #2980b9);
}

.btn-secondary {
  background-color: transparent;
  color: var(--color-primary, #3498db);
  border: 1px solid var(--color-primary, #3498db);
}

.btn-secondary:hover {
  background-color: rgba(52, 152, 219, 0.05);
}
.date-input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid var(--color-border);
  border-radius: 6px;
  font-size: 1rem;
  margin-bottom: 0.5rem;
}

.filter-button {
    margin-top: 1rem;
  background-color: var(--color-primary, #3498db);
  color: white;
  border: none;
  padding: 0.6rem 1.2rem;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.type-input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid var(--color-border);
  border-radius: 6px;
  font-size: 1rem;
  margin-top: 0.5rem;
}

.placeholder-text {
  color: lightgray;
}

@media (max-width: 768px) {
  .climate-dashboard, .trends-grid {
    grid-template-columns: 1fr;
  }
  
  .action-buttons {
    flex-direction: column;
  }
  
  .btn {
    width: 100%;
    margin-bottom: 0.5rem;
  }
  
  .trend-item {
    flex-direction: column;
    text-align: center;
  }
  
  .trend-icon {
    margin-right: 0;
    margin-bottom: 1rem;
  }
}
</style>