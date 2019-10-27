const createFormData = (file) => {
  const formData = new FormData();

  formData.append('file', file);
  return formData;
};

export default { createFormData };
