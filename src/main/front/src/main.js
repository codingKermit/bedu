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


/** sweetalert 임포트 */
import VueSweetalert2 from 'vue-sweetalert2'
import 'sweetalert2/dist/sweetalert2.min.css'

/** axios, router 전역 변수로 사용 */
const app = createApp(App);
app.config.globalProperties.axios = axios;
app.config.globalProperties.router = router;

/** CkEditor5 임포트 */



/** 비에듀 테마 컬러 블랙, 블루, 옐로우 값 전역 변수 사용*/
app.config.globalProperties.onlyblack = "#231815";
app.config.globalProperties.spaceblue = "#303076";
app.config.globalProperties.dinamicYellow = "#EDC268";


app
.use(router)
.use(BootstrapVue3)
.use(VueSweetalert2)
.component("font-awesome-icon",FontAwesomeIcon)
.mount('#app')