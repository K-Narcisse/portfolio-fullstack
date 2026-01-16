<template>
  <div class="about-page">
    
    <!-- HEADER -->
    <div class="about-header">
      <h1>À propos</h1>
      <p>Passionné par la création d'expériences digitales exceptionnelles</p>
    </div>

    <!-- CONTENU -->
    <div class="main-container">
      
      <!-- BIO -->
      <div class="bio-section">
        
        <div class="bio-image-wrapper">
          <!-- IMPORTANT : On utilise :src (avec deux points) pour lier la variable -->
          <img :src="avatarImg" alt="Avatar Développeur" class="avatar-img">
        </div>
        
        <div class="bio-content">
          <h2>Bonjour, je suis développeur full-stack</h2>
          
          <p>
            Avec plusieurs années d'expérience dans le développement web, je me
            spécialise dans la création d'applications modernes, performantes et
            élégantes. Ma passion pour la technologie et le design me pousse à
            créer des solutions qui font la différence.
          </p>
          
          <p>
            Je maîtrise un large éventail de technologies, allant du frontend avec
            Vue.js et React, au backend avec Java Spring Boot et Node.js. Mon
            approche allie expertise technique, créativité et attention aux détails.
          </p>
          
          <!-- Stats -->
          <div class="stats-row">
            <div class="stat-item">
              <span class="number">5+</span>
              <span class="label">Années d'expérience</span>
            </div>
            <div class="stat-item">
              <span class="number">50+</span>
              <span class="label">Projets réalisés</span>
            </div>
            <div class="stat-item">
              <span class="number">100%</span>
              <span class="label">Clients satisfaits</span>
            </div>
          </div>
        </div>

      </div>

      <!-- COMPÉTENCES -->
      <div class="skills-section">
        <h2 class="skills-title">Mes Compétences Techniques</h2>
        <div class="skills-grid">
          <div class="skill-card" v-for="skill in skills" :key="skill.name">
            <div class="skill-icon">{{ skill.icon }}</div>
            <h3>{{ skill.name }}</h3>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 1. IMPORTATION DE L'IMAGE LOCALE
// '@' signifie le dossier 'src'
import avatarImg from '@/assets/avatar.png';

const skills = ref([
  { name: 'Java & Spring Boot', icon: '☕' },
  { name: 'Vue.js 3 & Vite', icon: '💚' },
  { name: 'HTML5 & CSS3', icon: '🎨' },
  { name: 'SQL & Bases de données', icon: '🗄️' },
  { name: 'Git & DevOps', icon: '🚀' },
  { name: 'API REST', icon: '🔗' },
]);
</script>

<style scoped>
/* Le CSS reste exactement le même qu'avant, je le remets pour être sûr */

.about-page { background-color: white; min-height: 100vh; }

.about-header {
  background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
  color: white; text-align: center; padding: 120px 20px 80px 20px;
}
.about-header h1 { font-size: 3rem; font-weight: 800; margin: 0 0 10px 0; }
.about-header p { font-size: 1.2rem; opacity: 0.9; margin: 0; }

.main-container { max-width: 1200px; margin: 0 auto; padding: 80px 20px; }

.bio-section { display: flex; align-items: center; gap: 80px; margin-bottom: 100px; }

.bio-image-wrapper {
  flex: 1;
  background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
  border-radius: 20px; display: flex; justify-content: center; align-items: center;
  aspect-ratio: 1 / 1; position: relative; overflow: hidden;
  box-shadow: 0 20px 40px rgba(99, 102, 241, 0.2);
}

.avatar-img {
  width: 70%; height: auto; object-fit: contain;
  filter: drop-shadow(0 10px 15px rgba(0,0,0,0.2));
  transition: transform 0.3s ease;
}
.bio-image-wrapper:hover .avatar-img { transform: scale(1.05); }

.bio-content { flex: 1; }
.bio-content h2 { font-size: 2.5rem; font-weight: 800; color: #1f2937; margin-bottom: 30px; line-height: 1.2; }
.bio-content p { font-size: 1.1rem; color: #4b5563; line-height: 1.8; margin-bottom: 20px; }

.stats-row { display: flex; gap: 60px; margin-top: 40px; padding-top: 20px; border-top: 1px solid #f3f4f6; }
.stat-item { display: flex; flex-direction: column; }
.stat-item .number { font-size: 2.5rem; font-weight: 800; color: #4f46e5; line-height: 1; margin-bottom: 5px; }
.stat-item .label { font-size: 0.9rem; color: #6b7280; font-weight: 500; }

.skills-title { text-align: center; font-size: 2rem; color: #1f2937; margin-bottom: 50px; }
.skills-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 30px; }

.skill-card {
  background: white; padding: 30px; border-radius: 15px; text-align: center;
  box-shadow: 0 4px 20px rgba(0,0,0,0.05); border: 1px solid #f3f4f6; transition: transform 0.3s;
}
.skill-card:hover { transform: translateY(-5px); border-color: #c7d2fe; }
.skill-icon { font-size: 2.5rem; margin-bottom: 15px; }
.skill-card h3 { font-size: 1.1rem; color: #374151; font-weight: 600; margin: 0; }

@media (max-width: 900px) {
  .bio-section { flex-direction: column; gap: 40px; }
  .bio-image-wrapper { width: 100%; aspect-ratio: 4/3; }
  .stats-row { justify-content: space-between; gap: 20px; }
}
</style>