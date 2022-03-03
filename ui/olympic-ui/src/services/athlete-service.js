import ServiceUtil from "./service-util";

class AthleteService {

    getAllAthletes(page) {
        return ServiceUtil.call("get", null, `/athlete/all?page=${page}`);
    }

    getAthleteById(id) {
        return ServiceUtil.call("get", null, `/athlete/${id}`);
    }

    getAthleteByName(name, surname) {
        return ServiceUtil.call("get", null, `/athlete?name=${name}&surname=${surname}`);
    }

    getAthletesByCountry(country, page) {
        return ServiceUtil.call("get", null, `/athlete/country?counrty=${country}&page=${page}`)
    }

    getAthletesByGame(id, page) {
        return ServiceUtil.call("get", null, `/athlete/game?id=${id}&page=${page}`);
    }

    getTop50AthletesByGame(id) {
        return ServiceUtil.call("get", null, `/athlete/game/${id}/top50`);
    }

    getAthletesByGameAndCountry(type, year, country) {
        return ServiceUtil.call("get", null, `/athlete/game/country?type=${type}&year=${year}&country=${country}`)
    }

}

export default new AthleteService;