<template>
    <basic-button
        @click="() => $emit('click')"
        color="green"
    >
        <folder-plus-icon />
    </basic-button>
</template>

<script>
import FolderPlusIcon from 'vue-material-design-icons/FolderPlus.vue';
import BasicButton from './BasicButton.vue';

export default {
  components: {
    FolderPlusIcon,
    BasicButton,
  },
};
</script>
