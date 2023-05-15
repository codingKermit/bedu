import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',
    name: 'main',
    component: () => import('../views/MainView.vue')
  },
  {
    path: '/lecture',
    name: 'lecture',
    component: () => import('../views/LectureView.vue')
  }
  ,
  {
    path: '/lecture2',
    name: 'lecture2',
    component: () => import('../views/LectureView2.vue')
  }
  ,
  {
    path: '/community',
    name: 'community',
    component: () => import('../views/CommunityView.vue')
  }
  ,
  {
    path: '/review',
    name: 'review',
    component: () => import('../views/StudyReview.vue')
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
    component: () => import('../views/LoginView.vue')
  }
  ,
  {
    path: '/regist',
    name: 'regist',
    component: () => import('../views/RegistView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
