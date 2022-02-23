import ServiceUtil from "./service-util";

class GameService {

    getAllGames() {
        return ServiceUtil.call("get", null, '/game/all');
    }

}

export default new GameService;