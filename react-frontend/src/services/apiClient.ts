import axios from 'axios';

const apiClient = axios.create({
    baseURL: '/api',
});

// Placeholder interceptors for future auth tokens (JWT)
apiClient.interceptors.request.use((config) => {
    return config;
});

apiClient.interceptors.response.use(
    (response) => response,
    (error) => Promise.reject(error)
);

export default apiClient;



