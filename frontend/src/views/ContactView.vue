<template>
  <div class="contact-page">
    
    <!-- 1. HEADER (Avec effet de fond moderne) -->
    <div class="contact-header">
      <!-- Cercles décoratifs en fond -->
      <div class="decoration-circle circle-1"></div>
      <div class="decoration-circle circle-2"></div>
      
      <div class="header-content">
        <h1>Contactez-moi</h1>
        <p>Une idée ? Un projet ? Construisons quelque chose de grand ensemble.</p>
      </div>
    </div>

    <!-- 2. CONTENEUR PRINCIPAL -->
    <div class="main-container">
      <div class="content-wrapper">
        
        <!-- COLONNE GAUCHE : Infos & Réseaux -->
        <div class="info-column">
          <div class="info-header">
            <h2>Parlons de votre projet</h2>
            <p class="intro-text">
              Je suis toujours ouvert aux nouvelles opportunités et collaborations.
              N'hésitez pas à me contacter via le formulaire ou directement par email.
            </p>
          </div>

          <div class="contact-details">
            <!-- Item Email -->
            <div class="detail-item">
              <div class="icon-box">
                <!-- SVG Icon Email -->
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75" /></svg>
              </div>
              <div class="detail-text">
                <h3>Email</h3>
                <a href="mailto:contact@monportfolio.com">narcissekouraogo73@gmail.com</a>
              </div>
            </div>

            <!-- Item Téléphone -->
            <div class="detail-item">
              <div class="icon-box">
                <!-- SVG Icon Phone -->
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" d="M10.5 1.5H8.25A2.25 2.25 0 006 3.75v16.5a2.25 2.25 0 002.25 2.25h7.5A2.25 2.25 0 0018 20.25V3.75a2.25 2.25 0 00-2.25-2.25H13.5m-3 0V3h3V1.5m-3 0h3m-3 18.75h3" /></svg>
              </div>
              <div class="detail-text">
                <h3>Téléphone</h3>
                <p>+226 77 48 71 20</p>
              </div>
            </div>

            <!-- Item Localisation -->
            <div class="detail-item">
              <div class="icon-box">
                <!-- SVG Icon Map -->
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" d="M15 10.5a3 3 0 11-6 0 3 3 0 016 0z" /><path stroke-linecap="round" stroke-linejoin="round" d="M19.5 10.5c0 7.142-7.5 11.25-7.5 11.25S4.5 17.642 4.5 10.5a7.5 7.5 0 1115 0z" /></svg>
              </div>
              <div class="detail-text">
                <h3>Localisation</h3>
                <p>Paris, France & Remote</p>
              </div>
            </div>
          </div>

          <!-- Réseaux Sociaux -->
          <div class="social-links">
            <p>Retrouvez-moi sur :</p>
            <div class="social-icons">
              <a href="#" class="social-btn github">GitHub</a>
              <a href="#" class="social-btn linkedin">LinkedIn</a>
              <a href="#" class="social-btn facebook">Facebook</a>
              <a href="#" class="social-btn whatsapp">WhatsApp</a>
            </div>
          </div>
        </div>

        <!-- COLONNE DROITE : Formulaire Pro -->
        <div class="form-column">
          <div class="form-card">
            <form @submit.prevent="envoyerMessage">
              <div class="grid-row">
                <div class="form-group">
                  <label>Nom complet</label>
                  <input v-model="form.nom" type="text" placeholder="KOURAOGO Narcisse" required>
                </div>
                <div class="form-group">
                  <label>Email</label>
                  <input v-model="form.email" type="email" placeholder="narcissekouraogo73@gmail.com" required>
                </div>
              </div>

              <div class="form-group">
                <label>Sujet</label>
                <input v-model="form.sujet" type="text" placeholder="Demande de devis, Collaboration..." required>
              </div>

              <div class="form-group">
                <label>Message</label>
                <textarea v-model="form.message" rows="5" placeholder="Décrivez votre besoin..." required></textarea>
              </div>

              <button type="submit" class="btn-submit">
                <span>Envoyer le message</span>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="send-icon"><path stroke-linecap="round" stroke-linejoin="round" d="M6 12L3.269 3.126A59.768 59.768 0 0121.485 12 59.77 59.77 0 013.27 20.876L5.999 12zm0 0h7.5" /></svg>
              </button>
            </form>
          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue';

const form = ref({ nom: '', email: '', sujet: '', message: '' });

const envoyerMessage = () => {
  // Simulation d'envoi
  alert(`Merci ${form.value.nom} ! Je vous répondrai sous 24h.`);
  form.value = { nom: '', email: '', sujet: '', message: '' };
};
</script>

<style scoped>
/* FOND GLOBAL */
.contact-page {
  background-color: #f8fafc; /* Gris très doux */
  min-height: 100vh;
}

/* --- HEADER VIOLET AVEC EFFETS --- */
.contact-header {
  background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%);
  color: white;
  text-align: center;
  padding: 140px 20px 100px 20px;
  position: relative;
  overflow: hidden; /* Pour couper les cercles qui dépassent */
}

/* Cercles décoratifs (effet visuel subtil) */
.decoration-circle {
  position: absolute;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.1);
}
.circle-1 { width: 300px; height: 300px; top: -100px; left: -50px; }
.circle-2 { width: 200px; height: 200px; bottom: 20px; right: 10%; }

.header-content { position: relative; z-index: 10; }
.contact-header h1 { font-size: 3.5rem; font-weight: 800; margin: 0 0 15px 0; letter-spacing: -1px; }
.contact-header p { font-size: 1.25rem; opacity: 0.9; max-width: 600px; margin: 0 auto; font-weight: 300; }

/* --- CONTENEUR PRINCIPAL --- */
.main-container {
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 20px;
  margin-top: -80px; /* Remonte sur le header */
  padding-bottom: 80px;
  position: relative;
  z-index: 20;
}

.content-wrapper {
  background: white;
  border-radius: 24px;
  box-shadow: 0 20px 50px rgba(0,0,0,0.08); /* Ombre premium */
  display: flex;
  overflow: hidden; /* Pour que le border-radius s'applique partout */
}

/* --- COLONNE GAUCHE (INFOS) --- */
.info-column {
  flex: 2; /* Prend 40% de la largeur */
  background: #f1f5f9; /* Gris bleuté très clair */
  padding: 50px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.info-header h2 { font-size: 1.8rem; color: #1e1b4b; margin: 0 0 15px 0; }
.intro-text { color: #64748b; line-height: 1.6; margin-bottom: 40px; }

/* ITEMS CONTACT */
.detail-item {
  display: flex;
  align-items: flex-start;
  gap: 20px;
  margin-bottom: 30px;
}

.icon-box {
  width: 45px; height: 45px;
  background: white;
  border-radius: 12px;
  display: flex; align-items: center; justify-content: center;
  color: #4f46e5;
  box-shadow: 0 4px 12px rgba(0,0,0,0.05);
  flex-shrink: 0;
}
.icon-box svg { width: 24px; height: 24px; }

.detail-text h3 { margin: 0 0 5px 0; font-size: 0.9rem; text-transform: uppercase; letter-spacing: 1px; color: #94a3b8; }
.detail-text p, .detail-text a { font-size: 1.1rem; color: #1e293b; font-weight: 600; text-decoration: none; margin: 0; }
.detail-text a:hover { color: #4f46e5; text-decoration: underline; }

/* RESEAUX SOCIAUX */
.social-links p { font-size: 0.9rem; color: #64748b; margin-bottom: 10px; }
.social-icons { display: flex; gap: 10px; }
.social-btn {
  padding: 8px 16px; border-radius: 8px; font-weight: 600; text-decoration: none; font-size: 0.9rem; transition: transform 0.2s;
  background: white; color: #1e293b; border: 1px solid #e2e8f0;
}
.social-btn:hover { transform: translateY(-2px); border-color: #4f46e5; color: #4f46e5; }


/* --- COLONNE DROITE (FORMULAIRE) --- */
.form-column {
  flex: 3; /* Prend 60% de la largeur */
  padding: 50px;
  background: white;
}

.grid-row { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }

.form-group { margin-bottom: 25px; }

label {
  display: block; margin-bottom: 8px; font-weight: 600; color: #334155; font-size: 0.9rem;
}

input, textarea {
  width: 100%;
  padding: 14px;
  border: 1px solid #e2e8f0;
  border-radius: 10px;
  font-size: 1rem;
  background-color: #f8fafc;
  transition: all 0.3s;
  font-family: inherit;
  box-sizing: border-box;
}

input:focus, textarea:focus {
  outline: none;
  border-color: #4f46e5;
  background-color: white;
  box-shadow: 0 0 0 4px rgba(79, 70, 229, 0.1);
}

.btn-submit {
  width: 100%;
  background: linear-gradient(135deg, #4f46e5 0%, #4338ca 100%);
  color: white;
  padding: 16px;
  border: none;
  border-radius: 12px;
  font-size: 1.1rem;
  font-weight: 700;
  cursor: pointer;
  display: flex; align-items: center; justify-content: center; gap: 10px;
  transition: all 0.3s;
  box-shadow: 0 10px 20px rgba(79, 70, 229, 0.25);
}

.btn-submit:hover {
  transform: translateY(-2px);
  box-shadow: 0 15px 25px rgba(79, 70, 229, 0.35);
}

.send-icon { width: 20px; height: 20px; }

/* --- RESPONSIVE --- */
@media (max-width: 900px) {
  .content-wrapper { flex-direction: column; }
  .grid-row { grid-template-columns: 1fr; }
  .main-container { margin-top: -40px; padding-bottom: 40px; }
  .contact-header { padding-bottom: 80px; }
  .info-column, .form-column { padding: 30px; }
}
</style>