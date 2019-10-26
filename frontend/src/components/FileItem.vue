<template>
  <div class="hover-div"
   @click="dl"
   @mouseenter="mouseEnter"
   @mouseleave="mouseLeave"
   >
    <basic-button color="orange">
      <file-icon />
    </basic-button>
    <div class="download-icon-div" v-show="mouseOnFlg">
      <download-icon/>
    </div>
  </div>
</template>

<script>
import FileIcon from 'vue-material-design-icons/File.vue';
import DownloadIcon from 'vue-material-design-icons/Download.vue';
import BasicButton from './BasicButton.vue';
import Api from '../js/api';

export default {
  components: { FileIcon, DownloadIcon, BasicButton },
  props: {
    desk_id: String,
    item_id: String,
    name: String,
  },
  data() {
    return { mouseOnFlg: false };
  },
  computed: {
    src() {
      return Api.getFileURL(this.desk_id, this.item_id);
    },
  },
  methods: {
    mouseEnter() {
      this.mouseOnFlg = true;
    },
    mouseLeave() {
      this.mouseOnFlg = false;
    },
    async dl() {
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
.hover-div {
    height: 170px;
    width: 170px;
    filter: brightness(100%) blur(100%);
    position: relative;
}

.download-icon-div {
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
  border-radius: 30px;
}

.download-icon-div >>> svg {
  bottom: 0;
  fill: white;
}

</style>
