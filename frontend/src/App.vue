<script setup>
import { RouterLink, RouterView } from 'vue-router';
import { ref, onMounted } from 'vue';

// --- LOGIQUE PHOTO DE PROFIL ---
const fileInput = ref(null);
const profileImage = ref('https://cdn-icons-png.flaticon.com/512/3135/3135715.png');

// 🔒 GESTION DES DROITS
const isAdmin = ref(true); // Mets sur 'true' pour modifier la photo, 'false' pour bloquer

onMounted(() => {
  const savedImage = localStorage.getItem('userAvatar');
  if (savedImage) {
    profileImage.value = savedImage;
  }
});

const triggerFileInput = () => {
  if (isAdmin.value) {
    fileInput.value.click();
  } else {
    alert("Seul l'administrateur peut modifier cette photo.");
  }
};

const handleFileUpload = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      profileImage.value = e.target.result;
      localStorage.setItem('userAvatar', e.target.result);
    };
    reader.readAsDataURL(file);
  }
};
</script>

<template>
  <div class="app-container">
    
    <!-- 1. BARRE DE NAVIGATION -->
    <nav class="navbar">
      
      <!-- LOGO -->
      <div class="logo">Portfolio</div>

      <!-- MENU -->
      <div class="nav-links">
        <RouterLink to="/">Accueil</RouterLink>
        <RouterLink to="/about">À propos</RouterLink>
        <RouterLink to="/services">Services</RouterLink>
        <RouterLink to="/library">Bibliothèque</RouterLink> 
        <RouterLink to="/video" class="nav-video">Visio 📹</RouterLink>
        <RouterLink to="/contact" class="btn-contact">Contact</RouterLink>

        <!-- === PHOTO DE PROFIL STYLÉE === -->
        <div 
          class="profile-wrapper" 
          :class="{ 'editable': isAdmin }" 
          @click="triggerFileInput"
          :title="isAdmin ? 'Modifier la photo' : 'Admin'"
        >
          <div class="gradient-ring"></div>
          <img :src="profileImage" class="nav-profile-pic" alt="Profile" />
          <div v-if="isAdmin" class="edit-icon">📷</div>
        </div>

        <input type="file" ref="fileInput" class="hidden-input" accept="image/*" @change="handleFileUpload" />
      </div>

    </nav>

    <!-- 2. CONTENU PRINCIPAL -->
    <main>
      <RouterView />
    </main>

    <!-- 3. PIED DE PAGE (REMIS COMME AVANT) -->
    <footer class="app-footer">
      <div class="footer-container">
        
        <!-- Colonne 1 : Info -->
        <div class="footer-col">
          <div class="footer-logo">Portfolio</div>
          <p>
            Création d'expériences numériques modernes et performantes. 
            Transformons vos idées en réalité.
          </p>
        </div>

        <!-- Colonne 2 : Liens Rapides -->
        <div class="footer-col">
          <h3>Navigation</h3>
          <ul class="footer-links">
            <li><RouterLink to="/">Accueil</RouterLink></li>
            <li><RouterLink to="/about">À propos</RouterLink></li>
            <li><RouterLink to="/services">Services</RouterLink></li>
            <li><RouterLink to="/library">Bibliothèque</RouterLink></li>
            <li><RouterLink to="/contact">Contact</RouterLink></li>
          </ul>
        </div>

        <!-- Colonne 3 : Réseaux Sociaux -->
        <div class="footer-col">
          <h3>Me suivre</h3>
          <div class="social-icons">
            
            <!-- Facebook -->
            <a href="https://www.facebook.com/profile.php?id=61577695450924" target="_blank" class="social-btn" title="Facebook">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>
            </a>

            <!-- WhatsApp -->
            <a href="https://wa.me/22677487120" target="_blank" class="social-btn" title="WhatsApp">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z"></path>
              </svg>
            </a>

            <!-- LinkedIn -->
            <a href="#" target="_blank" class="social-btn" title="LinkedIn">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M16 8a6 6 0 0 1 6 6v7h-4v-7a2 2 0 0 0-2-2 2 2 0 0 0-2 2v7h-4v-7a6 6 0 0 1 6-6z"></path><rect x="2" y="9" width="4" height="12"></rect><circle cx="4" cy="4" r="2"></circle></svg>
            </a>

          </div>
        </div>
      
      </div> 

      <!-- Copyright -->
      <div class="footer-bottom">
        <p>&copy; 2025 Mon Portfolio. Tous droits réservés.</p>
      </div>
    </footer>

  </div>
</template>

<style>
/* Reset global */
body { margin: 0; padding: 0; font-family: 'Plus Jakarta Sans', sans-serif; background-color: #f9fafb; }
.app-container { min-height: 100vh; display: flex; flex-direction: column; overflow-x: hidden; }

main { flex: 1; }

/* NAVBAR */
.navbar {
  display: flex; align-items: center; position: fixed; top: 0; left: 0; width: 100%; height: 80px; padding: 0 50px; z-index: 9999; box-sizing: border-box;
  background-color: rgba(255, 255, 255, 0.95) !important; 
  backdrop-filter: blur(10px);
  box-shadow: 0 4px 20px rgba(0,0,0,0.03); border-bottom: 1px solid #f1f5f9;
}

.logo { font-size: 1.8rem; font-weight: 800; color: #0f172a !important; margin-right: auto; }
.nav-links { display: flex; align-items: center; gap: 25px; }
.nav-links a { text-decoration: none; color: #334155 !important; font-weight: 600; font-size: 0.95rem; transition: color 0.3s; }
.nav-links a:hover { color: #4f46e5 !important; }
.nav-video { color: #ef4444 !important; font-weight: 700 !important; display: flex; align-items: center; gap: 5px; }

/* BOUTON CONTACT */
.btn-contact {
  background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%);
  color: #ffffff !important; padding: 10px 25px; border-radius: 50px; font-weight: bold; 
  box-shadow: 0 4px 15px rgba(79, 70, 229, 0.3); transition: transform 0.2s;
}
.btn-contact:hover { transform: translateY(-2px); }

/* --- STYLE PHOTO DE PROFIL --- */
.profile-wrapper {
  position: relative; width: 50px; height: 50px; display: flex; align-items: center; justify-content: center; margin-left: 15px; cursor: default;
}
.gradient-ring {
  position: absolute; top: 0; left: 0; right: 0; bottom: 0; border-radius: 50%;
  background: linear-gradient(45deg, #f09433, #e6683c, #dc2743, #cc2366, #bc1888, #4f46e5); 
  z-index: 1; background-size: 200% 200%; animation: rainbow 5s ease infinite;
}
.nav-profile-pic {
  width: 44px; height: 44px; border-radius: 50%; object-fit: cover; z-index: 2; border: 3px solid white; background: white;
}
.profile-wrapper.editable { cursor: pointer; }
.profile-wrapper.editable:hover .nav-profile-pic { filter: brightness(0.8); }
.edit-icon { position: absolute; z-index: 3; font-size: 1.2rem; opacity: 0; transition: opacity 0.2s; pointer-events: none; }
.profile-wrapper.editable:hover .edit-icon { opacity: 1; }
@keyframes rainbow { 0%{background-position:0% 50%} 50%{background-position:100% 50%} 100%{background-position:0% 50%} }
.hidden-input { display: none; }

/* --- FOOTER STYLE (REMIS À L'ORIGINE) --- */
.app-footer {
  background-color: #111827; /* Noir/Bleu Nuit très foncé */
  color: #d1d5db; /* Gris clair */
  padding: 60px 50px 20px 50px;
  margin-top: auto;
}

.footer-container {
  max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; flex-wrap: wrap; gap: 40px; padding-bottom: 40px;
  border-bottom: 1px solid #374151;
}

.footer-col { flex: 1; min-width: 250px; }

.footer-logo { font-size: 1.8rem; font-weight: 800; color: #ffffff; margin-bottom: 20px; }
.footer-col p { line-height: 1.6; opacity: 0.8; }
.footer-col h3 { font-size: 1.2rem; color: #ffffff; margin-bottom: 20px; }

.footer-links { list-style: none; padding: 0; margin: 0; }
.footer-links li { margin-bottom: 12px; }
.footer-links a { text-decoration: none; color: #d1d5db; transition: color 0.3s; }
.footer-links a:hover { color: #a78bfa; padding-left: 5px; }

.social-icons { display: flex; gap: 15px; }
.social-btn {
  width: 40px; height: 40px; background-color: #1f2937; border-radius: 50%; display: flex; align-items: center; justify-content: center; color: white; transition: all 0.3s;
}
.social-btn:hover { background-color: #6366f1; transform: translateY(-3px); }

.footer-bottom { text-align: center; padding-top: 20px; font-size: 0.9rem; opacity: 0.6; }

/* Responsive Footer */
@media (max-width: 768px) {
  .app-footer { padding: 40px 20px 20px 20px; }
  .footer-container { flex-direction: column; gap: 40px; }
}
</style>