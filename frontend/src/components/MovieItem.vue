<template>
    <basic-button
    color="blue"
    :image="image"
    >
    <play-circle-icon />
    </basic-button>
</template>

<script>
import PlayCircleIcon from 'vue-material-design-icons/PlayCircle.vue';
import BasicButton from './BasicButton.vue';

export default {
  components: { PlayCircleIcon, BasicButton },
  props: {
    image: String,
  },
};
</script>

<style scoped>
</style>
