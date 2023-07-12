import * as types from './mutation_types'
import { CBNUMLIST_REMOVE, CBNUMLIST_ADD } from './mutation_types'

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
    [CBNUMLIST_REMOVE](state, num) {
        const index = state.cbnumList.indexOf(num);
        if (index !== -1) {
          state.cbnumList.splice(index, 1);
        }
    },
    [CBNUMLIST_ADD](state, num) {
        if (!state.cbnumList.includes(num)) {
          state.cbnumList.push(num);
        }
    },
}