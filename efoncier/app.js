
var express     = require('express');
//var db          = require('./app/config/sequelize');
var passport=require("passport");
var app = express();



//require('./app/config/passport');
require('./app/config/express')(app,passport);
//require("./app/routes/userRoute")(app);



app.set('views', __dirname + '/views');
app.engine('html', require('consolidate').handlebars);
app.set('view engine', 'html');
app.use('/bower_components',  express.static(__dirname + '/public/bower_components'));
app.use(express.static(__dirname + '/public'));

var route = require("./app/routes/routeController")(app);


app.listen(8080);
console.log('Serveur ecoute sur le port 8080...');
 
//expose app
exports = module.exports = app;
