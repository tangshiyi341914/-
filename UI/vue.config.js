let proxyObj = {};
const CompressionPlugin = require('compression-webpack-plugin');
proxyObj['/'] = {
  target: 'http://47.101.43.9:8081',
  changeOrigin: true,
  pathRewrite: {
    '^/api': '',
  },
};
module.exports = {
  lintOnSave: false,
  devServer: {
    host: '47.101.43.9',
    port: 8082,
    proxy: proxyObj,
  },
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === 'production') {
      return {
        plugins: [
          new CompressionPlugin({
            test: /\.js$|\.html$|\.css/,
            threshold: 1024,
            deleteOriginalAssets: false,
          }),
        ],
      };
    }
  },
};
