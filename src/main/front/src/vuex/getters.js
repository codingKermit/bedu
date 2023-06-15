
export default {
  getNickname: state => state.nickname,
  getEmail: state => state.email,
  getUsernum: state => state.usernum,
  getErrorState: state => state.errorState,
  getIsAuth: state => state.isAuth,
  loggedIn(state) {
    return !!state.user
  },
  isLogin: state => state.isLogin,
}