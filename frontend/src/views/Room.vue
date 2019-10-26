<template>
  <room-template :items="items" />
</template>

<script>
import RoomTemplate from '../components/RoomTemplate.vue';

export default {
  components: { RoomTemplate },
  data() {
    return {
      items: [
        {
          type: 'url',
        },
        {
          type: 'image',
        },
        {
          type: 'file',
        },
        {
          type: 'video',
        },
        {
          type: 'url',
        },
        {
          type: 'image',
        },
        {
          type: 'file',
        },
        {
          type: 'video',
        },
        {
          type: 'audio',
        },
        {
          type: 'text',
        },
      ],
    };
  },
};
</script>

<style scoped>

</style>
