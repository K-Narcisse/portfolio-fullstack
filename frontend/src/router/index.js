import { createRouter, createWebHistory } from 'vue-router'

// Importation des vues (pages)
import HomeView from '../views/HomeView.vue'
import PortfolioView from '../views/PortfolioView.vue'
import ContactView from '../views/ContactView.vue'
import AboutView from '../views/AboutView.vue'
import ServicesView from '../views/ServicesView.vue'

// 👇 C'EST CETTE LIGNE QUI MANQUAIT :
import VideoCallView from '../views/VideoCallView.vue'
import LibraryView from '../views/LibraryView.vue' // <-- IMPORT de LibraryView
import AdminView from '../views/AdminView.vue' // <-- IMPORT AdminView


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

// ... dans routes :
{ 
  path: '/admin', 
  name: 'admin', 
  component: AdminView 
},

    // ...
    // ... autres routes
    { path: '/library', name: 'library', component: LibraryView }, // <-- ROUTE
  // ...
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/portfolio',
      name: 'portfolio',
      component: PortfolioView
    },
    {
      path: '/contact',
      name: 'contact',
      component: ContactView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/services',
      name: 'services',
      component: ServicesView
    },
    {
      path: '/video',
      name: 'video',
      component: VideoCallView
    }
  ],
  // Cette option permet de revenir tout en haut de la page quand on change de menu
  scrollBehavior() {
    return { top: 0 }
  }
})

export default router