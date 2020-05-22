import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default [
    {
        path: '/',
        component: () => import('@/views/login'),
        name: 'login',
    },
    {
        path: '/register',
        component: () => import('@/views/register'),
        name: 'register',
    },
    {
        path: '/admin-main',
        component: () => import('@/views/admin'),
        name: 'adminMain',
        children: [],
    },
    {
        path: '/student-main',
        component: () => import('@/views/student'),
        name: 'studentMain',
        children: [],
    },
    {
        path: '/academy-main',
        component: () => import('@/views/academy'),
        name: 'academyMain',
        children: [],
    },
];
