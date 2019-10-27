<template>
    <div
        @click="() => $emit('click')"
        class="base-upload-wrapper"
    >
        <slot></slot>
    </div>
</template>

<style scoped>
  .base-upload-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 170px;
    height: 170px;
    border-radius: 30px;
    border: 4px dashed var(--color-black);
    transition: 300ms ease;
    font-size: 100px;
    box-sizing: border-box;
    color: var(--color-black);
    background-color: var(--color-white);
  }

  .base-upload-wrapper:hover {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3);
  }

  .base-upload-wrapper:active {
    filter: brightness(0.9);
  }

  .base-upload-wrapper >>> svg {
    bottom: 0;
    fill: var(--svg-color);
  }
</style>
