<template>
  <div
    class="wrapper"
    @dragover="isDrag = true" >
    <div class="uploads">
      <upload-file @click="upload" />
      <upload-url @click="uploadURLModal" />
      <upload-text @click="uploadTextModal" />
      <share-button />
    </div>
    <storage :items="renderItems" />
    <upload
      v-if="isDrag"
      @change="e => onChange(e.dataTransfer.files[0])"
      @close="isDrag = false" />

    <modal
      v-if="isModalShow"
      @close="modalContent = null"
      :content="modalContent" />
  </div>
</template>

<script>
import Storage from './Storage.vue';
import Upload from './Upload.vue';
import Modal from './Modal.vue';
import UploadFile from './UploadFile.vue';
import UploadUrl from './UploadUrl.vue';
import UploadText from './UploadText.vue';
import UploadUrlContent from './UploadUrlContent.vue';
import UploadTextContent from './UploadTextContent.vue';
import ShareButton from './ShareButton.vue';
import Items from '../js/items';

export default {
  components: {
    Storage,
    Upload,
    Modal,
    UploadFile,
    UploadUrl,
    UploadText,
    ShareButton,
  },
  props: {
    items: Array,
    deskId: String,
  },
  data() {
    return {
      isDrag: false,
      modalContent: null,
    };
  },
  methods: {
    onChange(e) {
      this.isDrag = false;
      this.$emit('change', e);
    },
    updateModalContent(f) {
      this.modalContent = f;
    },
    upload() {
      const input = document.createElement('input');
      input.type = 'file';
      input.addEventListener('change', () => {
        this.$emit('change', input.files[0]);
      });
      input.click();
    },
    uploadURLModal() {
      this.modalContent = h => h(
        UploadUrlContent,
        {
          on: {
            click: (e) => {
              this.$emit('change', e);
              this.modalContent = null;
            },
          },
        },
      );
    },
    uploadTextModal() {
      this.modalContent = h => h(
        UploadTextContent,
        {
          on: {
            click: (e) => {
              this.$emit('change', e);
              this.modalContent = null;
            },
          },
        },
      );
    },
  },
  computed: {
    isModalShow() {
      return this.modalContent !== null;
    },
    renderItems() {
      return this
        .items
        .map(x => Items.toComponent({ ...x, desk_id: this.deskId }, this.updateModalContent));
    },
  },
};
</script>

<style scoped>
  .wrapper {
    width: 100vw;
    min-height: 100vh;
    box-sizing: border-box;
    padding: 32px 128px;
  }

  .uploads {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(170px, 1fr));
    grid-gap: 100px;
    margin-bottom: 32px;
  }

  .uploads * {
    margin: 0 auto;
  }
</style>
