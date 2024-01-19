const express = require('express');
const app = express();
const { createProxyMiddleware } = require('http-proxy-middleware');

const options = {
    target: 'http://localhost:8080', // target host
    changeOrigin: true, // needed for virtual hosted sites
    ws: true, // proxy websockets
    pathRewrite: { [`^/api`]: ""}
};

module.exports = function(app) {
    app.use(createProxyMiddleware('/api', options));
};