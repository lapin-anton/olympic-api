import ServiceUtil from "./service-util";

class GameService {

    getAllGames() {
        return ServiceUtil.call("get", null, '/game/all');
    }

    getGameById(id) {
        return ServiceUtil.call("get", null, `/game/${id}`);
    }

    getAthletesCountByGameId(id) {
        return ServiceUtil.call("get", null, `/game/${id}/athletes`)
    }

}

export default new GameService;