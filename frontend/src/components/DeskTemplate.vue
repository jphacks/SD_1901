<template>
  <div
    class="wrapper"
    @dragover="isDrag = true" >
    <div class="uploads"></div>
    <storage :items="renderItems" />
    <upload
      v-if="isDrag"
      @change="onChange"
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
import Items from '../js/items';

export default {
  components: { Storage, Upload, Modal },
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
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
</style>
