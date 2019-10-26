<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <menu-icon />
  </div>
</template>

<script>
// @ is an alias to /src
import MenuIcon from 'vue-material-design-icons/Menu.vue';
import HelloWorld from '@/components/HelloWorld.vue';

export default {
  name: 'home',
  components: {
    HelloWorld,
    MenuIcon,
  },
};
</script>
