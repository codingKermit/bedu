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

createApp(App).use(router)
.use(router)
.use(BootstrapVue3)
.component("font-awesome-icon",FontAwesomeIcon)
.mount('#app')
