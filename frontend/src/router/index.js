import Vue from 'vue';
import VueRouter from 'vue-router';
import New from '../views/New.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'new',
    component: New,
  },
  {
    path: '/desk/:id',
    name: 'desk',
    component: () => import(/* webpackChunkName: "desk" */ '../views/Desk.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
