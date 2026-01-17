import api from './http-common'

const basePath = '/affected-areas'

const getMeasurePointsInRiskAreas = () => {
    return api.get(`${basePath}/risk-measure-points`)
}

export default {
    getMeasurePointsInRiskAreas
}