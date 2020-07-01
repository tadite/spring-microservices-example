const gatewayPath = "http://localhost";

export function apiPath(path) {
    return gatewayPath + path;
}

export function fetchApi(path, args) {
    return fetch(gatewayPath + path, args);
}