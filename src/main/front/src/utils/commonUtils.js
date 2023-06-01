import router from "@/router";
import axios from "axios";

// 공통으로 사용하는 유틸함수들 모음집
// vue 파일에서 사용할때는 전역함수처럼 this.$함수명 이런식으로 사용 가능

export default {
    install(app) {
        console.log(app)
        const globalProperties = app?.config.globalProperties
        globalProperties.$axiosSend = commonAxios.axiosSend;
        globalProperties.$routerPush = commonRouter.routerPush;
        globalProperties.$isNotEmpty = commonValid.isNotEmpty;
        // globalProperties.$axiosSend = commonAxios.axiosSend;
        // globalProperties.$axiosSend = commonAxios.axiosSend;
        // globalProperties.$axiosSend = commonAxios.axiosSend;

        
    }
}

// axios함수들
export const commonAxios = {
    // axios (method(필수값),보낼주소(필수값), 보낼 파라미터)
    // 호출예시 : this.$axiosSend('get', '/api/something', {name: '123', value: '456'}, true)
    axiosSend(method, url, params) {
        console.log('Axios ready : method = %o, url = %o', method, url)
        return axios({
            method,
            url, 
            params
        })
    }
}

// router함수들
export const commonRouter = {
    // router push함수 url: 라우트 주소, query: 라우트에 태워 보낼 데이터, hasData: true: 보낼 데이터값 있음(기본), false: 주소로 호출만 함) 
    // 호출예시 : this.$routerPush('/url', {name: '123', value: '456'}, true)
    routerPush: (url, query, hasData) => {
        console.log('router push ::: url = %o, query = %o, hasData = %o', url, query, hasData)
        // 1. router에 담아 보낼 데이터가 있다면
        if (hasData) {
            router.push({
                name: url,
                query
            })
        } else { // 2. router에 담아 보낼 데이터가 없이 router호출만
            router.push(url)
        }
    }
}

// 인피니티스크롤 공통쪽으로 개발
export const commonScroll = {

}



// 공통 밸리데이션
export const commonValid = {
    // 빈 데이터 감지용
    isNotEmpty: (value) => {
        console.log('isEmpty ::: ', value)
        if (value === '') return false
        if (value === null) return false
        if (value === undefined) return false
        if (value === {}) return false
        if (Array?.isArray(value) && value === []) return false
        return true
    }
    
}

