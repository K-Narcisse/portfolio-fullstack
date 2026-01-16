<template>
  <div class="library-page">
    <!-- HERO -->
    <header class="hero-section">
      <div class="hero-content">
        <span class="pill-badge">E-Learning & Ressources Premium</span>
        <h1>Boostez Votre <span class="text-gradient">Carrière</span></h1>
        <p>Accédez à des ressources exclusives pour progresser plus vite que jamais.</p>
      </div>
    </header>

    <!-- CONTENU -->
    <div class="main-container">
      <!-- Stats -->
      <div class="stats-bar">
        <div class="stat-item">
          <span class="stat-number">{{ documents.length }}</span>
          <span class="stat-label">Ressources</span>
        </div>
        <div class="stat-item">
          <span class="stat-icon">⚡</span>
          <span class="stat-label">Instantané</span>
        </div>
        <div class="stat-item">
          <span class="stat-icon">🔒</span>
          <span class="stat-label">Sécurisé</span>
        </div>
      </div>

      <!-- Loader -->
      <div v-if="loading" class="loading-state">
        <div class="spinner"></div>
        <p>Chargement...</p>
      </div>

      <!-- GRID -->
      <div v-else class="grid-container">
        <div v-for="doc in documents" :key="doc.id" class="premium-card">

          <div class="card-visual" :class="doc.type === 'VIDEO' ? 'grad-video' : 'grad-file'">
            <div class="card-tag">
              <span>{{ doc.type === 'VIDEO' ? '🎬 Vidéo' : '📄 Document' }}</span>
            </div>
            <div class="icon-3d">
              <span>{{ doc.type === 'VIDEO' ? '📹' : '📝' }}</span>
            </div>
          </div>

          <div class="card-content">
            <h3 class="card-title" :title="doc.title">{{ doc.title }}</h3>
            <p class="card-desc">{{ doc.description || "Description indisponible" }}</p>

            <div class="card-separator"></div>

            <div class="card-bottom">
              <div class="price-group">
                <span class="currency-label">FCFA</span>
                <span class="price-value">{{ formatPrice(doc.price) }}</span>
              </div>
              <button class="btn-primary" @click="openPaymentModal(doc)">Obtenir</button>
            </div>
          </div>

        </div>
      </div>
    </div>

    <!-- MODAL -->
    <transition name="fade">
      <div v-if="showModal" class="modal-backdrop" @click.self="closeModal">
        <div class="modal-panel">

          <div class="modal-header">
            <div>
              <h2>Achat</h2>
              <p>Paiement sécurisé</p>
            </div>
            <button class="close-icon" @click="closeModal">✕</button>
          </div>

          <div class="modal-body">
            <div class="product-summary">
              <div class="summary-icon">💼</div>
              <div class="summary-info">
                <h4>{{ selectedDoc.title }}</h4>
                <p>{{ selectedDoc.type }}</p>
              </div>
              <span class="summary-price">{{ formatPrice(selectedDoc.price) }} FCFA</span>
            </div>

            <!-- Moyens -->
            <label class="section-label" for="payment-method">Moyen de paiement</label>
            <div class="payment-grid" id="payment-method">
              <div class="pay-option" :class="{ active: paymentMethod === 'OM' }" @click="paymentMethod = 'OM'">
                <div class="radio-circle"></div> Orange
              </div>
              <div class="pay-option" :class="{ active: paymentMethod === 'MOOV' }" @click="paymentMethod = 'MOOV'">
                <div class="radio-circle"></div> Moov
              </div>
              <div class="pay-option" :class="{ active: paymentMethod === 'CARD' }" @click="paymentMethod = 'CARD'">
                <div class="radio-circle"></div> Carte
              </div>
              <div class="pay-option" :class="{ active: paymentMethod === 'CRYPTO' }" @click="paymentMethod = 'CRYPTO'">
                <div class="radio-circle"></div> Crypto
              </div>
            </div>

            <!-- Champs dynamiques -->
            <div class="dynamic-form">
              <transition name="slide-fade" mode="out-in">
                <div v-if="paymentMethod === 'OM' || paymentMethod === 'MOOV'" key="mobile">
                  <label for="phone-input">Numéro</label>
                  <input id="phone-input" v-model="inputValue" placeholder="77 00 00 00" />
                </div>

                <div v-else-if="paymentMethod === 'CARD'" key="card">
                  <label for="card-input">Carte Bancaire</label>
                  <input id="card-input" placeholder="XXXX XXXX XXXX XXXX"/>
                  <div class="row">
                    <input placeholder="MM/AA"/>
                    <input placeholder="CVC"/>
                  </div>
                </div>

                <div v-else class="crypto-info" key="crypto">
                  <p>USDT (TRC20):</p>
                  <code class="wallet">TVJ5...8zX2</code>
                </div>
              </transition>
            </div>

            <button class="btn-pay" @click="processPayment">
              <span v-if="!processing">Payer</span>
              <span v-else>Traitement...</span>
            </button>
          </div>

        </div>
      </div>
    </transition>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const documents = ref([]);
const loading = ref(true);
const showModal = ref(false);
const selectedDoc = ref({});
const processing = ref(false);
const paymentMethod = ref('OM');
const inputValue = ref('');

onMounted(async () => {
  try {
    const { data } = await axios.get('http://localhost:8080/api/documents');
    documents.value = data;
  } finally {
    loading.value = false;
  }
});

const formatPrice = v => v.toString().replaceAll(/\B(?=(\d{3})+(?!\d))/g, ' ');

const openPaymentModal = doc => {
  selectedDoc.value = doc;
  showModal.value = true;
};

const closeModal = () => showModal.value = false;

const processPayment = () => {
  processing.value = true;
  setTimeout(() => {
    processing.value = false;
    showModal.value = false;
    downloadFile(selectedDoc.value);
  }, 1500);
};

const downloadFile = doc => {
  const link = document.createElement('a');
  link.href = doc.fileUrl;
  link.setAttribute('download', doc.title);
  link.click();
};
</script>

<style scoped>
/* ===== COLOR PALETTE =====
   Navy   #0A0F24
   Indigo #4338CA
   Violet #7C3AED
   Neon   #06E3FF
========================== */

.library-page { background:#0A0F24; min-height:100vh; color:white; font-family:"Plus Jakarta Sans",sans-serif; }

.hero-section {
  background:linear-gradient(135deg,#4338CA,#7C3AED);
  padding:120px 20px 90px;
  text-align:center;
}
.pill-badge{background:#ffffff25; padding:6px 14px; border-radius:999px; font-weight:600;}
.text-gradient{background:linear-gradient(to right,#06E3FF,#7C3AED);-webkit-background-clip:text;background-clip:text;-webkit-text-fill-color:transparent;}

.main-container{max-width:1200px;margin:-40px auto 40px;padding:0 20px;}
.stats-bar{background:#11172F;border-radius:20px;padding:25px;display:flex;justify-content:space-around;box-shadow:0 12px 30px #0008;}
.stat-number{font-size:2rem;font-weight:800;color:#06E3FF;}

.grid-container{display:grid;grid-template-columns:repeat(auto-fill,minmax(260px,1fr));gap:25px;}

.premium-card{
  background:#11172F;border-radius:22px;overflow:hidden;transition:.3s;display:flex;flex-direction:column;border:1px solid #ffffff15;
}
.premium-card:hover{transform:translateY(-6px);box-shadow:0 20px 40px #000a;}

.card-visual{height:170px;display:flex;align-items:center;justify-content:center;position:relative;}
.grad-file{background:linear-gradient(135deg,#1F2937,#4338CA);}
.grad-video{background:linear-gradient(135deg,#312E81,#7C3AED);}
.card-tag{position:absolute;top:10px;left:10px;background:#ffffff25;padding:6px 12px;border-radius:10px;font-size:.75rem;}
.icon-3d{font-size:4rem;filter:drop-shadow(0 12px 12px #0008);}

.card-content{padding:22px;display:flex;flex-direction:column;flex:1;}
.card-title{font-size:1.2rem;font-weight:700;margin-bottom:8px;color:white;}
.card-desc{color:#94A3B8;font-size:.9rem;flex:1;}
.card-separator{height:1px;background:#1e293b;margin:15px 0;}

.price-group{text-align:left;}
.price-value{font-size:1.3rem;font-weight:800;color:#06E3FF;}

.btn-primary{
  background:#06E3FF;color:#0A0F24;padding:10px 18px;border-radius:12px;font-weight:700;border:none;cursor:pointer;
}
.btn-primary:hover{background:#39edff;}

.modal-backdrop{position:fixed; inset:0; display:flex; align-items:right; justify-content:center; background:#0009; backdrop-filter:blur(6px);}
.modal-panel{background:#11172F;border-radius:22px;width:90%;max-width:450px;}

.modal-header{padding:22px;border-bottom:1px solid #ffffff18;display:flex;justify-content:space-between;}
.modal-body{padding:22px;}

.payment-grid{display:grid;grid-template-columns:1fr 1fr;gap:10px;}
.pay-option{padding:10px;border-radius:10px;background:#1A213B;border:2px solid transparent;cursor:pointer;}
.pay-option.active{border-color:#06E3FF;}

.row{display:flex;gap:10px;margin-top:10px;}

.btn-pay{width:100%;background:#06E3FF;color:#0A0F24;padding:16px;border-radius:14px;margin-top:20px;font-weight:700;cursor:pointer;}

.crypto-info{color:#06E3FF;text-align:center;}
.wallet{background:#0A0F24;padding:10px;border-radius:8px;display:block;}

input{width:100%;padding:12px;border-radius:10px;border:none;background:#1A213B;color:white;margin-top:6px;}
input:focus{outline:2px solid #06E3FF;}
</style>
