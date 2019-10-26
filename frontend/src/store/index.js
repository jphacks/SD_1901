import Vuex from 'vuex';
import Vue from 'vue';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    cache: {},
  },
  mutations: {
    caching(state, { deskId, itemId, content }) {
      if (state.cache[deskId] === undefined) {
        state.cache[deskId] = {};
      }
      state.cache[deskId][itemId] = content;
    },
  },
  actions: {
  },
  modules: {
  },
});
