<template>
  <basic-button
    @click="() => $emit('click')"
    color="pink"
    :image="src"
  >
    <image-icon />
  </basic-button>
</template>

<script>
import ImageIcon from 'vue-material-design-icons/Image.vue';
import BasicButton from './BasicButton.vue';
import Api from '../js/api';

export default {
  components: { ImageIcon, BasicButton },
  props: {
    desk_id: String,
    item_id: String,
  },
  computed: {
    src() {
      return Api.getFileURL(this.desk_id, this.item_id);
    },
  },
};
</script>
