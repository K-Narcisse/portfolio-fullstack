<template>
  <div class="library-page">
    
    <!-- EN-TÊTE HERO -->
    <header class="hero-section">
      <div class="hero-content">
        <span class="pill-badge">Portfolio & E-Learning</span>
        <h1>Mes Réalisations & <span class="text-gradient">Ressources</span></h1>
        <p>Explorez mes projets techniques et accédez à ma bibliothèque de formation.</p>
      </div>
    </header>

    <div class="main-container">
      
      <!-- 1. NAVIGATION (ONGLETS) -->
      <div class="tabs-wrapper">
        <div class="tabs-pill">
          <button 
            class="tab-btn" 
            :class="{ active: activeTab === 'projects' }" 
            @click="activeTab = 'projects'">
            💻 Mes Projets ({{ projects.length }})
          </button>
          <button 
            class="tab-btn" 
            :class="{ active: activeTab === 'documents' }" 
            @click="activeTab = 'documents'">
            📚 Ressources ({{ documents.length }})
          </button>
        </div>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="loading-state">
        <div class="spinner"></div>
        <p>Chargement des données...</p>
      </div>

      <div v-else>

        <!-- ======================================================= -->
        <!-- VUE 1 : PROJETS (DESIGN EXACT DE TA CAPTURE)            -->
        <!-- ======================================================= -->
        <div v-if="activeTab === 'projects'" class="projects-grid fade-in">
          
          <div v-if="projects.length === 0" class="empty-state">
            <p>Aucun projet n'a encore été ajouté depuis l'admin.</p>
          </div>

          <!-- BOUCLE SUR LES PROJETS -->
          <div v-for="proj in projects" :key="proj.id" class="clean-card">
            
            <!-- HAUT : FOND VIOLET + ICONE CENTRÉE -->
            <div class="clean-card-header">
              <div class="icon-3d">{{ proj.icon || '🚀' }}</div>
            </div>

            <!-- CORPS : FOND BLANC -->
            <div class="clean-card-body">
              <h3 class="clean-title">{{ proj.title }}</h3>
              
              <p class="clean-desc">
                {{ proj.description || "Description du projet indisponible." }}
              </p>
              
              <!-- TAGS (PILULES) -->
              <div class="clean-tags" v-if="proj.tags">
                <span v-for="tag in proj.tags.split(',')" :key="tag" class="clean-tag">
                  {{ tag.trim() }}
                </span>
              </div>

              <!-- LIEN "VOIR LE PROJET" -->
              <div class="clean-footer">
                <a href="#" class="clean-link">
                  Voir le projet <span class="arrow">→</span>
                </a>
              </div>
            </div>

          </div>
        </div>

        <!-- ======================================================= -->
        <!-- VUE 2 : RESSOURCES (DESIGN NEON DARK - INCHANGÉ)        -->
        <!-- ======================================================= -->
        <div v-if="activeTab === 'documents'" class="grid-container fade-in">
          <div v-for="doc in documents" :key="doc.id" class="neon-card">
            <div class="card-upper">
              <div class="card-badge">
                <span class="file-icon">📄</span> {{ doc.type === 'VIDEO' ? 'Vidéo' : 'Document' }}
              </div>
              <div class="glow-center"></div>
              <div class="icon-3d-neon">
                <span v-if="doc.type === 'VIDEO'" class="emoji-xl">🎬</span>
                <img v-else src="https://cdn-icons-png.flaticon.com/512/2991/2991106.png" alt="Doc" width="85">
              </div>
            </div>

            <div class="card-lower">
              <h3 class="card-title">{{ doc.title }}</h3>
              <p class="card-desc">{{ doc.description }}</p>
              <div class="card-divider"></div>
              <div class="card-actions">
                <div class="price-block">
                  <span class="currency">FCFA</span>
                  <span class="price">{{ formatPrice(doc.price) }}</span>
                </div>
                <button class="btn-neon-purple" @click="openPaymentModal(doc)">Obtenir</button>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>

    <!-- MODALE DE PAIEMENT (Pour les ressources) -->
    <transition name="fade">
      <div v-if="showModal" class="modal-backdrop" @click.self="closeModal">
        <div class="modal-panel">
          <div class="modal-header">
            <div class="modal-title"><h2>Checkout</h2></div>
            <button class="close-icon" @click="closeModal">✕</button>
          </div>
          <div class="modal-body">
            <div class="product-summary">
              <h4>{{ selectedDoc.title }}</h4>
              <div class="summary-price">{{ formatPrice(selectedDoc.price) }} FCFA</div>
            </div>
            <label class="section-label">Payer avec :</label>
            <div class="payment-grid">
              <div class="pay-option" :class="{ active: paymentMethod === 'OM' }" @click="paymentMethod = 'OM'">Orange</div>
              <div class="pay-option" :class="{ active: paymentMethod === 'MOOV' }" @click="paymentMethod = 'MOOV'">Moov</div>
              <div class="pay-option" :class="{ active: paymentMethod === 'CARD' }" @click="paymentMethod = 'CARD'">Carte</div>
              <div class="pay-option" :class="{ active: paymentMethod === 'CRYPTO' }" @click="paymentMethod = 'CRYPTO'">Crypto</div>
            </div>
            
            <div class="dynamic-form">
               <div v-if="paymentMethod === 'OM' || paymentMethod === 'MOOV'">
                  <label>Numéro de téléphone</label>
                  <input type="text" placeholder="77 00 00 00" class="input-field">
               </div>
               <div v-else-if="paymentMethod === 'CARD'">
                  <label>Numéro de carte</label>
                  <input type="text" placeholder="0000 0000 0000 0000" class="input-field">
               </div>
               <div v-else class="wallet-address">TVJ5...8zX2</div>
            </div>

            <button class="btn-pay" @click="processPayment" :disabled="processing">
              {{ processing ? '...' : 'Confirmer' }}
            </button>
          </div>
        </div>
      </div>
    </transition>

  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();

const documents = ref([]);
const projects = ref([]);
const loading = ref(true);
const activeTab = ref('projects');
const showModal = ref(false);
const selectedDoc = ref(null);
const processing = ref(false);
const paymentMethod = ref('OM');

onMounted(async () => {
  if (route.query.tab === 'documents') activeTab.value = 'documents';

  try {
    const projRes = await axios.get('http://localhost:8080/api/projects');
    projects.value = projRes.data;
    const docsRes = await axios.get('http://localhost:8080/api/documents');
    documents.value = docsRes.data;
  } catch (error) {
    console.error("Erreur API", error);
    if(projects.value.length === 0) {
        projects.value = [
            { id: 1, title: 'E-commerce Platform', description: 'Plateforme complète avec gestion panier.', tags: 'Vue.js, Spring Boot, PostgreSQL', icon: '🛍️' },
            { id: 2, title: 'Gestion de Tâches', description: 'App collaborative temps réel.', tags: 'React, Node.js, MongoDB', icon: '📱' },
            { id: 3, title: 'Portfolio Photo', description: 'Site vitrine élégant pour photographe.', tags: 'Vue.js, TypeScript', icon: '📷' }
        ]
    }
  } finally {
    loading.value = false;
  }
});

const formatPrice = (val) => val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
const openPaymentModal = (doc) => { selectedDoc.value = doc; showModal.value = true; };
const closeModal = () => { showModal.value = false; processing.value = false; };
const processPayment = () => {
  processing.value = true;
  setTimeout(() => {
    processing.value = false;
    showModal.value = false;
    alert("Paiement réussi !");
  }, 1500);
};
</script>

<style scoped>
/* GLOBAL */
@import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap');
.library-page { font-family: 'Plus Jakarta Sans', sans-serif; background-color: #f8fafc; min-height: 100vh; color: #0f172a; padding-bottom: 80px; }
.main-container { max-width: 1200px; margin: 0 auto; padding: 0 20px; }
.hero-section { background: white; padding: 80px 20px 40px; text-align: center; }
.pill-badge { background: #eef2ff; color: #312e81; padding: 6px 16px; border-radius: 50px; font-weight: 700; font-size: 0.8rem; border: 1px solid #e0e7ff; display: inline-block; margin-bottom: 15px; }
h1 { font-size: 3rem; font-weight: 800; margin-bottom: 10px; color: #0f172a; }
.text-gradient { background: linear-gradient(to right, #4338ca, #7e22ce); -webkit-background-clip: text; background-clip: text; color: transparent; }
p { color: #334155; font-size: 1.1rem; }

/* TABS */
.tabs-wrapper { display: flex; justify-content: center; margin-bottom: 50px; margin-top: 20px; }
.tabs-pill { background: #f1f5f9; border-radius: 50px; padding: 5px; display: flex; gap: 5px; border: 1px solid #e2e8f0; }
.tab-btn { border: none; background: transparent; padding: 12px 30px; border-radius: 40px; font-weight: 700; color: #475569; cursor: pointer; transition: all 0.3s ease; font-size: 0.95rem; }
.tab-btn.active { background: #4338ca; color: white; box-shadow: 0 4px 15px rgba(67, 56, 202, 0.3); }
.fade-in { animation: fadeIn 0.5s ease; }
@keyframes fadeIn { from{opacity:0; transform:translateY(10px);} to{opacity:1; transform:translateY(0);} }

/* ================================================================= */
/* 1. NOUVEAU STYLE PROJETS (IDENTIQUE À L'IMAGE)                    */
/* ================================================================= */
.projects-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(340px, 1fr)); /* Largeur confortable */
  gap: 30px;
}

.clean-card {
  background: white;
  border-radius: 16px; /* Coins arrondis propres */
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0,0,0,0.03); /* Ombre très légère */
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex;
  flex-direction: column;
  border: 1px solid #f1f5f9;
}

.clean-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 20px 40px rgba(0,0,0,0.08); /* Ombre plus forte au survol */
}

/* HEADER VIOLET */
.clean-card-header {
  height: 180px;
  background-color: #8b5cf6; /* Violet vif comme sur l'image */
  display: flex;
  align-items: center;
  justify-content: center;
}

.icon-3d {
  font-size: 4rem; /* Emoji large */
  filter: drop-shadow(0 4px 8px rgba(0,0,0,0.15)); /* Légère ombre 3D */
}

/* CORPS BLANC */
.clean-card-body {
  padding: 24px;
  flex: 1;
  display: flex;
  flex-direction: column;
  text-align: left;
}

.clean-title {
  margin: 0 0 12px 0;
  font-size: 1.35rem;
  font-weight: 700;
  color: #1e293b; /* Noir soft */
}

.clean-desc {
  font-size: 1rem;
  color: #475569; /* Gris lisible */
  line-height: 1.6;
  margin-bottom: 24px;
  flex: 1;
}

/* TAGS (PILULES) */
.clean-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-bottom: 24px;
}

.clean-tag {
  background-color: #f1f5f9; /* Fond gris très clair / bleu pâle */
  color: #4f46e5; /* Texte Indigo */
  padding: 6px 14px;
  border-radius: 20px; /* Forme de pilule */
  font-size: 0.8rem;
  font-weight: 700;
}

/* PIED DE CARTE (LIEN) */
.clean-footer {
  margin-top: auto;
}

.clean-link {
  text-decoration: none;
  color: #4f46e5;
  font-weight: 700;
  font-size: 0.95rem;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: gap 0.2s;
}

.clean-link:hover {
  gap: 10px;
  color: #4338ca;
}
.arrow { font-size: 1.2rem; }

/* ================================================================= */
/* 2. STYLE RESSOURCES (NEON DARK - INCHANGÉ)                        */
/* ================================================================= */
.grid-container { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 30px; }
.neon-card { background: linear-gradient(180deg, #1e1b4b 0%, #020617 100%); border-radius: 30px; overflow: hidden; border: 1px solid rgba(255,255,255,0.15); position: relative; }
.card-upper { height: 200px; position: relative; display: flex; align-items: center; justify-content: center; background: radial-gradient(circle at top, rgba(99, 102, 241, 0.15), transparent 70%); }
.card-badge { position: absolute; top: 15px; left: 15px; border: 1px solid rgba(255,255,255,0.5); padding: 5px 12px; border-radius: 10px; color: white; font-size: 0.8rem; background: rgba(0,0,0,0.5); backdrop-filter: blur(4px); }
.glow-center { position: absolute; width: 80px; height: 80px; background: #7c3aed; filter: blur(50px); opacity: 0.5; border-radius: 50%; }
.icon-3d-neon { position: relative; z-index: 2; font-size: 4rem; }
.card-lower { padding: 25px; color: white; }
.card-title { font-size: 1.25rem; font-weight: 700; color:white; margin-bottom: 5px;}
.card-desc { color: #e2e8f0; font-size: 0.9rem; margin-bottom: 15px; }
.card-actions { display: flex; justify-content: space-between; align-items: center; margin-top: 15px; border-top: 1px solid rgba(255,255,255,0.2); padding-top: 15px; }
.currency { font-size: 0.7rem; color: #e2e8f0; }
.price { font-size: 1.4rem; font-weight: 800; }
.btn-neon-purple { background: linear-gradient(135deg, #6366f1 0%, #a855f7 100%); color: white; border: none; padding: 10px 24px; border-radius: 10px; font-weight: 700; cursor: pointer; }

/* MODALE (Minimaliste) */
.modal-backdrop { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.7); backdrop-filter: blur(5px); z-index: 5000; display: flex; align-items: center; justify-content: center; }
.modal-panel { background: white; width: 90%; max-width: 400px; border-radius: 20px; padding: 25px; box-shadow: 0 25px 50px rgba(0,0,0,0.2); }
.modal-header { display: flex; justify-content: space-between; border-bottom: 1px solid #e2e8f0; padding-bottom: 10px; margin-bottom: 20px; }
.modal-title { color: #0f172a; font-weight: 700; }
.section-label { display: block; color: #1e293b; font-weight: 700; margin-bottom: 12px; }
.payment-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; margin-bottom: 20px; }
.pay-option { border: 1px solid #cbd5e1; padding: 10px; border-radius: 8px; cursor: pointer; text-align: center; font-weight: 600; font-size: 0.9rem; color: #334155; }
.pay-option.active { border-color: #4338ca; background: #eef2ff; color: #4338ca; }
.btn-pay { width: 100%; background: #0f172a; color: white; padding: 15px; border-radius: 12px; border: none; font-weight: 700; cursor: pointer; margin-top: 10px;}
.close-icon { background: none; border: none; font-size: 1.5rem; cursor: pointer; color: #475569; }
.input-field { width: 100%; padding: 12px; border: 1px solid #94a3b8; border-radius: 8px; margin-bottom: 15px; color: #0f172a; }
.wallet-address { background: #020617; color: #22c55e; padding: 10px; text-align: center; border-radius: 5px; font-family: monospace; border: 1px solid #22c55e; }
</style>