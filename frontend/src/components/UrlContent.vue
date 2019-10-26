<template>
  <div class="wrapper">
    <div class="content-name">{{ contentName }}</div>
    <div class="link">
      <a :href="url">{{ url }}</a>
      <button class="clip-btn" @click="toClipBoard">
        <clip-icon />
      </button>
    </div>
    <figure class="qr">
      <img :src="image" />
    </figure>
    <toast :show="toastState">クリップボードにコピーしました</toast>
  </div>
</template>

<script>
import ClipIcon from 'vue-material-design-icons/Clipboard.vue';
import Toast from './Toast.vue';

export default {
  props: {
    id: String,
  },
  components: { ClipIcon, Toast },
  data() {
    return {
      contentName: 'ContentName',
      url: 'https://example.com',
      image: 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/QR_code_desktop_Japanese_Wikipedia.svg/1200px-QR_code_desktop_Japanese_Wikipedia.svg.png',
      toastState: false,
    };
  },
  methods: {
    toClipBoard() {
      navigator
        .clipboard
        .writeText(this.url)
        .then(() => {
          this.toastState = true;
          setTimeout(() => {
            this.toastState = false;
          }, 2000);
        });
    },
  },
};
</script>

<style scoped>
  .wrapper {
    padding: 32px 128px;
    display: flex;
    flex-direction: column;
    background-color: var(--color-white);
  }

  .wrapper > * {
    margin-bottom: 48px;
  }

  .wrapper > *:last-child {
    margin-bottom: 0;
  }

  .content-name {
    font-size: 48px;
  }

  .link {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    font-size: 32px;
  }

  .link a {
    color: var(--color-aqua);
    margin-right: 8px;
  }

  .clip-btn {
    display: flex;
    justify-content: center;
    align-content: center;
    width: 32px;
    height: 32px;
    border: 1px solid var(--color-black);
    border-radius: 7px;
    font-size: 16px;
    box-sizing: border-box;
    line-height: 16px;
    transition: 300ms ease;
  }

  .clip-btn:hover {
    background-color: var(--color-aqua);
    border-color: var(--color-aqua);
  }

  .clip-btn:active {
    filter: brightness(0.9);
  }

  .clip-btn >>> svg {
    bottom: 0;
  }

  .qr {
    width: 320px;
    height: 320px;
    margin: 0 auto;
  }

  .qr > img {
    width: 100%;
    height: 100%;
  }
</style>
