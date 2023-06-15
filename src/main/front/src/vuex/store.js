import {createStore} from "vuex";
import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";    

const isLoggedIn = () => {
  const token = localStorage.getItem('user_token');
  return !!token;
};

export default createStore({
  state: {
    user: null,
    isLogin: isLoggedIn(),
  },
  mutations,
  getters,
  actions    
});