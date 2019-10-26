const createFormData = (file, fileName) => {
  const formData = new FormData();

  formData.append('file', file, fileName);
  return formData;
};

export default { createFormData };
