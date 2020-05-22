import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default [
    {
        path: '/',
        component: () => import('@/views/student/index'),
    },
];
