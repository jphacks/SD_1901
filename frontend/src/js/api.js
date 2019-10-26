const BASE_URL = 'http://localhost:3000';

const request = (endpoint, opt) => fetch(`${BASE_URL}${endpoint}`, opt);

const createDesk = () => request('/desk', { method: 'PUT' });

const getDesk = deskId => request(`/desk/${deskId}`);

const getFile = (deskId, itemId) => request(`/desk/${deskId}/${itemId}`);

const getFileURL = (deskId, itemId) => `${BASE_URL}/desk/${deskId}/${itemId}`;

const uploadFile = (deskId, formData) => request(`/desk/${deskId}`, { method: 'POST', body: formData });

const updateFile = (deskId, formData) => request(`/desk/${deskId}`, { method: 'PUT', body: formData });

export default {
  createDesk,
  getDesk,
  getFile,
  getFileURL,
  uploadFile,
  updateFile,
};
