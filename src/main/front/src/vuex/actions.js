import * as types from "./mutation_types";

export default {
  loginSuccess({ commit }, payload) {
    commit(types.LOGIN_SUCCESS, payload);
  },
  logout({ commit }) {
    commit(types.LOGOUT);
  },
};