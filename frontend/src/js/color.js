const colors = {
  aqua: '#4dd0e1',
  lime: '#cddc39',
  yellow: '#ffc107',
  purple: '#ab47bc',
  pink: '#e91e63',
  orange: '#ff9800',
  green: '#4caf50',
  blue: '#3f51b5',
  red: '#f44336',
  black: '#3c3c3c',
  white: '#ffffff',
  grey: '#9e9e9e',
};

const fromString = str => colors[str] || '#ffffff';

export default { fromString };
