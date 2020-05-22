import Axios from 'axios';

import store from '@/store';
import { getToken } from '@/utils/auth';

// axios 객체 생성
const service = Axios.create({
    baseURL: '', // url = base url + request url
    timeout: 5000, // request timeout
});

// request interceptor
service.interceptors.request.use(
    (config) => {
        if (store.getters.token) {
            config.headers[''] = getToken();
            config.headers['Access-Control-Allow-Origin'] = '*';
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
