<template>
    <div id="new">
        <div>
            <new-desk-icon @click="createNewDesk"/>
            <p>create a new desk</p>
        </div>
    </div>
</template>

<script>
import NewDeskIcon from '../components/NewDeskIcon.vue';
import api from '../js/api';

export default {
  components: { NewDeskIcon },
  methods: {
    async createNewDesk() {
      try {
        const res = await api.createDesk().then(x => x.json());
        this.$router.push(`/desk/${res.desk_id}`);
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>

<style scoped>
#new {
    min-height: 100vh;
    width: 100vw;
    background-color: var(--color-white);
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>
