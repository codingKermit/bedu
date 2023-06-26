import { EMAIL, IS_AUTH, ERROR_STATE, NICKNAME, USERNUM, CLS } from './mutation_types'
import loginAPI from './loginAPI'
import jwt_decode from 'jwt-decode'

// 이메일을 설정하는 액션
let setEmail = ({ commit }, data) => {
    commit(EMAIL, data)
}

// 에러 상태를 설정하는 액션
let setErrorState = ({ commit }, data) => {
    commit(ERROR_STATE, data)
}

// 인증 상태를 설정하는 액션
let setIsAuth = ({ commit }, data) => {
    commit(IS_AUTH, data)
}

// 닉네임을 설정하는 액션
let setNickname = ({ commit }, data) => {
    commit(NICKNAME, data)
}

// 사용자 번호를 설정하는 액션
let setUsernum = ({ commit }, data) => {
    commit(USERNUM, data)
}

// 클라스를 설정하는 액션
let setCls = ({ commit }, data) => {
    commit(CLS, data)
}

// 백엔드에서 반환한 결과값을 처리하여 로그인 성공 여부를 Vuex에 저장하는 함수
let processResponse = (store, loginResponse) => {
    let decodedToken;
    switch (loginResponse) {
        case 'notFound':
            // ID 또는 비밀번호가 잘못되었음을 에러 상태에 저장
            setErrorState(store, 'Wrong ID or Password')
            // 인증 상태를 실패로 설정
            setIsAuth(store, false)
            break
        default:
            decodedToken = jwt_decode(loginResponse.data.user_token);
            // 토큰을 해독하여 사용자 번호, 닉네임, 이메일, 클라스를 설정
            setUsernum(store, decodedToken.usernum)
            setNickname(store, decodedToken.nickname)
            setEmail(store, decodedToken.email)
            setCls(store, decodedToken.cls)
            // 에러 상태를 초기화
            setErrorState(store, '')
            // 인증 상태를 성공으로 설정
            setIsAuth(store, true)
    }
}

export default {
    // 로그인 액션
    async login(store, { email, password }) {
        // 로그인 API 호출
        let loginResponse = await loginAPI.doLogin(email, password)
        // 백엔드 응답 처리
        processResponse(store, loginResponse)
        // 로그인 결과 반환
        return store.getters.getIsAuth
    },
}