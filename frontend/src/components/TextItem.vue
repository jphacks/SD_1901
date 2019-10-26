<template>
    <basic-button
        color="lime"
        @click="() => $emit('click')"
    >
        <text-icon />
    </basic-button>
</template>

<script>
import TextIcon from 'vue-material-design-icons/FormatTitle.vue';
import BasicButton from './BasicButton.vue';

export default {
  components: { TextIcon, BasicButton },
};
</script>
