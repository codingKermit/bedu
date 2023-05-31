export default {
    getEmail: state => state.email,
    getErrorState: state => state.errorState,
    getIsAuth: state => state.isAuth,
    getNickname: state => state.nickname,
    loggedIn(state) {
      return !!state.user
    }
  }