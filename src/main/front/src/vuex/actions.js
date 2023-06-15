import {EMAIL, IS_AUTH, ERROR_STATE, NICKNAME} from './mutation_types'
import loginAPI from './loginAPI'

let setEmail = ({commit}, data) => {
  commit(EMAIL, data)
}

let setErrorState = ({commit}, data) => {
  commit(ERROR_STATE, data)
}

let setIsAuth = ({commit}, data) => {
  commit(IS_AUTH, data)
}

let setNickname = ({ commit }, data) => {
  commit(NICKNAME, data)
}

// 백엔드에서 반환한 결과값을 가지고 로그인 성공 실패 여부를 vuex에 넣어준다.
let processResponse = (store, loginResponse) => {
  switch (loginResponse) {
    case 'notFound':
      setErrorState(store, 'Wrong ID or Password')
      setIsAuth(store, false)
      break
    default:
      setEmail(store, loginResponse.email)
      setNickname(store, loginResponse.nickname)
      setErrorState(store, '')
      setIsAuth(store, true)
  }
}

export default {
  async login(store, {email, password}) {
    let loginResponse = await loginAPI.doLogin(email, password)
    processResponse(store, loginResponse)
    return store.getters.getIsAuth  // 로그인 결과를 리턴한다
  },
}