import Vue from "vue";
import App from "./App.vue";
import Router from "vue-router";
import routes from "./routes";
import store from "./vuex/store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

Vue.config.productionTip = false;

Vue.use(Router);
Vue.use(ElementUI);

const router = new Router({
  routes,
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
