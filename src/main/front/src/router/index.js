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
  ,{
    path : '/lectureCategories',
    name : 'lectureCategories',

    component : () => import('@/views/lecture/LectureCategories.vue')
  },
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
    path: '/comm/freBd',
    name: 'community',
    component: () => import('../views/comm/FreeBoardView.vue')
  }

  ,
  {
    path: '/comm/freBdWrite',
    name: 'communitywrite',
    component: () => import('../views/comm/FreeBoardWrite.vue')
  }
  ,
  {
    path: '/comm/freBdDetail/:num',
    name: 'communitydetail',
    component: () => import('../views/comm/FreeBoardDetail.vue')
  }
  ,
  {
    path: '/comm/freBdEdit/:num',
    name: 'communityedit',
    component: () => import('../views/comm/FreeBoardEdit.vue')
  }

  ,
  {
    path: '/comm/qna',
    name: 'qnaBoard',
    component: () => import('../views/comm/QnaBoardView.vue')
  }
  ,
  {
    path: '/comm/qnaWrite',
    name: 'qnaBoardwrite',
    component: () => import('../views/comm/QnaBoardWrite.vue')
  }
  ,
  {
    path: '/comm/qnaDetail/:num',
    name: 'qnaBoarddetail',
    component: () => import('../views/comm/QnaBoardDetail.vue')
  }
  ,
  {
    path: '/comm/qnaEdit/:num',
    name: 'qnaBoardedit',
    component: () => import('../views/comm/QnaBoardEdit.vue')
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
