import ServiceUtil from "./service-util";

class ResultsService {

    getAllResultsByGame(type, year, page) {
        return ServiceUtil.call("get", null, `/results/game/all?type=${type}&year=${year}&page=${page}`);
    }

    getCountryTeamRatingByGame(type, year) {
        return ServiceUtil.call("get", null, `/results/rating/game?type=${type}&year=${year}`);
    }

}

export default ResultsService;