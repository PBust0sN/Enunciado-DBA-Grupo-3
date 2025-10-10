<template>
  <div class="home-container">
    <header class="climate-header">
      <h1>Sistema de Monitoreo del Cambio Climático</h1>
      <p>Seguimiento de indicadores críticos para la acción climática</p>
    </header>

    <section class="climate-dashboard">
      <div class="climate-card">
        <div class="card-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="48" height="48">
            <path fill="currentColor" d="M12 2a7 7 0 0 1 7 7c0 2.38-1.19 4.47-3 5.74V17a1 1 0 0 1-1 1H9a1 1 0 0 1-1-1v-2.26A7 7 0 0 1 5 9a7 7 0 0 1 7-7m0 2a5 5 0 0 0-5 5c0 1.97 1.15 3.66 2.83 4.48.17.09.34.14.51.2.09.03.18.04.27.06.09.03.18.05.27.07v1.19h2.24v-1.2c.09-.02.18-.04.26-.06.09-.03.18-.04.27-.07.18-.06.34-.11.51-.2A4.98 4.98 0 0 0 17 9a5 5 0 0 0-5-5M9.5 21h5a1.5 1.5 0 0 1 0 3h-5a1.5 1.5 0 0 1 0-3z"/>
          </svg>
        </div>
        <div class="card-content">
          <h3>Anomalía Térmica Global</h3>
          <p class="reading">+1.2°C</p>
          <p class="trend critical">↑ 0.1°C desde 2020</p>
          <p class="description">Incremento sobre niveles preindustriales</p>
        </div>
      </div>

      <div class="climate-card">
        <div class="card-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="48" height="48">
            <path fill="currentColor" d="M4.5,21H19.5A1.5,1.5 0 0,0 21,19.5A1.5,1.5 0 0,0 19.5,18H4.5A1.5,1.5 0 0,0 3,19.5A1.5,1.5 0 0,0 4.5,21M4.5,17H19.5A1.5,1.5 0 0,0 21,15.5A1.5,1.5 0 0,0 19.5,14H4.5A1.5,1.5 0 0,0 3,15.5A1.5,1.5 0 0,0 4.5,17M4.5,13H19.5A1.5,1.5 0 0,0 21,11.5A1.5,1.5 0 0,0 19.5,10H4.5A1.5,1.5 0 0,0 3,11.5A1.5,1.5 0 0,0 4.5,13M4.5,9H19.5A1.5,1.5 0 0,0 21,7.5A1.5,1.5 0 0,0 19.5,6H4.5A1.5,1.5 0 0,0 3,7.5A1.5,1.5 0 0,0 4.5,9M4.5,5H19.5A1.5,1.5 0 0,0 21,3.5A1.5,1.5 0 0,0 19.5,2H4.5A1.5,1.5 0 0,0 3,3.5A1.5,1.5 0 0,0 4.5,5Z" />
          </svg>
        </div>
        <div class="card-content">
          <h3>Nivel del Mar</h3>
          <p class="reading">+104.5 mm</p>
          <p class="trend critical">↑ 4.5 mm/año</p>
          <p class="description">Aumento desde 1993</p>
        </div>
      </div>

      <div class="climate-card">
        <div class="card-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="48" height="48">
            <path fill="currentColor" d="M17.66,11.2C17.43,10.9 17.15,10.64 16.89,10.38C16.22,9.78 15.46,9.35 14.82,8.72C13.33,7.26 13,4.85 13.95,3C13,3.23 12.17,3.75 11.46,4.32C8.87,6.4 7.85,10.07 9.07,13.22C9.11,13.32 9.15,13.42 9.15,13.55C9.15,13.77 9,13.97 8.8,14.05C8.57,14.15 8.33,14.09 8.14,13.93C8.08,13.88 8.04,13.83 8,13.76C6.87,12.33 6.69,10.28 7.45,8.64C5.78,10 4.87,12.3 5,14.47C5.06,14.97 5.12,15.47 5.29,15.97C5.43,16.57 5.7,17.17 6,17.7C7.08,19.43 8.95,20.67 10.96,20.92C13.1,21.19 15.39,20.8 17.03,19.32C18.86,17.66 19.5,15 18.56,12.72L18.43,12.46C18.22,12 17.66,11.2 17.66,11.2M14.5,17.5C14.22,17.74 13.76,18 13.4,18.1C12.28,18.5 11.16,17.94 10.5,17.28C11.69,17 12.4,16.12 12.61,15.23C12.78,14.43 12.46,13.77 12.33,13C12.21,12.26 12.23,11.63 12.5,10.94C12.69,11.32 12.89,11.7 13.13,12C13.9,13 15.11,13.44 15.37,14.8C15.41,14.94 15.43,15.08 15.43,15.23C15.46,16.05 15.1,16.95 14.5,17.5H14.5Z" />
          </svg>
        </div>
        <div class="card-content">
          <h3>Emisiones de CO₂</h3>
          <p class="reading">419 ppm</p>
          <p class="trend critical">↑ 2.5 ppm/año</p>
          <p class="description">Mayor nivel en 800,000 años</p>
        </div>
      </div>
      
      <div class="climate-card">
        <div class="card-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="48" height="48">
            <path fill="currentColor" d="M20.79,13.95L18.46,14.57L16.46,13.44V10.56L18.46,9.43L20.79,10.05L21.31,8.12L19.54,7.65L20,5.88L18.07,5.36L17.45,7.69L15.45,8.82L13,7.38V5.12L14.71,3.41L13.29,2L12,3.29L10.71,2L9.29,3.41L11,5.12V7.38L8.5,8.82L6.5,7.69L5.92,5.36L4,5.88L4.47,7.65L2.7,8.12L3.22,10.05L5.55,9.43L7.55,10.56V13.45L5.55,14.58L3.22,13.96L2.7,15.89L4.47,16.36L4,18.12L5.93,18.64L6.55,16.31L8.55,15.18L11,16.62V18.88L9.29,20.59L10.71,22L12,20.71L13.29,22L14.7,20.59L13,18.88V16.62L15.5,15.17L17.5,16.3L18.12,18.63L20,18.12L19.53,16.35L21.3,15.88L20.79,13.95M9.5,10.56L12,9.11L14.5,10.56V13.44L12,14.89L9.5,13.44V10.56Z" />
          </svg>
        </div>
        <div class="card-content">
          <h3>Pérdida de Hielo</h3>
          <p class="reading">-427 Gt</p>
          <p class="trend critical">↓ Acelerando</p>
          <p class="description">Hielo perdido anualmente en Groenlandia</p>
        </div>
      </div>
    </section>

    <section class="climate-trends">
      <h2>Tendencias de Eventos Extremos</h2>
      <div class="trends-grid">
        <div class="trend-item">
          <div class="trend-icon warning">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32" height="32">
              <path fill="currentColor" d="M6,16.5L3,19.44V11H6M11,14.66L9.43,13.32L8,14.64V7H11M16,13L13,16V3H16M18.81,12.81L17,11H22V16L20.21,14.21L13,21.36L9.53,18.34L5.75,22H3L9.47,15.66L13,18.64" />
            </svg>
          </div>
          <div class="trend-content">
            <h3>Olas de Calor</h3>
            <p>+138% frecuencia desde 1990</p>
          </div>
        </div>
        <div class="trend-item">
          <div class="trend-icon warning">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32" height="32">
              <path fill="currentColor" d="M12,7A5,5 0 0,1 17,12A5,5 0 0,1 12,17A5,5 0 0,1 7,12A5,5 0 0,1 12,7M12,9A3,3 0 0,0 9,12A3,3 0 0,0 12,15A3,3 0 0,0 15,12A3,3 0 0,0 12,9M12,2L14.39,5.42C13.65,5.15 12.84,5 12,5C11.16,5 10.35,5.15 9.61,5.42L12,2M3.34,7L7.5,6.65C6.9,7.16 6.36,7.78 5.94,8.5C5.5,9.24 5.25,10 5.11,10.79L3.34,7M3.36,17L5.12,13.23C5.26,14 5.53,14.78 5.95,15.5C6.37,16.24 6.91,16.86 7.5,17.37L3.36,17M20.65,7L18.88,10.79C18.74,10 18.47,9.23 18.05,8.5C17.63,7.78 17.1,7.15 16.5,6.64L20.65,7M20.64,17L16.5,17.36C17.09,16.85 17.62,16.22 18.04,15.5C18.46,14.77 18.73,14 18.87,13.21L20.64,17M12,22L9.59,18.56C10.33,18.83 11.14,19 12,19C12.82,19 13.63,18.83 14.37,18.56L12,22Z" />
            </svg>
          </div>
          <div class="trend-content">
            <h3>Sequías Severas</h3>
            <p>+67% intensidad en últimas décadas</p>
          </div>
        </div>
        <div class="trend-item">
          <div class="trend-icon warning">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32" height="32">
              <path fill="currentColor" d="M9,10A1,1 0 0,1 8,9C8,8.43 8.4,8 9,8A1,1 0 0,1 10,9C10,9.43 9.6,10 9,10M15.5,6L18,3.5L20.5,6L18,8.5M15.5,14L18,11.5L20.5,14L18,16.5M9,4A1,1 0 0,1 8,3A1,1 0 0,1 9,2A1,1 0 0,1 10,3A1,1 0 0,1 9,4M19,12A1,1 0 0,1 18,11A1,1 0 0,1 19,10A1,1 0 0,1 20,11A1,1 0 0,1 19,12M9,20A1,1 0 0,1 8,19A1,1 0 0,1 9,18A1,1 0 0,1 10,19A1,1 0 0,1 9,20M9,16A1,1 0 0,1 8,15A1,1 0 0,1 9,14A1,1 0 0,1 10,15A1,1 0 0,1 9,16M9,12A1,1 0 0,1 8,11A1,1 0 0,1 9,10A1,1 0 0,1 10,11A1,1 0 0,1 9,12M3,14A1,1 0 0,1 2,13A1,1 0 0,1 3,12A1,1 0 0,1 4,13A1,1 0 0,1 3,14M3,10A1,1 0 0,1 2,9A1,1 0 0,1 3,8A1,1 0 0,1 4,9A1,1 0 0,1 3,10M3,6A1,1 0 0,1 2,5A1,1 0 0,1 3,4A1,1 0 0,1 4,5A1,1 0 0,1 3,6M3,18A1,1 0 0,1 2,17A1,1 0 0,1 3,16A1,1 0 0,1 4,17A1,1 0 0,1 3,18Z" />
            </svg>
          </div>
          <div class="trend-content">
            <h3>Precipitaciones Extremas</h3>
            <p>+30% eventos de inundación desde 2000</p>
          </div>
        </div>
        <div class="trend-item">
          <div class="trend-icon warning">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32" height="32">
              <path fill="currentColor" d="M12.74,5.47C15.1,6.5 16.35,9.03 15.92,11.46C17.19,12.56 18,14.19 18,16V16.17C18.31,16.06 18.65,16 19,16A3,3 0 0,1 22,19A3,3 0 0,1 19,22H6A4,4 0 0,1 2,18A4,4 0 0,1 6,14H6.27C5,12.45 4.6,10.24 5.5,8.26C6.72,5.5 9.97,4.24 12.74,5.47M11.93,7.3C10.16,6.5 8.09,7.31 7.31,9.07C6.85,10.09 6.93,11.22 7.41,12.13C8.5,10.83 10.16,10 12,10C12.7,10 13.38,10.12 14,10.34C13.94,9.06 13.18,7.86 11.93,7.3M13.55,3.64C13,3.4 12.45,3.23 11.88,3.12L14.37,1.82L15.27,4.71C14.76,4.29 14.19,3.93 13.55,3.64M6.09,4.44C5.6,4.79 5.17,5.19 4.8,5.63L4.91,2.82L7.87,3.5C7.25,3.71 6.65,4.03 6.09,4.44M18,9.71C17.91,9.12 17.78,8.55 17.59,8L19.97,9.5L17.92,11.73C18.03,11.08 18.05,10.4 18,9.71M3.04,11.3C3.11,11.9 3.24,12.47 3.43,13L1.06,11.5L3.1,9.28C3,9.93 2.97,10.61 3.04,11.3M19,18H16V16A4,4 0 0,0 12,12A4,4 0 0,0 8,16H6A2,2 0 0,0 4,18A2,2 0 0,0 6,20H19A1,1 0 0,0 20,19A1,1 0 0,0 19,18Z" />
            </svg>
          </div>
          <div class="trend-content">
            <h3>Ciclones Tropicales</h3>
            <p>+25% intensidad en categorías 4 y 5</p>
          </div>
        </div>
      </div>
    </section>

    <section class="welcome-message">
      <h2>Monitor del Cambio Climático</h2>
      <p>Accede para consultar datos detallados, gráficos históricos y proyecciones climáticas</p>
      <div class="action-buttons">
        <router-link to="/login" class="btn btn-primary">Iniciar Sesión</router-link>
        <router-link to="/register" class="btn btn-secondary">Registrarse</router-link>
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