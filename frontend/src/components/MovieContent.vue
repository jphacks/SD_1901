<template>
  <div class="movie-content-wrapper">
    <video :src="src" controls width="900" height="600" ></video>
  </div>
</template>

<script>
import Api from '../js/api';

export default {
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

<style scoped>
.movie-content-wrapper {
    width: 900px;
    height: 600px;
}

</style>
