<template>
  <div class="wrap" @click.self="() => $emit('close')">
    <slot />
  </div>
</template>

<script>
export default {
  created() {
    document.body.style.overflow = 'hidden';
  },
  destroyed() {
    document.body.style.overflow = '';
  },
};
</script>

<style scoped>
  .wrap {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(0, 0, 0, 0.3);
    backdrop-filter: blur(8px);
  }
</style>
