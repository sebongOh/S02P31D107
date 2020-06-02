import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default [
  {
    path: "/",
    component: () => import("@/views/login"),
    name: "login",
  },
  {
    path: "/join",
    component: () => import("@/views/join"),
    name: "join",
  },
  {
    path: "/findPassword",
    component: () => import("@/views/findpassword"),
    name: "findPassword",
  },
  {
    path: "/admin-main",
    component: () => import("@/views/admin"),
    name: "adminMain",
    children: [],
  },
  {
    path: "/student-main",
    component: () => import("@/views/student"),
    name: "studentMain",
    children: [],
  },
  {
    path: "/academy-main",
    component: () => import("@/views/academy"),
    name: "academyMain",
    children: [],
  },
  {
    path: "/payment",
    component: () => import("@/views/payment"),
    name: "payment",
    children: [],
  },
];
