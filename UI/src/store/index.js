import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const store = new Vuex.Store({
  name: 'store',
  state: {
    routes: [],
    sessions: {},
    users: [],
    currentSession: null,
    currentUser: JSON.parse(window.sessionStorage.getItem('user')),
    filterKey: '',
    stomp: null,
    isDot: {},
  },
  mutations: {
    INIT_CURRENTUSER(state, user) {
      state.currentUser = user;
    },
    initRoutes(state, data) {
      state.routes = data;
    },
    changeCurrentSession(state, currentSession) {
      Vue.set(
        state.isDot,
        state.currentUser.username + '#' + currentSession.username,
        false,
      );
      state.currentSession = currentSession;
    },
    INIT_USER(state, data) {
      state.users = data;
    },
  },
  actions: {},
  modules: {},
});
store.watch(
  function(state) {
    return state.sessions;
  },
  {
    deep: true /*这个貌似是开启watch监测的判断,官方说明也比较模糊*/,
  },
);
export default store;
