<template>
  <div
    @click="() => $emit('click')"
    :style="style"
    class="wrapper"
  >
    <slot />
  </div>
</template>

<script>
import color from '../js/color';

export default {
  props: {
    color: String,
    image: String,
  },
  computed: {
    style() {
      return {
        backgroundImage: `url(${this.image})` || 'none',
        backgroundColor: this.image ? 'none' : color.fromString(this.color),
        border: (this.image && this.color) ? `16px solid ${color.fromString(this.color)}` : 'none',
        '--svg-color': (this.image && this.color) ? color.fromString(this.color) : color.fromString('black'),
      };
    },
  },
};
</script>

<style scoped>
  .wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 170px;
    height: 170px;
    border-radius: 30px;
    transition: 300ms ease;
    font-size: 100px;
    background-size: cover;
    box-sizing: border-box;
    color: var(--color-black);
  }

  .wrapper:hover {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3);
  }

  .wrapper:active {
    filter: brightness(0.9);
  }

  .wrapper >>> svg {
    bottom: 0;
    fill: var(--svg-color);
  }
</style>
