<template>
    <base-upload
        @click="() => $emit('click')"
    >
        <plus-icon />
    </base-upload>
</template>

<script>
import PlusIcon from 'vue-material-design-icons/Plus.vue';
import BaseUpload from './BaseUpload.vue';

export default {
  components: { PlusIcon, BaseUpload },
};
</script>
