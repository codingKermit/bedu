import {createStore} from "vuex";
import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";    

// 사용자 로그인 상태 확인
const isLoggedIn = () => {
    const token = localStorage.getItem('user_token');
    return !!token;
};

export default createStore({
    // Vuex 스토어 설정
    state: {
        // 로그인 여부
        isLogin: isLoggedIn(),
        // 닉네임
        nickname: null,
        // 이메일
        email: null,
        // 사용자 번호
        usernum: null,
        // CLS
        cls: null
    },
    mutations,
    getters,
    actions    
});