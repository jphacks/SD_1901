<template>
  <div
    class="image-wrapper"
    @click="dlImage"
    @mouseenter="mouseEnter"
    @mouseleave="mouseLeave"
  >
    <div class="image-style-wrapper">
        <img class="img-style" :src="src">
    </div>
    <div v-show="mouseOnFlg" class="hover-icon">
      <download-icon />
    </div>
  </div>
</template>

<script>
import DownloadIcon from 'vue-material-design-icons/Download.vue';
import Api from '../js/api';

export default {
  components: { DownloadIcon },
  props: {
    desk_id: String,
    item_id: String,
    name: String,
  },
  computed: {
    src() {
      return Api.getFileURL(this.desk_id, this.item_id);
    },
  },
  data() {
    return { mouseOnFlg: false };
  },
  methods: {
    mouseEnter() {
      this.mouseOnFlg = true;
    },
    mouseLeave() {
      this.mouseOnFlg = false;
    },
    async dlImage() {
      const blob = await fetch(this.src).then(x => x.blob());

      const anchor = document.createElement('a');
      anchor.href = window.URL.createObjectURL(blob);
      anchor.download = this.name;
      anchor.click();
    },
  },
};
</script>

<style scoped>
.image-wrapper {
    height: 80vh;
    width: 80vw;
    filter: brightness(100%) blur(100%);
    position: relative;
    background-color: #BDBDBD;
}
.image-wrapper:active {
    filter: brightness(0.9);
}
.image-style-wrapper {
    height: 100%;
    width: 100%
}

.img-style {
    height: 100%;
    width: 100%;
    object-fit: contain;
}

.hover-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
  font-size: 100px;
  box-sizing: border-box;
  background: rgba(0, 0, 0, 0.5);
}

.hover-icon >>> svg {
  bottom: 0;
  fill: white;
}
</style>
