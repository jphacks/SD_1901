<template>
    <base-upload
        @click="() => $emit('click')"
    >
        <text-icon />
    </base-upload>
</template>

<script>
import TextIcon from 'vue-material-design-icons/FormatTitle.vue';
import BaseUpload from './BaseUpload.vue';

export default {
  components: { TextIcon, BaseUpload },
};
</script>
