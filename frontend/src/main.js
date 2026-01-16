import { createApp } from 'vue'
import App from './App.vue'
import router from './router' // <--- On importe le fichier que tu viens de créer

const app = createApp(App)

app.use(router) // <--- On active le routeur ici
app.mount('#app')