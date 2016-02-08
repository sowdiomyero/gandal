// DATABASE INSTANCE DEFINITION

var Sequelize=require("sequelize");

var config=require('./config.json')['local'];
var sequelize =new Sequelize(config.database,config.username,config.password,config);
// exporter afin que le module suivant puisse y acceder
module.exports.sequelize =sequelize; 
module.exports.Sequelize=Sequelize; 