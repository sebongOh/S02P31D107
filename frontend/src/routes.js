

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import Components from './views/Components.vue'
import Profile from './views/user/Profile.vue'
//나중에 sidebar 에 합체할 부분
import Test from './views/user/Test.vue'

export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    {
        path : '/profile',
        name : 'Profile',
        component : Profile
    },
    {
        path : '/test',
        name : 'Test',
        component : Test
    }
]
