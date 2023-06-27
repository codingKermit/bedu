export default {
    // state에서 닉네임 값을 가져오는 getter
    getNickname: state => state.nickname,

    // state에서 이메일 값을 가져오는 getter
    getEmail: state => state.email,

    // state에서 사용자 번호 값을 가져오는 getter
    getUsernum: state => state.usernum,

    // state에서 클라스 값을 가져오는 getter
    getCls: state => state.cls,

    // state에서 에러 상태 값을 가져오는 getter
    getErrorState: state => state.errorState,

    // state에서 인증 상태 값을 가져오는 getter
    getIsAuth: state => state.isAuth,

    // 사용자가 로그인되어 있는지 여부를 반환하는 getter
    loggedIn(state) {
        // state에서 user 값이 존재하는지 확인하여 로그인 여부를 반환
        return !!state.user
    },

    // 로그인 중인지 여부를 반환하는 getter
    isLogin: state => state.isLogin,
}