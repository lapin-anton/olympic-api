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

    postImgUrl(id, url) {
        return ServiceUtil.call("post", null, `/athlete/${id}/img?url=${url}`);
    }

}

export default new AthleteService;