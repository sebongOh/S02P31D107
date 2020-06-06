import { getToken, setToken } from "@/utils/auth";
import { join, login, acajoin } from "@/api/student";
import { requestEmail } from "@/api/student";
import { emailCheck } from "@/api/student";
import { findPassword } from "@/api/student";
import { updateProfile, retire } from "@/api/student";
import { passwordCheck } from "@/api/student";
import { readReview, writeReview } from "@/api/student";
import { pay } from "@/api/student";
import { paySuccess } from "@/api/student";
import { findAcademy } from "@/api/student";
import { memberInfo } from "@/api/student";
import { memberAcademy } from "@/api/student";
const axios = require('axios');

const state = {
  token: getToken(),
  name: "",
  email: "",
  address: "",
  phone: "",
  age: "",
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
  SET_PROFILEFILE: (state, profileFile) => {
    state.profileFile = profileFile;
  }
};

const actions = {
  // user login
  login({ commit }, data) {
    //    const { email, password } = userInfo;
    return new Promise((resolve, reject) => {
      login({
        email: data.email.trim(),
        password: data.password,
        type: data.type,
      })
        .then((res) => {
          commit("SET_TOKEN", res.data);
          setToken(res.data);
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  join({ commit }, formData) {
    return new Promise((resolve, reject) => {
      join(formData)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  pay({ commit }, data) {
    return new Promise((resolve, reject) => {
      pay(data)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  paySuccess({ commit }, data) {
    return new Promise((resolve, reject) => {
      paySuccess(data)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },

  acajoin({ commit }, formData) {
    return new Promise((resolve, reject) => {
      acajoin(formData)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },

  requestEmail({ commit }, email) {
    return new Promise((resolve, reject) => {
      requestEmail(email)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  emailCheck({ commit }, data) {
    return new Promise((resolve, reject) => {
      emailCheck(data)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  findPassword({ commit }, data) {
    return new Promise((resolve, reject) => {
      findPassword(data)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },

  updateProfile({ commit }, formData) {
    return new Promise((resolve, reject) => {
      updateProfile(formData)
        .then((res) => {
          commit("SET_TOKEN", res.data);
          setToken(res.data);
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  passwordCheck({ commit }, data) {
    return new Promise((resolve, reject) => {
      passwordCheck({
        email: data.email.trim(),
        password: data.password,
      })
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  retire({ commit }, data) {
    return new Promise((resolve, reject) => {
      retire(data) //헤더에 액세스 토큰 넣어서 보내야함
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  readReview({ commit }, data) {
    return new Promise((resolve, reject) => {
      readReview(data.academyId)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  writeReview({ commit }, data) {
    return new Promise((resolve, reject) => {
      writeReview(data)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  findAcademy({ commit }, data) {
    return new Promise((resolve, reject) => {
      findAcademy(data.academyId)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  memberInfo({ commit }) {
    return new Promise((resolve, reject) => {
      memberInfo()
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        });
    });
  },
  memberAcademy({ commit }) {
    return new Promise((resolve, reject) => {
      memberAcademy()
        .then((res) => {
          resolve(res);
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
