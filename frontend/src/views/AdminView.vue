<template>
  <div class="admin-page">
    
    <div class="admin-container">
      <h1>Tableau de Bord Admin</h1>
      
      <!-- NAVIGATION DES ONGLETS -->
      <div class="tabs">
        <button 
          :class="{ active: activeTab === 'projects' }" 
          @click="activeTab = 'projects'">
          Gérer Projets
        </button>
        <button 
          :class="{ active: activeTab === 'documents' }" 
          @click="activeTab = 'documents'">
          Gérer Bibliothèque
        </button>
      </div>

      <!-- ========================================= -->
      <!-- CONTENU 1 : AJOUTER UN PROJET             -->
      <!-- ========================================= -->
      <div v-if="activeTab === 'projects'" class="tab-content">
        <h2>Ajouter un nouveau projet</h2>
        <form @submit.prevent="addProject">
          <div class="form-group">
            <label>Titre du projet</label>
            <input v-model="projectForm.title" type="text" placeholder="Ex: Site E-commerce" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <textarea v-model="projectForm.description" rows="3" required></textarea>
          </div>
          <div class="form-group">
            <label>Technologies (séparées par des virgules)</label>
            <input v-model="projectForm.tags" type="text" placeholder="Vue.js, Java, MySQL">
          </div>
          <div class="form-group">
            <label>Icône (Emoji)</label>
            <input v-model="projectForm.icon" type="text" placeholder="💻" class="emoji-input">
          </div>
          
          <button type="submit" class="btn-save">Enregistrer le Projet</button>
        </form>
      </div>

      <!-- ========================================= -->
      <!-- CONTENU 2 : AJOUTER UN DOCUMENT (UPLOAD)  -->
      <!-- ========================================= -->
      <div v-if="activeTab === 'documents'" class="tab-content">
        <h2>Ajouter une ressource</h2>
        
        <form @submit.prevent="addDocument">
          <div class="form-group">
            <label>Titre</label>
            <input v-model="docForm.title" type="text" required>
          </div>
          
          <div class="form-group">
            <label>Type</label>
            <select v-model="docForm.type">
              <option value="PDF">Document PDF</option>
              <option value="VIDEO">Vidéo</option>
            </select>
          </div>

          <div class="form-group">
            <label>Prix (FCFA)</label>
            <input v-model="docForm.price" type="number">
          </div>

          <!-- INPUT FILE (IMPORTANT) -->
          <div class="form-group">
            <label>Sélectionner le fichier (PDF ou MP4)</label>
            <input type="file" @change="handleFileUpload" required>
          </div>

          <div class="form-group">
            <label>Description</label>
            <textarea v-model="docForm.description" rows="3"></textarea>
          </div>

          <button type="submit" class="btn-save">Uploader et Enregistrer</button>
        </form>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import axios from 'axios';

const activeTab = ref('projects');

// --- 1. GESTION PROJETS ---
const projectForm = reactive({
  title: '',
  description: '',
  tags: '',
  icon: '📁'
});

const addProject = async () => {
  try {
    await axios.post('http://localhost:8080/api/projects', projectForm);
    alert('Projet ajouté avec succès !');
    // Reset
    projectForm.title = '';
    projectForm.description = '';
    projectForm.tags = '';
  } catch (error) {
    console.error(error);
    alert("Erreur lors de l'ajout (Vérifie le backend).");
  }
};

// --- 2. GESTION DOCUMENTS (UPLOAD) ---
const docForm = reactive({
  title: '',
  type: 'PDF',
  price: 0,
  description: ''
});

const selectedFile = ref(null);

const handleFileUpload = (event) => {
  selectedFile.value = event.target.files[0];
};

const addDocument = async () => {
  if (!selectedFile.value) {
    alert("Veuillez sélectionner un fichier !");
    return;
  }

  // Utilisation de FormData pour envoyer le fichier
  const formData = new FormData();
  formData.append('title', docForm.title);
  formData.append('type', docForm.type);
  formData.append('price', docForm.price);
  formData.append('description', docForm.description);
  formData.append('file', selectedFile.value);

  try {
    await axios.post('http://localhost:8080/api/documents', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    });
    
    alert('Fichier uploadé et enregistré avec succès !');
    
    // Reset
    docForm.title = '';
    docForm.description = '';
    selectedFile.value = null;
    document.querySelector('input[type="file"]').value = ""; // Vide l'input
    
  } catch (error) {
    console.error(error);
    alert("Erreur lors de l'upload. Vérifie la taille du fichier.");
  }
};
</script>

<style scoped>
.admin-page {
  background-color: #f3f4f6;
  min-height: 100vh;
  padding-top: 100px;
  padding-bottom: 50px;
}

.admin-container {
  max-width: 800px;
  margin: 0 auto;
  background: white;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 10px 30px rgba(0,0,0,0.05);
}

h1 { text-align: center; color: #1e1b4b; margin-bottom: 30px; }
h2 { color: #4f46e5; margin-bottom: 20px; border-bottom: 2px solid #e5e7eb; padding-bottom: 10px; }

.tabs { display: flex; gap: 10px; margin-bottom: 30px; }
.tabs button {
  flex: 1; padding: 12px; border: none; background: #e5e7eb; cursor: pointer;
  font-weight: bold; border-radius: 10px; transition: all 0.3s;
}
.tabs button.active { background: #4f46e5; color: white; }

.form-group { margin-bottom: 20px; }
label { display: block; font-weight: 600; margin-bottom: 8px; color: #374151; }
input, textarea, select {
  width: 100%; padding: 12px; border: 1px solid #d1d5db; border-radius: 8px;
  font-size: 1rem; box-sizing: border-box;
}
input:focus, textarea:focus, select:focus {
  outline: none; border-color: #4f46e5; box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.1);
}

.emoji-input { font-size: 1.5rem; text-align: center; width: 60px; }

.btn-save {
  width: 100%; background: #111827; color: white; padding: 15px; border: none;
  border-radius: 8px; font-weight: bold; font-size: 1.1rem; cursor: pointer;
  transition: background 0.3s;
}
.btn-save:hover { background: #000; }
</style>