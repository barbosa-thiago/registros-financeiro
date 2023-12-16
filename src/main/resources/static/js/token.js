function addAuthorizationHeader() {
    if (token) {
        return {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json',
        };
    } else {
        return {};
    }
}