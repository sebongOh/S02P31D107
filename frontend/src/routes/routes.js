import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default [
    {
        path: '/',
        component: () => import('@/views/login/index'),
        name: 'login',
    },
    {
        path: '/register',
        component: () => import('@/views/register/index'),
        name: 'register',
    },
    {
        path: '/admin-main',
        component: () => import('@/views/admin/index'),
        name: 'adminMain',
        children: [],
    },
    {
        path: '/student-main',
        component: () => import('@/views/student/index'),
        name: 'studentMain',
        children: [],
    },
    {
        path: '/academy-main',
        component: () => import('@/views/academy/index'),
        name: 'academyMain',
        children: [],
    },
];
