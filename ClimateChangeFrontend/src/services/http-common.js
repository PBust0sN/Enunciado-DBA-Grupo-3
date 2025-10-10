import axios from "axios";

const API_BASE = import.meta.env.VITE_API_BASE || ""
const url = API_BASE + "/api/v1"

const api = axios.create({
  baseURL: url,
  withCredentials: true,
  headers: { "Content-Type": "application/json" }
})

export default api
