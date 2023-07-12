import * as types from './mutation_types'

export default {
    [types.EMAIL](state, email) {
        state.email = email
    },
    [types.NICKNAME](state, nickname) {
        state.nickname = nickname
    },
    [types.USERNUM](state, usernum) {
        state.usernum = usernum
    },
    [types.ERROR_STATE](state, errorState) {
        state.errorState = errorState
    },
    [types.IS_AUTH](state, isAuth) {
        state.isAuth = isAuth
    },
    [types.CLS](state, cls) {
        state.cls = cls
    },
    [types.LESSONS](state, lessons) {
        state.lessons = lessons
    },
    [types.CBNUMLIST](state, cbnumList) {
        state.cbnumList = cbnumList
    },
    [types.CBNUMLIST_REMOVE](state, commNum) {
        const index = state.cbnumList.indexOf(commNum);
        if (index > -1) {
          state.cbnumList.splice(index, 1);
        }
        localStorage.setItem('cbnumList', JSON.stringify(state.cbnumList));
    },
    [types.CBNUMLIST_ADD](state, num) {
        if (!state.cbnumList.includes(num)) {
          state.cbnumList.push(num);
          localStorage.setItem('cbnumList', JSON.stringify(state.cbnumList));
        }
    }
}