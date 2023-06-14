export default {
  getEmail: state => state.email,
  getErrorState: state => state.errorState,
  getIsAuth: state => state.isAuth,
  loggedIn(state) {
    return !!state.user
  }
}