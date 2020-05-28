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
        path: '/join',
        component: () => import('@/views/join'),
        name: 'join',
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
    {
        path: '/student-profile',
        component: () => import('@/views/profile'),
        name: 'studentProfile'
    },
    {
        path: '/academy-management',
        component: () => import('@/views/academyManagement'),
        name: 'academyManagement'
    },
    {
        path: '*',
        component: () => import('@/views/error'),
        name: 'error404'
    }
];
