import {EMAIL, IS_AUTH, ERROR_STATE} from './mutation_types'
import loginAPI from '../service/loginAPI'


// 이메일 상태를 설정합니다.
let setEmail = ({ commit }, data) => {
  commit(EMAIL, data)
}

// 에러 상태를 설정합니다.
let setErrorState = ({ commit }, data) => {
  commit(ERROR_STATE, data)
}

// 인증 상태를 설정합니다.
let setIsAuth = ({ commit }, data) => {
  commit(IS_AUTH, data)
}


// 백엔드에서 반환된 결과값을 처리하여 로그인 성공 여부를 Vuex에 반영합니다.
let processResponse = (store, loginResponse) => {
  switch (loginResponse) {
    case 'notFound':
      setErrorState(store, 'Wrong ID or Password')
      setIsAuth(store, false)
      break
    default:
      setEmail(store, loginResponse.email)
      setErrorState(store, '')
      setIsAuth(store, true)
  }
}

export default {
  // 로그인 액션을 수행합니다.
  async login(store, { email, password }) {
    let loginResponse = await loginAPI.doLogin(email, password)
    processResponse(store, loginResponse)
    return store.getters.getIsAuth
  },

}