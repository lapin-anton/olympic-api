import ServiceUtil from "./service-util";

class SportService {

    getAllSports() {
        return ServiceUtil.call("get", null, '/sport/all');
    }

}

export default new SportService;