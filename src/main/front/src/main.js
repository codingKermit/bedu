import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

/** Bootstrap, Bootstrap-vue-3 */
import { BootstrapVue3 } from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

/** fontawesome import */
import { library } from '@fortawesome/fontawesome-svg-core'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'


library.add(fab, fas, far)

/** axios 기본 URL */
axios.defaults.baseURL="http://localhost:8090";

import VueSweetalert2 from 'vue-sweetalert2'
import 'sweetalert2/dist/sweetalert2.min.css'
const app = createApp(App);
app.config.globalProperties.axios = axios;

/** 비에듀 테마 컬러 블랙, 블루, 옐로우 */
app.config.globalProperties.onlyblack = "#231815";
app.config.globalProperties.spaceblue = "#303076";
app.config.globalProperties.dinamicYellow = "#EDC268";



app
.use(router)
.use(BootstrapVue3)
.use(VueSweetalert2)
.component("font-awesome-icon",FontAwesomeIcon)
.mount('#app')
