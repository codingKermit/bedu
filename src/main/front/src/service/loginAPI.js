import axios from 'axios'

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
  async doLogin(email, password) {
    try {
      const getUserInfoPromise = getUserInfo(email, password)
      const [userInfoResponse] = await Promise.all([getUserInfoPromise])
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