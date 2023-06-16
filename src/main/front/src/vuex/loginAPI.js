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
  
export default {
    async doLogin(email, password) {
        try {
            const getUserInfoPromise = getUserInfo(email, password)
            const [userInfoResponse] = await Promise.all([getUserInfoPromise])
            if (userInfoResponse.data.length === 0) {
                return 'notFound'
            } else {
                alert("로그인을 성공하셨습니다. 메인페이지로 이동합니다.")
                localStorage.setItem('user_token', userInfoResponse.data.user_token)
                return userInfoResponse
            }
        } catch (err) {
            console.error(err)
        }
    }
}