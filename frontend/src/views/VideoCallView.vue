<template>
  <div class="video-page">
    
    <!-- HEADER -->
    <div class="video-header">
      <div class="header-content">
        <h1>Espace de Conférence</h1>
        <p>Créez une salle sécurisée ou rejoignez une réunion existante.</p>
      </div>
    </div>

    <!-- CONTENU -->
    <div class="video-container">
      
      <!-- ÉTAT 1 : CONFIGURATION (AVANT DE REJOINDRE) -->
      <div v-if="!isJoined" class="join-card">
        <div class="icon-wrapper">
          <span class="icon-camera">📹</span>
        </div>
        
        <h2>Démarrer une session</h2>
        
        <form @submit.prevent="startMeeting">
          
          <!-- 1. NOM DE L'UTILISATEUR -->
          <div class="form-group">
            <label>Votre Nom</label>
            <input 
              v-model="userName" 
              type="text" 
              placeholder="Ex: Jean Dupont" 
              required 
              class="dark-input" 
            />
          </div>

          <!-- 2. ID DE LA RÉUNION (CODE) -->
          <div class="form-group">
            <label>ID de la réunion (Code)</label>
            <div class="input-with-action">
              <input 
                v-model="roomId" 
                type="text" 
                placeholder="Ex: Projet-Alpha-2025" 
                required 
                class="dark-input" 
              />
              <!-- Bouton Générer un code aléatoire -->
              <button type="button" class="btn-icon" @click="generateCode" title="Générer un code">🎲</button>
            </div>
            <small class="hint">Partagez ce code avec les autres participants.</small>
          </div>

          <button type="submit" class="btn-join">
            Entrer dans la salle
          </button>

        </form>
      </div>

      <!-- ÉTAT 2 : EN APPEL (JITSI) -->
      <div v-else class="video-wrapper">
        <!-- Barre d'info pendant l'appel -->
        <div class="active-call-bar">
          <span>🔴 En direct : <strong>{{ roomId }}</strong></span>
          <button @click="copyCode" class="btn-copy">📋 Copier le code</button>
          <button @click="leaveMeeting" class="btn-hangup">Quitter</button>
        </div>
        
        <div class="meeting-frame" id="jitsi-container"></div>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const isJoined = ref(false);
const userName = ref('');
const roomId = ref('');
let api = null;

// Générer un ID aléatoire
const generateCode = () => {
  roomId.value = 'Meeting-' + Math.floor(1000 + Math.random() * 9000);
};

// Copier le code
const copyCode = () => {
  navigator.clipboard.writeText(roomId.value);
  alert("Code copié ! Envoyez-le à vos invités.");
};

// Démarrer la réunion
const startMeeting = () => {
  if (!userName.value || !roomId.value) return;
  isJoined.value = true;

  setTimeout(() => {
    const domain = 'meet.jit.si';
    const options = {
      roomName: roomId.value, 
      width: '100%',
      height: '100%',
      parentNode: document.querySelector('#jitsi-container'),
      userInfo: { displayName: userName.value },
      configOverwrite: { 
        startWithAudioMuted: true, 
        startWithVideoMuted: false, 
        prejoinPageEnabled: false,
        // Active l'option pour changer le background
        disableBackgroundBlur: false 
      },
      interfaceConfigOverwrite: {
        // J'ai ajouté 'select-background' dans la liste ci-dessous
        TOOLBAR_BUTTONS: [
          'microphone', 'camera', 'desktop', 'fullscreen',
          'hangup', 'chat', 'videoquality', 'tileview', 'share-video',
          'select-background', 'settings' 
        ],
        SHOW_JITSI_WATERMARK: false
      }
    };

    api = new window.JitsiMeetExternalAPI(domain, options);

    api.addEventListeners({
      videoConferenceLeft: () => leaveMeeting()
    });
  }, 100);
};

const leaveMeeting = () => {
  if (api) api.dispose();
  isJoined.value = false;
};
</script>

<style scoped>
/* --- THÈME DARK NAVY (Strictement identique à ta demande) --- */
.video-page {
  background-color: #10101a;
  min-height: 100vh;
  display: flex; flex-direction: column; color: #e2e8f0;
}

.video-header { padding: 80px 20px 40px; text-align: center; }
.video-header h1 { 
  margin: 0; font-size: 2.5rem; font-weight: 800; 
  background: linear-gradient(to right, #818cf8, #c084fc);
  -webkit-background-clip: text; background-clip: text; color: transparent; 
}
.video-header p { color: #94a3b8; margin-top: 10px; font-size: 1.1rem; }

.video-container { flex: 1; display: flex; justify-content: center; padding: 20px; max-width: 1200px; width: 100%; margin: 0 auto; }

/* CARTE DE CONFIGURATION */
.join-card {
  background: #1e1e2e; padding: 40px; border-radius: 24px;
  box-shadow: 0 20px 60px rgba(0,0,0,0.4); text-align: center;
  max-width: 450px; width: 100%; height: fit-content;
  border: 1px solid rgba(255,255,255,0.05); margin-top: 20px;
}

.icon-wrapper {
  background: #2a2a3c; width: 70px; height: 70px; border-radius: 50%;
  display: flex; align-items: center; justify-content: center; margin: 0 auto 20px;
  border: 1px solid rgba(255,255,255,0.1);
}
.icon-camera { font-size: 2rem; }

h2 { margin-bottom: 20px; font-weight: 700; color: white; }

/* FORMULAIRE */
.form-group { text-align: left; margin-bottom: 20px; }
label { display: block; margin-bottom: 8px; color: #94a3b8; font-size: 0.9rem; font-weight: 600; }

.input-with-action { display: flex; gap: 10px; }

.dark-input {
  width: 100%; padding: 14px; background: #10101a; border: 1px solid #3f3f5f;
  border-radius: 12px; font-size: 1rem; color: white; box-sizing: border-box;
  transition: border 0.3s;
}
.dark-input:focus { outline: none; border-color: #818cf8; }

.btn-icon {
  background: #2a2a3c; border: 1px solid #3f3f5f; border-radius: 12px;
  cursor: pointer; padding: 0 15px; font-size: 1.2rem; transition: 0.2s;
}
.btn-icon:hover { background: #818cf8; border-color: #818cf8; }

.hint { font-size: 0.8rem; color: #64748b; margin-top: 5px; display: block; }

.btn-join {
  width: 100%; background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%);
  color: white; padding: 16px; border: none; border-radius: 12px;
  font-weight: bold; font-size: 1.1rem; cursor: pointer; margin-top: 10px;
  transition: transform 0.2s, box-shadow 0.2s;
  box-shadow: 0 4px 15px rgba(124, 58, 237, 0.3);
}
.btn-join:hover { transform: translateY(-2px); box-shadow: 0 6px 25px rgba(124, 58, 237, 0.5); }

/* ZONE APPEL ACTIF */
.video-wrapper { width: 100%; display: flex; flex-direction: column; gap: 15px; }

.active-call-bar {
  display: flex; justify-content: space-between; align-items: center;
  background: #1e1e2e; padding: 15px 20px; border-radius: 15px;
  border: 1px solid rgba(255,255,255,0.05);
}
.active-call-bar span { color: #e2e8f0; }
.active-call-bar strong { color: #818cf8; font-family: monospace; font-size: 1.1rem; }

.btn-copy {
  background: #2a2a3c; color: white; border: none; padding: 8px 15px;
  border-radius: 8px; cursor: pointer; font-size: 0.9rem; margin-left: auto; margin-right: 10px;
}
.btn-copy:hover { background: #4f46e5; }

.btn-hangup {
  background: #ef4444; color: white; border: none; padding: 8px 15px;
  border-radius: 8px; cursor: pointer; font-size: 0.9rem; font-weight: bold;
}
.btn-hangup:hover { background: #dc2626; }

.meeting-frame {
  width: 100%; height: 75vh; background: #000; border-radius: 20px; overflow: hidden; border: 1px solid #333;
}
</style>