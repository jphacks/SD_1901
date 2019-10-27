<template>
  <div class="load"></div>
</template>

<style scoped>
  .load {
    width: 100px;
    height: 100px;
    border: 8px solid var(--color-white);
    border-bottom-color: transparent;
    border-radius: 50%;
    animation: anim 1s linear 0s infinite normal;
  }

  @keyframes anim {
    from {
      transform: rotate(0deg);
    }
    to {
      transform: rotate(360deg);
    }
  }
</style>
