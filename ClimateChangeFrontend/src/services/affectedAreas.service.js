import api from './http-common'

const basePath = '/affected-areas'

const getMeasurePointsInRiskAreas = () => {
    return api.get(`${basePath}/risk-measure-points`)
}

const getInvalidGeometry = () => {
    return api.get(`${basePath}/getInvalidGeometry`)
}

const getValidAreas = () => {
    return api.get(`${basePath}/getValidAreas`)
}
export default {
    getMeasurePointsInRiskAreas,
    getInvalidGeometry,
    getValidAreas
}