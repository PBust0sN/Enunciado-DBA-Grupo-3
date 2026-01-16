import httpClient from "./http-common";

const getById = (id) => {
    return httpClient.get(`/measurements/get-measurement/${id}`);
}

const getExtreme = () => {
    return httpClient.get(`/measurements/extreme`);
}

const getAnomaly = () => {
    return httpClient.get(`/measurements/calculate-anomalia`);
}

const putById = (id) => {
    return httpClient.put(`/measurements/update-measurement/${id}`);
}

const deleteById = (id) => {
    return httpClient.delete(`/measurements/delete-measurement/${id}`);
}

export default { getById, getExtreme, getAnomaly, putById, deleteById};