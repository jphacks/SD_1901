<template>
    <div class="upload-url-content-wrapper">
        <p>Put your URL</p>
        <input type="text" class="upload-url-content" placeholder="https://~" v-model="url">
        <button
            class="upload-button"
            @click="() => $emit('click', url)"
        >upload</button>
    </div>
</template>

<script>
export default {
  data() {
    return {
      url: '',
    };
  },
};
</script>

<style scoped>
.upload-url-content-wrapper {
    border: 1px solid black;
    width: 60vh;
    height: 60vh;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    font-size: 24px;
    background: var(--color-white);
}

.upload-url-content {
    box-sizing: border-box;
    padding: 0 6px;
    font-size: 18px;
    width: 85%;
    height: 3rem;
}


.upload-button {
    width: 150px;
    height: 45px;
    font-size: 18px;
    background-color: var(--color-white);
    border: 1px solid var(--color-green);
    color: var(--color-green);
    border-radius: 10px;
}
.upload-button:active{
  filter: brightness(0.9);
}

</style>
