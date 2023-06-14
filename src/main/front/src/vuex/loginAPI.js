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
        console.log(getUserInfoPromise)
        const [userInfoResponse] = await Promise.all([getUserInfoPromise])
        console.log(userInfoResponse.data)
        if (userInfoResponse.data.length === 0) {
          return 'notFound'
        } else {
          localStorage.setItem('user_token', userInfoResponse.data.user_token)
          return userInfoResponse
        }
      } catch (err) {
        console.error(err)
      }
    }
  }