<template>
  <div class="wrapper" :class="{ invisible: !show }">
    <div class="toast">
      <slot />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    show: Boolean,
  },
};
</script>

<style scoped>
  .wrapper {
    position: fixed;
    width: 100vw;
    left: 0;
    bottom: 32px;
    padding: 0;
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    justify-content: center;
    transition: 300ms ease-in-out;
  }

  .toast {
    display: inline;
    background: white;
    padding: 16px 32px;
    max-width: 30%;
    font-size: 16px;
    border-radius: 32px;
    box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.5);
  }

  .invisible {
    bottom: -64px;
  }
</style>
