<template>
  <basic-button
    color="grey"
    @click="() => $emit('click')"
  >
    <question-icon />
  </basic-button>
</template>

<script>
import QuestionIcon from 'vue-material-design-icons/FileQuestion.vue';
import BasicButton from './BasicButton.vue';

export default {
  components: { BasicButton, QuestionIcon },
};
</script>
