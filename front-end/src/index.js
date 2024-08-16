import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';  // 모든 플젝에 적용하므로 누락주의

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <App />
);

