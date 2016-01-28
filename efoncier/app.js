
var express     = require('express');
var db          = require('./app/config/sequelize');
var app = express();
require('./app/config/passport');
var passport=require("passport");
//Initialisation Express
require('./app/config/express')(app,passport);

//expose les routes  
require("./app/routes/userRoute")(app);

app.listen(8080);
console.log('Serveur ecoute sur le port 8080...');
 
//expose app
exports = module.exports = app;

// var monitor = require('node-monitor');// insert monitor module-plugin

//     monitor.Monitor(server, options);//add server to monitor