import api from './http-common'

const basePath = '/dataset'

const getAllDatasets = () => {
    return api.get(`${basePath}/get-all-datasets`)
}
const createDataset = (data) => {
    return api.post(`${basePath}/create-dataset`, data)
}

const getById = (idDataset) => {
    return api.get(`${basePath}/getById/${idDataset}`)
}

const getByName = (name) => {
    return api.get(`${basePath}/get-by-name/${name}`)
}

const timeSeriesMeasure = (idDataset, startDate, endDate) => {
    return api.get(`${basePath}/time-serie-measure/${idDataset}/${startDate}/${endDate}`)
}

const interpolateWeeklyData = (idDataset) => {
    return api.get(`${basePath}/interpolar-datos-semanales/${idDataset}`)
}
export default {
    getAllDatasets,
    createDataset,
    getById,
    getByName,
    timeSeriesMeasure,
    interpolateWeeklyData
}
