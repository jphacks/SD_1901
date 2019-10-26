<template>
  <div class="movie-content-wrapper">
    <video :src="src" controls width="900" height="600" ></video>
  </div>
</template>

<script>
export default {
  props: {
    src: String,
  },
};
</script>

<style scoped>
.movie-content-wrapper {
    width: 900px;
    height: 600px;
}

</style>
