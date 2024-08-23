import axios from 'axios';  // npm install axios

axios.defaults.baseURL = 'http://localhost:8081'
axios.defaults.headers.post["Content-type"] = 'application/json' // 모든 POST 요청에 대해 Content-Type을 JSON으로 설정


// backend와 통신
// 로그인이 완료시 JWT를 저장한다.

export const getAuthToken = () => {
    return window.localStorage.getItem("auth_token");  // 'auth_token' 키로 저장된 값을 가져옴
}

export const setAuthToken = (token) => {
    window.localStorage.setItem("auth_token", token);    // 'auth_token' 키로 토큰을 저장
}

export const request = (method, url, data) => {

    //let headers = {};
    // if(getAuthToken() != null && getAuthToken() !== "null"){
    //     headers = {"Autorizaton" : 'Bearer ${getAuthToken()'}
    //     console.log('headers', headers);
    // }

    // 이 부분은 주석 처리되어 있으나, 필요한 경우 JWT를 Authorization 헤더에 추가할 수 있는 코드
    // if 블록은 로컬 스토리지에 저장된 토큰이 null이 아닌 경우, Authorization 헤더에 토큰을 추가하는 기능을 담당

    console.log('axios !!!!!!!!');
    console.log('method: ', method);
    console.log('url: ', url);
    console.log('data: ', data);
  
    return axios({
        method: method,
    //  headers: headers,   // (주석 처리됨) 인증 헤더 (JWT 토큰을 포함할 수 있음)
        url: url,
        data: data
    });
};


