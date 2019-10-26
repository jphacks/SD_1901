<template>
  <div class="hover-div"
   @click="() => $emit('click')"
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

export default {
  components: { FileIcon, DownloadIcon, BasicButton },
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
