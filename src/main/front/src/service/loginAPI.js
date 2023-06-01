import axios from 'axios'

// 사용자 정보를 가져오는 함수
// 주어진 이메일과 비밀번호를 사용하여 백엔드 API에 POST 요청을 보내고 응답을 받아옴
const getUserInfo = (email, password) => {
  const reqData = {
    'email': email,
    'password': password
  }
  let serverUrl = '//localhost:8090'

  return axios.post(serverUrl + '/api/login', reqData, {
    headers: {
      'Content-type': 'application/json'
    }
  })
}

export default {
  // 로그인을 수행하는 비동기 함수
  async doLogin(email, password) {
    try {
      // 사용자 정보를 가져오는 프로미스 생성
      const getUserInfoPromise = getUserInfo(email, password)

      // 사용자 정보를 비동기로 가져옴
      const [userInfoResponse] = await Promise.all([getUserInfoPromise])

      // 사용자 정보가 없는 경우 'notFound' 반환
      if (userInfoResponse.data.length === 0) {
        return 'notFound'
      } else {
        // 사용자 정보가 있는 경우 토큰을 로컬 스토리지에 저장하고 사용자 정보 반환
        localStorage.setItem('user_token', userInfoResponse.data.user_token)
        return userInfoResponse
      }
    } catch (err) {
      console.error(err)
    }
  }
}