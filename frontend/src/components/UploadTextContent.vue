<template>
    <div class="upload-text-content-wrapper">
        <p>Put your words.</p>
        <textarea
            rows="4"
            cols="40"
            placeholder="put your words. like 'todo: write readme'"
            class="upload-textarea-style"
            v-model="text"
            ></textarea>
        <button
            class="upload-button"
            @click="() => $emit('click', text)"
        >upload</button>
    </div>
</template>

<script>
export default {
  data() {
    return {
      text: '',
    };
  },
};
</script>

<style scoped>
.upload-text-content-wrapper {
    border: 1px solid black;
    width: 60vh;
    height: 60vh;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    font-size: 24px
}

.upload-textarea-style {
    box-sizing: border-box;
    width: 70%;
    height: 200px;
    padding: 12px;
    font-size: 16px;
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
