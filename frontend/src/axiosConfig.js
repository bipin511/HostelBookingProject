import axios from "axios";

// Create a reusable Axios instance
const api = axios.create({
  baseURL: "http://localhost:8080",
});

// Add a request interceptor to include token if available
api.interceptors.request.use(
  (config) => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.token) {
      config.headers["Authorization"] = `Bearer ${user.token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
