import http from "../http-common"

class ServiceUtil {

    call(type, data, url) {
        let request;

        if (type === 'get') {
            request = http[type](url)
        } else {
            request = http[type](url, data)
        }

        return request
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
            })
    }
}

export default ServiceUtil;