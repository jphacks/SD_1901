<template>
  <basic-button
    @click="() => $emit('click')"
    color="yellow"
  >
    <music-icon />
  </basic-button>
</template>

<script>
import MusicIcon from 'vue-material-design-icons/Music.vue';
import BasicButton from './BasicButton.vue';

export default {
  components: { MusicIcon, BasicButton },
};
</script>
