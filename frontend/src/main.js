import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from './common/axios';
import mitt from 'mitt';
import {VueMasonryPlugin} from "vue-masonry/src/masonry-vue3.plugin";

// createApp(App).use(store).use(router).use(VueSplide).use(axios).use(infiniteScroll).mount("#app");
const emitter=mitt()
let app = createApp(App)
app.config.globalProperties.emitter = emitter
app.use(VueMasonryPlugin)
app.use(store).use(router).use(axios)
app.mount('#app')