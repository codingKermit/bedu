import {createStore} from "vuex"
import getters from "./getters"
import mutations from "./mutations"
import actions from "./actions"

export default createStore({
  // 상태 객체를 정의합니다.
  state: {
    user: null,    // 사용자 정보
    isLogin: false, // 로그인 상태 여부
  },

  // 뮤테이션을 등록합니다.
  mutations,

  // 게터를 등록합니다.
  getters,

  // 액션을 등록합니다.
  actions
})