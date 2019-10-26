<template>
    <div id="new">
        <div>
            <new-desk-icon />
            <p>create a new desk</p>
        </div>
    </div>
</template>

<script>
import NewDeskIcon from '../components/NewDeskIcon.vue';

export default {
  components: { NewDeskIcon },
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
