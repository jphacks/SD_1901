<template>
  <div>
    <desk-template
      @change="upload"
      :deskId="deskId"
      :items="items" />

    <modal v-if="loading">
      <load />
    </modal>
  </div>
</template>

<script>
import DeskTemplate from '../components/DeskTemplate.vue';
import File from '../js/files';
import Api from '../js/api';
import Modal from '../components/Modal.vue';
import Load from '../components/Load.vue';

export default {
  components: { DeskTemplate, Modal, Load },
  computed: {
    deskId() {
      return this.$route.params.id;
    },
  },
  data() {
    return {
      items: [],
      loading: false,
    };
  },
  methods: {
    async upload(file) {
      console.log(file);
      this.loading = true;
      const formData = File.createFormData(file);
      try {
        const res = await Api.uploadFile(this.deskId, formData).then(x => x.json());
        this.items = await this.getDesk();
        this.loading = false;
        console.log(res);
      } catch (err) {
        // TODO: Errorハンドリング
        console.log(err);
        this.loading = false;
      }
    },
    getDesk() {
      return Api.getDesk(this.deskId).then(x => x.json()).then(x => x.item_info_list);
    },
  },
  async created() {
    this.items = await this.getDesk();
  },
};
</script>

<style scoped>

</style>
