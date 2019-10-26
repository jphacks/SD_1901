<script>
export default {
  props: {
    items: Array,
  },
  render(createElement) {
    return createElement(
      'div',
      { class: 'grid' },
      this.items.map(func => func(createElement)),
    );
  },
};
</script>

<style scoped>
  .grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(170px, 1fr));
    grid-gap: 100px;
  }

  .grid > * {
    margin: 0 auto;
  }
</style>
