<template>
  <basic-button
    color="aqua"
    @click="() => $emit('click')" >
    <url-icon />
  </basic-button>
</template>

<script>
import UrlIcon from 'vue-material-design-icons/Link.vue';
import BasicButton from './BasicButton.vue';

export default {
  components: { UrlIcon, BasicButton },
};
</script>

<style scoped>

</style>
