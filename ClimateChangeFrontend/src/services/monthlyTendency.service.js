import api from './http-common'



const getMonthlyTendencies = () => {
    return api.get('/monthly-tendency/get-all')
}

export default {
    getMonthlyTendencies
}