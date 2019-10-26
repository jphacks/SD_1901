<template>
  <desk-template :deskId="deskId" :items="items" />
</template>

<script>
import DeskTemplate from '../components/DeskTemplate.vue';
import Api from '../js/api';

export default {
  components: { DeskTemplate },
  computed: {
    deskId() {
      return this.$route.params.id;
    },
  },
  data() {
    return {
      items: [],
    };
  },
  async created() {
    this.items = await Api.getDesk(this.deskId).then(x => x.json()).then(x => x.item_info_list);
  },
};
</script>

<style scoped>

</style>
