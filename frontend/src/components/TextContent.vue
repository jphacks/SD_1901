<template>
   <div class="text-content-style">
       <span>{{ text }}</span>
   </div>
</template>

<script>
import Repo from '../js/repository';

export default {
  props: {
    desk_id: String,
    item_id: String,
  },
  data() {
    return {
      text: '',
    };
  },
  methods: {
    async getData() {
      this.text = await Repo.getFile(this.$store)(this.desk_id, this.item_id);
    },
  },
  created() {
    this.getData();
  },
};
</script>

<style scoped>
.text-content-style {
    border: 10px solid var(--color-lime);
    background: var(--color-white);
    border-radius: 30px;
    width: 60vh;
    height: 60vh;
    font-size: 2rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
