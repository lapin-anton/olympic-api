import ServiceUtil from "./service-util";

class ResultsService {

    getAllResultsByGame(type, year, page) {
        return ServiceUtil.call("get", null, `/results/game/all?type=${type}&year=${year}&page=${page}`);
    }

    getCountryTeamRatingByGame(id) {
        return ServiceUtil.call("get", null, `/results/team/rating/game/${id}`);
    }

}

export default new ResultsService;