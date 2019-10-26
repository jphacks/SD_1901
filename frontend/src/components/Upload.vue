<template>
  <modal @close="() => $emit('close')" class="modal">
    <input
      type="file"
      class="file-input"
      @change="e => $emit('change', e)"
      @dragleave="() => $emit('close')"
      @drop.prevent="e => $emit('change', e)" />
    <upload-icon class="upload-icon" />
  </modal>
</template>

<script>
import UploadIcon from 'vue-material-design-icons/CloudUploadOutline.vue';
import Modal from './Modal.vue';

export default {
  components: { Modal, UploadIcon },
  methods: {
    drop(e) {
      console.log(e);
    },
  },
};
</script>

<style scoped>
  .modal {
    font-size: 320px;
  }

  .file-input {
    position: absolute;
    opacity: 0;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }

  .upload-icon {
    pointer-events: none;
    animation: show 500ms ease;
  }

  @keyframes show {
    from {
      transform: translateY(100px);
    }

    to {
      transform: translateY(0);
    }
  }
</style>
