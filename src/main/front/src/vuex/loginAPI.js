import axios from 'axios'

const getUserInfo = (email, password) => {
  const reqData = {
    'email': email,
    'password': password
  }

  return axios.post('/api/login', reqData, {
    headers: {
      'Content-type': 'application/json'
    }
  })
}

// 로그인 시간 기록 함수
const setLoginTime = () => {
  const currentTime = new Date().getTime()
  const loginTime = currentTime + 3600000; // 1시간(3,600,000 밀리초) 추가
  localStorage.setItem('login_time', loginTime.toString())
}

// 클릭 이벤트 핸들러
const handleClick = () => {
  const userToken = localStorage.getItem('user_token')
  if (userToken) {
    setLoginTime()
  }
}

// 이벤트 리스너 등록
document.addEventListener('click', handleClick)

// 주기적인 확인 함수
const checkLogoutTime = () => {
  const loginTime = localStorage.getItem('login_time')
  if (loginTime) {
    const currentTime = new Date().getTime()
    if (currentTime >= parseInt(loginTime, 10)) {
      // 로그아웃 처리
      localStorage.removeItem('user_token')
      localStorage.removeItem('login_time')
      // 기타 로그아웃 관련 동작 수행
      window.location.reload()
    }
  }
}

// 로그인 함수 수정
export default {
  async doLogin(email, password) {
    try {
      const getUserInfoPromise = getUserInfo(email, password)
      const [userInfoResponse] = await Promise.all([getUserInfoPromise])
      if (userInfoResponse.data.length === 0) {
        return 'notFound'
      } else {
        // 로그인 성공 시 로그인 시간 기록
        setLoginTime()
        localStorage.setItem('user_token', userInfoResponse.data.user_token)
        return userInfoResponse
      }
    } catch (err) {
      console.error(err)
    }
  }
}

// 주기적인 확인 설정 (예: 1초마다 확인)
setInterval(checkLogoutTime, 1000)