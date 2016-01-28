
var express     = require('express');
var http     = require('http');
//var db          = require('./app/config/sequelize');
var serveStatic = require("serve-static");
var app= express();
/*require('./app/config/passport');
var passport=require("passport");
//Initialisation Express
require('./app/config/express')(app,passport);

//expose les routes  
require("./app/routes/userRoute")(app);*/

app.use("/public/",serveStatic(__dirname + "/public"));
http.createServer(app).listen(8080);
/*app.use('views', './public');
app.get('/index', function(req, resp){
		console.log('Appel au index.html catché, je vais rendre le fichier se trouvant sous public/index.html');
		resp.render('index.html');

	});
app.listen(8080);*/
console.log('Serveur ecoute sur le port 8080...');
 
//expose app
//exports = module.exports = app;

// var monitor = require('node-monitor');// insert monitor module-plugin

//     monitor.Monitor(server, options);//add server to monitor