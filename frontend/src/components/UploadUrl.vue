<template>
    <base-upload
        @click="() => $emit('click')"
    >
        <url-icon />
    </base-upload>
</template>

<script>
import UrlIcon from 'vue-material-design-icons/Link.vue';
import BaseUpload from './BaseUpload.vue';

export default {
  components: { UrlIcon, BaseUpload },
};
</script>
