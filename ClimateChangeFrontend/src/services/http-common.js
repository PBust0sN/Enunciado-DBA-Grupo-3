import axios from "axios";

const API_BASE = import.meta.env.VITE_API_BASE || ""
const url = API_BASE + "/api/v1"

const api = axios.create({
  baseURL: url,
  withCredentials: true,
  headers: { "Content-Type": "application/json" }
})

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api

