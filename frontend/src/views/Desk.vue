<template>
  <desk-template
    @change="upload"
    :deskId="deskId"
    :items="items" />
</template>

<script>
import DeskTemplate from '../components/DeskTemplate.vue';
import File from '../js/files';
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
  methods: {
    async upload(e) {
      const formData = File.createFormData(e.dataTransfer.files[0]);
      try {
        const res = await Api.uploadFile(this.deskId, formData).then(x => x.json());
        console.log(res);
      } catch (err) {
        // TODO: Errorハンドリング
        console.log(err);
      }
    },
  },
  async created() {
    this.items = await Api.getDesk(this.deskId).then(x => x.json()).then(x => x.item_info_list);
  },
};
</script>

<style scoped>

</style>
