<template>
  <div class="home-view">
    
    <!-- ========================================= -->
    <!-- 1. HERO SECTION (Haut de page Violet)     -->
    <!-- ========================================= -->
    <section class="hero-section">
      <!-- GAUCHE : Texte -->
      <div class="content-left">
        <h1>
          Créons ensemble <br />
          <span class="gradient-text">votre présence <br /> digitale</span>
        </h1>
        <p>
          Développeur full-stack passionné, je transforme vos idées en
          solutions web innovantes et performantes.
        </p>
        <div class="buttons">
          <router-link to="/portfolio" class="btn-white">Voir mes projets</router-link>
          <router-link to="/contact" class="btn-transparent">Me contacter</router-link>
        </div>
      </div>

      <!-- DROITE : Cartes Flottantes -->
      <div class="content-right">
        <div class="floating-card card-1">
          <span class="icon">💻</span>
          <div class="text"><h3>Web Development</h3></div>
        </div>
        <div class="floating-card card-2">
          <span class="icon">🎨</span>
          <div class="text"><h3>UI/UX Design</h3></div>
        </div>
        <div class="floating-card card-3">
          <span class="icon">🚀</span>
          <div class="text"><h3>Performance</h3></div>
        </div>
      </div>
    </section>

    <!-- ========================================= -->
    <!-- 2. FEATURES SECTION (TES CARTES BLANCHES) -->
    <!-- ========================================= -->
    <section class="features-section">
      <div class="features-container">
        <!-- Boucle pour afficher les 3 cartes -->
        <div v-for="(item, index) in atouts" :key="index" class="feature-card">
          <div class="icon-large">{{ item.icon }}</div>
          <h3>{{ item.title }}</h3>
          <p>{{ item.text }}</p>
        </div>
      </div>
    </section>

    <!-- ========================================= -->
    <!-- 3. CTA SECTION (Bas de page)              -->
    <!-- ========================================= -->
    <section class="cta-section">
      <div class="cta-content">
        <h2>Prêt à démarrer votre projet ?</h2>
        <p>Discutons de vos besoins et créons quelque chose d'exceptionnel ensemble</p>
        <router-link to="/contact" class="btn-cta">Commencer maintenant</router-link>
      </div>
    </section>

  </div>
</template>

<script setup>
import { ref } from 'vue';

// Données pour les 3 cartes blanches
const atouts = ref([
  {
    icon: '⚡', 
    title: 'Rapide & Performant', 
    text: 'Des applications optimisées pour une expérience utilisateur fluide.'
  },
  {
    icon: '📱', 
    title: 'Responsive Design', 
    text: 'Parfaitement adapté à tous les appareils et tailles d\'écran.'
  },
  {
    icon: '🔒', 
    title: 'Sécurisé', 
    text: 'Code sécurisé suivant les meilleures pratiques du marché.'
  }
]);
</script>

<style scoped>
/* Conteneur global */
.home-view { width: 100%; overflow-x: hidden; }

/* --- 1. HERO SECTION STYLES --- */
.hero-section {
  min-height: 100vh;
  width: 100%;
  background: linear-gradient(135deg, #5b54e8 0%, #7c3aed 100%);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 10%;
  box-sizing: border-box;
  position: relative;
}

.content-left { max-width: 50%; color: white; z-index: 10; }

h1 { font-size: 3.5rem; font-weight: 800; line-height: 1.2; margin-bottom: 20px; }

.gradient-text {
  background: linear-gradient(to right, #ffd700, #a3e635);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}

.hero-section p {
  font-size: 1.2rem;
  color: rgba(255, 255, 255, 0.8);
  margin-bottom: 40px;
  line-height: 1.6;
  max-width: 500px;
}

.buttons { display: flex; gap: 20px; }

.btn-white {
  background: white; color: #5b54e8; padding: 15px 30px; border-radius: 50px;
  text-decoration: none; font-weight: bold; box-shadow: 0 5px 15px rgba(0,0,0,0.2);
  transition: transform 0.2s;
}

.btn-transparent {
  background: transparent; color: white; border: 1px solid rgba(255,255,255,0.5);
  padding: 15px 30px; border-radius: 50px; text-decoration: none; font-weight: bold;
  transition: background 0.2s;
}

.btn-white:hover, .btn-transparent:hover { transform: translateY(-3px); }
.btn-transparent:hover { background: rgba(255,255,255,0.1); }

.content-right {
  position: relative; width: 40%; height: 400px;
  display: flex; flex-direction: column; justify-content: center;
}

.floating-card {
  background: white; color: #333; padding: 15px 25px; border-radius: 12px;
  display: flex; align-items: center; gap: 15px; width: 240px;
  box-shadow: 0 10px 30px rgba(0,0,0,0.2); margin-bottom: 25px;
  animation: float 6s ease-in-out infinite;
}

.icon { font-size: 1.8rem; }
.floating-card h3 { margin: 0; font-size: 1rem; font-weight: 600; color: #333; }

.card-1 { margin-left: 0; }
.card-2 { margin-left: 80px; animation-delay: 1s; }
.card-3 { margin-left: 40px; animation-delay: 2s; }

@keyframes float {
  0% { transform: translateY(0px); }
  50% { transform: translateY(-15px); }
  100% { transform: translateY(0px); }
}


/* --- 2. FEATURES SECTION STYLES (Les cartes blanches) --- */
.features-section {
  background-color: #f9fafb; /* Fond très clair */
  padding: 100px 20px;
}

.features-container {
  max-width: 1200px; margin: 0 auto;
  display: flex; justify-content: center; gap: 30px; flex-wrap: wrap;
}

.feature-card {
  background: white; padding: 40px 30px; border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.05); text-align: center;
  flex: 1; min-width: 280px; max-width: 350px;
  border: 1px solid #f3f4f6; transition: transform 0.3s;
}

.feature-card:hover { transform: translateY(-10px); box-shadow: 0 10px 30px rgba(0,0,0,0.1); }

.icon-large { font-size: 3rem; margin-bottom: 20px; }

.feature-card h3 { font-size: 1.25rem; font-weight: 700; color: #1f2937; margin-bottom: 15px; }
.feature-card p { color: #6b7280; line-height: 1.6; margin: 0; }


/* --- 3. CTA SECTION STYLES (Bas de page) --- */
.cta-section {
  background: linear-gradient(135deg, #5b54e8 0%, #7c3aed 100%);
  padding: 100px 20px; text-align: center; color: white;
}

.cta-content h2 { font-size: 2.5rem; font-weight: 800; margin-bottom: 15px; }
.cta-content p { font-size: 1.2rem; margin-bottom: 40px; opacity: 0.9; }

.btn-cta {
  background-color: white; color: #5b54e8; padding: 15px 40px;
  border-radius: 50px; font-weight: bold; font-size: 1.1rem;
  text-decoration: none; display: inline-block; transition: transform 0.3s;
}
.btn-cta:hover { transform: translateY(-3px); }

/* MOBILE */
@media (max-width: 900px) {
  .hero-section { flex-direction: column; padding-top: 120px; text-align: center; }
  .content-left { max-width: 100%; margin-bottom: 50px; }
  .buttons { justify-content: center; }
  .content-right { width: 100%; align-items: center; }
  .card-1, .card-2, .card-3 { margin-left: 0; }
}
</style>