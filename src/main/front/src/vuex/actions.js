import {EMAIL, IS_AUTH, ERROR_STATE, NICKNAME, USERNUM, CLS} from './mutation_types'
import loginAPI from './loginAPI'
import jwt_decode from 'jwt-decode'

let setEmail = ({ commit }, data) => {
    commit(EMAIL, data)
}

let setErrorState = ({ commit }, data) => {
    commit(ERROR_STATE, data)
}

let setIsAuth = ({ commit }, data) => {
    commit(IS_AUTH, data)
}

let setNickname = ({ commit }, data) => {
    commit(NICKNAME, data)
}

let setUsernum = ({ commit }, data) => {
    commit(USERNUM, data)
}

let setCls = ({ commit }, data) => {
    commit(CLS, data)
}

// 백엔드에서 반환한 결과값을 가지고 로그인 성공 실패 여부를 vuex에 넣어준다.
let processResponse = (store, loginResponse) => {
    let decodedToken;
    switch (loginResponse) {
        case 'notFound':
        setErrorState(store, 'Wrong ID or Password')
        setIsAuth(store, false)
        break
        default:
        decodedToken = jwt_decode(loginResponse.data.user_token);
        setUsernum(store, decodedToken.usernum)
        setNickname(store, decodedToken.nickname)
        setEmail(store, decodedToken.email)
        setCls(store, decodedToken.cls)
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