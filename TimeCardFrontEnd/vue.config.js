// vue.config.js
module.exports = {
  // proxy all webpack dev-server requests starting with /api 叩き先によるらしい
  // to our Spring Boot backend (localhost:8088) using http-proxy-middleware 8090に設定中
  // see https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8090', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
        ws: true,
        changeOrigin: true
      }
    }
  },
  // Change build paths to make them Maven compatible
  // see https://cli.vuejs.org/config/
  //outputDir: 'target/',
  assetsDir: 'static',
  outputDir: 'target/deliverables'
//  outputDir: "src/main/resources/static/",
  // outputDir: "src/main/resources/",
  // indexPath: "templates/index.html"
};
