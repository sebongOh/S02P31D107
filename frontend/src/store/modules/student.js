import { getToken, setToken } from '@/utils/auth';
import { login } from '@/api/user';

const state = {
    token: getToken(),
    name: '',
    email: '',
    address: '',
    phone: '',
    age: '',
    gender: 0,
    child: 0,
};

const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token;
    },
    SET_NAME: (state, name) => {
        state.name = name;
    },
    SET_EMAIL: (state, email) => {
        state.email = email;
    },
    SET_ADDRESS: (state, address) => {
        state.address = address;
    },
    SET_PHONE: (state, phone) => {
        state.phone = phone;
    },
    SET_AGE: (state, age) => {
        state.age = age;
    },
    SET_GENDER: (state, gender) => {
        state.gender = gender;
    },
    SET_CHILD: (state, child) => {
        state.child = child;
    },
};

const actions = {
    // user login
    login({ commit }, userInfo) {
        const { email, password } = userInfo;
        return new Promise((resolve, reject) => {
            login({
                email: email.trim(),
                password: password,
            })
                .then((res) => {
                    commit('SET_TOKEN', res.data);
                    setToken(res.data);
                    resolve();
                })
                .catch((err) => {
                    reject(err);
                });
        });
    },
};

// 만약 모듈이 독립적이거나 재사용되기를 원한다면, namespaced: true라고 네임스페이스에 명시
export default {
    namespaced: true,
    state,
    mutations,
    actions,
};
