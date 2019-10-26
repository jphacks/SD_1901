<template>
  <div class="file-content-flame">
  </div>
</template>

<script>
export default {
  props: {
    url: String,
  },
};
</script>

<style scoped>
.file-content-flame {
    border: 1px solid black;
    background-color: #dfdfdf;
}

.file-content-flame img {
    object-fit: contain;
}
</style>
