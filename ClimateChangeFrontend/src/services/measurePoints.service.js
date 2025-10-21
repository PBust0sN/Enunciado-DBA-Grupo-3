import api from './http-common'

const basePath = '/measurePoints'

const findById = (id) => {
    return api.get(`${basePath}/get/${id}`)
}

const getAll = () => {
    return api.get(`${basePath}/getAll`)
}

const create = (data) => {
    return api.post(`${basePath}/create`, data)
}

const update = (data) => {
    return api.put(`${basePath}/update`, data)
}

const getPointsWithHighestVariation = () => {
    return api.get(`${basePath}/pointsWithHighestVariation`)
}

const getPointsWithoutGeoreference = () => {
    return api.get(`${basePath}/pointsWithoutGeoreference`)
}

const getByLatLon = (lat, lon) => {
    return api.get(`${basePath}/getByLatLon/${lat}/${lon}`)
}

const getLessThan50 = (lat, lon) =>{
    return api.get(`${basePath}/getLessThan50/${lat}/${lon}`)
}

export default {
    findById,
    getAll,
    create,
    update,
    getPointsWithHighestVariation,
    getPointsWithoutGeoreference,
    getByLatLon,
    getLessThan50
}