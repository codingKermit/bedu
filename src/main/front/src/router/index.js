import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'main',
    component: () => import('../views/HomeView.vue')
  }
  ,
  {
    path: '/course',
    name: 'course',

    component: () => import('../views/lecture/LectureCourse.vue')
  }
  , 
  {
    path : '/lectureDetail',
    name : 'lectureDetail',

    component : () => import('@/views/lecture/LectureDetail.vue')
  }
  ,
  {
    path : '/lectureField',
    name : 'lectureField',

    component : () => import('@/views/lecture/LectureField.vue')
  }
  ,
  {
    path: '/review',
    name: 'review',
    component: () => import('../views/Review/StudyReview.vue')
  }
  ,
  {
    path: '/companyStudy',
    name: 'companyStudy',
    component: () => import('../views/CompanyStudy.vue')
  }
  ,
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/user/LoginView.vue')
  }
  ,
  {
    path: '/regist',
    name: 'regist',
    component: () => import('../views/user/RegistView.vue')
  }

  ,
  {
    path: '/community',
    name: 'community',
    component: () => import('../views/comm/FreeBoardView.vue')
  }

  ,
  {
    path: '/communitywrite',
    name: 'communitywrite',
    component: () => import('../views/comm/FreeBoardWrite.vue')
  }
  ,
  {
    path: '/communitydetail/:comm_num',
    name: 'communitydetail',
    component: () => import('../views/comm/FreeBoardDetail.vue')
  }
  ,
  {
    path: '/communityedit/:num',
    name: 'communityedit',
    component: () => import('../views/comm/FreeBoardEdit.vue')
  }
  ,
  {
    path: '/csc',
    name: 'csc',
    component: () => import('../views/Notice/CscView.vue')
  }
  ,
  {
    path: '/tou',
    name: 'tou',
    component: () => import('../views/Notice/TouView.vue')
  }
  ,
  {
    path: '/privacy',
    name: 'privacy',
    component: () => import('../views/Notice/PrivacyView.vue')
  }
  ,
  {
    path: '/reviewWrite',
    name: 'reviewWrite',
    component: () => import('../views/Review/ReviewWrite.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  // eslint-disable-next-line
  scrollBehavior (to, from, savedPosition) {
    return { top: 0 }
  }
})

export default router
