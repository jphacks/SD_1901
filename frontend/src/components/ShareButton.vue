<template>
    <div class="share-button-wrapper" @click="pbcopy">
        <share-item />
    </div>
</template>

<script>
import ShareItem from 'vue-material-design-icons/Share.vue';

export default {
  components: { ShareItem },
  data() {
    return {
      location: window.location.href,
    };
  },
  methods: {
    pbcopy() {
      navigator
        .clipboard
        .writeText(this.location);
    },
  },
};
</script>

<style scoped>
.share-button-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 170px;
    height: 170px;
    border-radius: 30px;
    border: 10px solid var(--color-green);
    transition: 300ms ease;
    font-size: 100px;
    box-sizing: border-box;
    color: var(--color-black);
    background-color: var(--color-white);
}
.share-button-wrapper:hover {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3);
}

.share-button-wrapper:active {
  filter: brightness(0.9);
}

.share-button-wrapper >>> svg {
    fill: var(--color-green);
    bottom: 0;
}
</style>
