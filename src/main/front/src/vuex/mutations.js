import * as types from './mutation_types'

export default {
  // 이메일 상태를 업데이트합니다.
  [types.EMAIL](state, email) {
    state.email = email
  },

  // 에러 상태를 업데이트합니다.
  [types.ERROR_STATE](state, errorState) {
    state.errorState = errorState
  },

  // 인증 상태를 업데이트합니다.
  [types.IS_AUTH](state, isAuth) {
    state.isAuth = isAuth
  },
}