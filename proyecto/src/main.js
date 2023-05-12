import { createApp } from 'vue'
import App from './App.vue'
import router from './router';

import { IonicVue } from '@ionic/vue';

/* Core CSS required for Ionic components to work properly */
import '@ionic/vue/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/vue/css/normalize.css';
import '@ionic/vue/css/structure.css';
import '@ionic/vue/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/vue/css/padding.css';
import '@ionic/vue/css/float-elements.css';
import '@ionic/vue/css/text-alignment.css';
import '@ionic/vue/css/text-transformation.css';
import '@ionic/vue/css/flex-utils.css';
import '@ionic/vue/css/display.css';

/* Theme variables */
import './theme/variables.css';

// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

//////////////////////////////////////Firebase//////////////////////////////////////
// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyAnr9APkry3XeB9NF69dvGkkexeqa5qTI0",
  authDomain: "proyecto-b55ad.firebaseapp.com",
  projectId: "proyecto-b55ad",
  storageBucket: "proyecto-b55ad.appspot.com",
  messagingSenderId: "307174819683",
  appId: "1:307174819683:web:30e46fc76241bd0e86f556"
};

// Initialize Firebase
const appFirebase = initializeApp(firebaseConfig);
//////////////////////////////////////Firebase//////////////////////////////////////

const app = createApp(App)
  .use(IonicVue)
  .use(appFirebase)
  .use(router);
  
router.isReady().then(() => {
  app.mount('#app');
});