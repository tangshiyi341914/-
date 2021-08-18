import Vue from 'vue';
import VueRouter from 'vue-router';
import login from '../views/Login.vue';
import home from '../views/Home.vue';
import userInfo from '../views/UserInfo.vue';
import Mainpage from "@/views/Mainpage";
import About from "@/views/About";
import Sell from "@/views/Sell";
import Contact from "@/views/Contact";
import profile from "../views/user/profile";
import RequireRelease from "../views/user/RequireRelease";
import Records from "@/views/user/Records";
import CompanyRegister from "@/views/CompanyRegister";
import UserRegister from "@/views/UserRegister";
import NewHeader from "@/views/index/NewHeader"
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject);
    return originalPush.call(this, location).catch((err) => err);
};

Vue.use(VueRouter);
const routes = [
    {
        path: '/',
        name: 'login',
        component: login,
        hidden: true,
    },
    {
        path: '/home',
        name: 'home',
        component: home,
        hidden: true,
        children: [
            {
                path: '/userinfo',
                name: '个人中心',
                component: userInfo,
                hidden: true,
            },
        ],
    },
    {
        path: '/mainpage',
        name: 'mainpage',
        component: Mainpage
    },

    {
        path: '/about',
        name: 'about',
        component: About
    },
    {
        path: '/sell',
        name: 'sell',
        component: Sell
    },
    {
        path: '/contact',
        name: 'contact',
        component: Contact
    },
    {
        path: '/profile',
        name: 'profile',
        component: profile,
    },
    {
        path: '/require',
        name: 'require',
        component: RequireRelease,
    },
    {
        path: '/records',
        name: 'records',
        component: Records
    },
    {
        path:'/UserRegister',
        name:'UserRegister',
        component: UserRegister
    },
    {
        path:'/CompanyRegister',
        name:'CompanyRegister',
        component: CompanyRegister
    },
    {
        path: '/NewHeader',
        name:'NewHeader',
        component: NewHeader
    },
    {
        path: '*',
        redirect: '/home',
    }
];
const router = new VueRouter({
    routes,
});

export default router;
