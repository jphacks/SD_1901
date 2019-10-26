<template>
    <div class="undefined-content-wrapper">
        <div class="undefined-content-text">
            <div>name : {{data.name}}</div>
            <div>type : {{data.type}}</div>
        </div>
        <button class="undefined-button-style">
            download
            <download-icon @click="() => $emit('click')" />
        </button>
    </div>
</template>

<script>
import DownloadIcon from 'vue-material-design-icons/Download.vue';

export default {
  props: {
    data: Object,
  },
  components: { DownloadIcon },
};
</script>

<style scoped>
.undefined-content-wrapper {
    border: 10px solid var(--color-grey);
    border-radius: 30px;
    background-color: var(--color-white);
    width: 60vh;
    height: 60vh;
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.undefined-content-text {
    font-size: 24px;
}
.undefined-button-style {
    width: 150px;
    height: 42px;
    font-size: 24px;
    background-color: var(--color-grey);
    border-radius: 8px;
    position: absolute;
    bottom: 10px;
}
.undefined-button-style:active {
  filter: brightness(0.9);
}

</style>
