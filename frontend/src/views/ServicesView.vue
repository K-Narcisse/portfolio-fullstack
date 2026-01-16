<template>
  <div class="services-page">
    
    <!-- 1. HEADER (Bandeau violet inchangé) -->
    <div class="services-header">
      <h1>Services</h1>
      <p>Des solutions sur mesure pour concrétiser vos projets digitaux</p>
    </div>

    <!-- 2. SECTION SERVICES (Fond BLANC maintenant) -->
    <div class="main-container">
      <div class="services-grid">
        <div v-for="(service, index) in services" :key="index" class="service-card">
          <div class="icon-3d">{{ service.icon }}</div>
          <h3>{{ service.title }}</h3>
          <p>{{ service.description }}</p>
        </div>
      </div>
    </div>

    <!-- 3. SECTION PROCESSUS (Fond GRIS CLAIR maintenant) -->
    <div class="process-full-width">
      <div class="inner-container">
        
        <h2>Mon processus de travail</h2>
        
        <div class="process-steps">
          <div v-for="step in process" :key="step.number" class="step-item">
            <div class="step-circle">{{ step.number }}</div>
            <h3>{{ step.title }}</h3>
            <p>{{ step.description }}</p>
          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue';

const services = ref([
  { title: "Développement Web", description: "Création d'applications web modernes et performantes avec les dernières technologies (Vue.js, React, TypeScript).", icon: "💻" },
  { title: "API REST", description: "Conception et développement d'APIs RESTful robustes et sécurisées avec Spring Boot et Node.js.", icon: "🔌" },
  { title: "UI/UX Design", description: "Design d'interfaces utilisateur intuitives et attractives avec une attention particulière à l'expérience utilisateur.", icon: "🎨" },
  { title: "Optimisation Performance", description: "Amélioration des performances de vos applications web pour une expérience utilisateur optimale.", icon: "⚡" },
  { title: "Base de Données", description: "Conception et optimisation de bases de données relationnelles (PostgreSQL, MySQL).", icon: "💾" },
  { title: "Maintenance & Support", description: "Support technique continu et maintenance de vos applications web existantes.", icon: "🛠️" }
]);

const process = ref([
  { number: 1, title: "Analyse", description: "Compréhension approfondie de vos besoins et objectifs." },
  { number: 2, title: "Conception", description: "Design et architecture adaptés à votre projet." },
  { number: 3, title: "Développement", description: "Codage avec les meilleures pratiques et technologies." },
  { number: 4, title: "Livraison", description: "Tests, déploiement et formation complète." }
]);
</script>

<style scoped>
/* FOND GLOBAL BLANC (Pour la partie Services) */
.services-page {
  background-color: #ffffff; 
  min-height: 100vh;
}

/* --- HEADER --- */
.services-header {
  background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
  color: white;
  text-align: center;
  padding: 140px 20px 80px 20px;
}
.services-header h1 { font-size: 3.5rem; font-weight: 800; margin: 0 0 15px 0; }
.services-header p { font-size: 1.2rem; opacity: 0.9; }

/* --- GRILLE SERVICES --- */
.main-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 80px 20px;
}

.services-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 30px;
}

/* STYLE DES CARTES (Fond blanc par défaut, mais avec effet au survol) */
.service-card {
  background: white;
  padding: 40px 30px;
  border-radius: 20px;
  text-align: center;
  /* Ombre très légère au repos */
  box-shadow: 0 4px 20px rgba(0,0,0,0.03);
  /* Bordure très fine grise au repos */
  border: 1px solid #f3f4f6; 
  transition: all 0.3s ease; /* Animation fluide */
}

/* --- L'EFFET "JOLI BLEU PRO" AU SURVOL --- */
.service-card:hover {
  transform: translateY(-8px); /* La carte monte un peu */
  
  /* La bordure devient Bleue (Indigo) */
  border-color: #4f46e5; 
  
  /* Une ombre portée bleutée pour l'effet "Glow" */
  box-shadow: 0 15px 30px rgba(79, 70, 229, 0.15); 
}

.icon-3d { font-size: 4rem; margin-bottom: 20px; }
.service-card h3 { font-size: 1.5rem; font-weight: 700; color: #1f2937; margin-bottom: 15px; }
.service-card p { font-size: 1rem; color: #6b7280; line-height: 1.6; }


/* --- SECTION PROCESSUS (FOND GRIS CLAIR MAINTENANT) --- */
.process-full-width {
  width: 100%;
  background-color: #f9fafb; /* <--- CHANGEMENT ICI (Gris clair) */
  padding: 100px 0; 
  border-top: 1px solid #e5e7eb;
}

.inner-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
  text-align: center;
}

.inner-container h2 {
  font-size: 2.5rem;
  font-weight: 800;
  color: #1f2937;
  margin-bottom: 80px;
}

.process-steps {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 30px;
  flex-wrap: wrap;
}

.step-item {
  flex: 1;
  min-width: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.step-circle {
  width: 80px;
  height: 80px;
  background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
  color: white;
  font-size: 2.2rem;
  font-weight: 800;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 30px;
  box-shadow: 0 10px 25px rgba(99, 102, 241, 0.25);
}

.step-item h3 {
  font-size: 1.3rem;
  font-weight: 700;
  color: #1f2937;
  margin-bottom: 15px;
}

.step-item p {
  font-size: 1rem;
  color: #6b7280;
  line-height: 1.6;
  max-width: 260px;
}

/* RESPONSIVE */
@media (max-width: 900px) {
  .process-steps { flex-direction: column; gap: 60px; }
}
</style>