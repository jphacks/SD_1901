<script>
export default {
  props: {
    content: Function,
  },
  methods: {
    close(e) {
      if (e.target !== e.currentTarget) return;
      this.$emit('close');
    },
  },
  created() {
    document.body.style.overflow = 'hidden';
  },
  destroyed() {
    document.body.style.overflow = '';
  },
  render(createElement) {
    const attr = {
      class: 'wrap',
      on: {
        click: this.close,
      },
    };

    if (this.content) {
      return createElement(
        'div',
        attr,
        [this.content(createElement)],
      );
    }
    return createElement(
      'div',
      attr,
      this.$slots.default,
    );
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
