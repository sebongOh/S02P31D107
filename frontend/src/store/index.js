import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';

Vue.use(Vuex);

// require.context 관련 공식 문서는 여기 참조(https://webpack.js.org/guides/dependency-management/#requirecontext)
// ./modules 폴더 안에 있는 파일 중 .js로 끝나는 모든 파일
const modulesFiles = require.context('./modules', true, /\.js$/);

const modules = modulesFiles.keys().reduce((modules, modulePath) => {
    const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1');
    const value = modulesFiles(modulePath);
    modules[moduleName] = value.default;
    return modules;
}, {});

const store = new Vuex.Store({
    modules,
    getters,
});

export default store;
