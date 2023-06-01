export default {
   // 이메일 상태를 반환합니다.
  getEmail: state => state.email,

  // 에러 상태를 반환합니다.
  getErrorState: state => state.errorState,

  // 인증 상태를 반환합니다.
  getIsAuth: state => state.isAuth,

  // 사용자가 로그인되어 있는지 여부를 반환합니다.
  loggedIn(state) {
    return !!state.user
  }
}