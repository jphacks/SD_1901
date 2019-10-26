<template>
   <div class="text-content-style">
       <span>{{text}}</span>
   </div>
</template>

<script>
export default {
  props: {
    text: String,
  },
};
</script>

<style scoped>
.text-content-style {
    border: 10px solid var(--color-lime);
    border-radius: 30px;
    width: 60vh;
    height: 60vh;
    font-size: 2rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
