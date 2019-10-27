<template>
    <basic-button
    @click="() => $emit('click')"
    color="blue"
    :image="src"
    >
    <play-circle-icon />
    </basic-button>
</template>

<script>
import PlayCircleIcon from 'vue-material-design-icons/PlayCircle.vue';
import BasicButton from './BasicButton.vue';
import Api from '../js/api';

export default {
  components: { PlayCircleIcon, BasicButton },
  props: {
    desk_id: String,
    thumbnail_id: String,
  },
  computed: {
    src() {
      return Api.getFileURL(this.desk_id, this.thumbnail_id);
    },
  },
};
</script>

<style scoped>
</style>
