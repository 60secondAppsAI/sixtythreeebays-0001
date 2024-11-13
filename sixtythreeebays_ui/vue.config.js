module.exports = {
  chainWebpack: config => {
    config
    .plugin('html')
    .tap(args => {
      args[0].title = 'sixtythreeebays'
      return args
    })
  }
}
//const { defineConfig } = require("@vue/cli-service");    
//module.exports = defineConfig({
//  transpileDependencies: true,
//});
