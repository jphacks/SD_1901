<template>
  <basic-button
    @click="() => $emit('click')"
    color="pink"
    :image="image"
  >
    <image-icon />
  </basic-button>
</template>

<script>
import ImageIcon from 'vue-material-design-icons/Image.vue';
import BasicButton from './BasicButton.vue';

export default {
  components: { ImageIcon, BasicButton },
  props: {
    image: String,
  },
};
</script>
