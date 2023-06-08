import * as types from "./mutation_types";

export default {
  [types.LOGIN_SUCCESS](state, payload) {
    state.isLoggedIn = true;
    state.nickname = payload.nickname;
    state.usernum = payload.usernum;
  },
  [types.LOGOUT](state) {
    state.isLoggedIn = false;
    state.nickname = "";
    state.usernum = "";
  },
};