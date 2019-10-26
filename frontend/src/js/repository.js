import Api from './api';

const getFile = store => async (deskId, itemId) => {
  if (store.state.cache[deskId] && store.state.cache[deskId][itemId]) {
    return store.state.cache[deskId][itemId];
  }

  const content = await Api.getFile(deskId, itemId).then(x => x.text());
  store.commit('caching', { deskId, itemId, content });
  return content;
};

export default { getFile };
