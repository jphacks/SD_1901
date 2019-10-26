<template>
  <div id="app">
    <basic-button
      color="red"
      image="https://avatars0.githubusercontent.com/u/13715034?s=460&v=4" >
      <file-icon />
    </basic-button>
    <basic-button color="aqua" >
      <file-icon />
    </basic-button>
    <url-item />
    <url-content />
    <router-view/>
  </div>
</template>

<script>
import FileIcon from 'vue-material-design-icons/Image.vue';
import BasicButton from './components/BasicButton.vue';
import UrlItem from './components/UrlItem.vue';
import UrlContent from './components/UrlContent.vue';

export default {
  components: {
    BasicButton,
    FileIcon,
    UrlItem,
    UrlContent,
  },
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
