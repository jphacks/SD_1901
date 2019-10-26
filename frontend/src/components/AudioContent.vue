<template>
  <div class="audio-content-wrapper">
    <music-icon class="audio-icon-style"/>
    <audio class="audio-style" :src="src" controls></audio>
  </div>
</template>

<script>
import MusicIcon from 'vue-material-design-icons/Music.vue';

export default {
  components: { MusicIcon },
  props: {
    src: String,
  },
};
</script>

<style scoped>
.audio-content-wrapper {
    position: relative;
    width: 60vh;
    height: 60vh;
    background-color: var(--color-yellow);
    display: flex;
    justify-content: center;
    align-items: center;
}

.audio-icon-style {
    font-size: 300px;
}

.audio-style {
    position: absolute;
    bottom: 0;
}
</style>
