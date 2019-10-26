<template>
    <basic-button
    @click="() => $emit('click')"
    color="blue"
    :image="item_id"
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
    item_id: String,
  },
};
</script>

<style scoped>
</style>
