/**
 * Module dependencies.
 */
var express = require('express');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var cookieParser=require('cookie-parser');
var session=require('express-session');
var flash = require("connect-flash");

module.exports = function(app,passport) {

    console.log('Initialisation de Express...');

    // // request body parsing middleware should be above methodOverride
    app.use(bodyParser.urlencoded({ extended: true }));
    app.use(bodyParser.json());
    app.use(methodOverride());
    app.use(cookieParser());
	app.use(session({secret: 'secret  code'}));
	app.use(passport.initialize());
	app.use(passport.session());
	app.use(flash());

};
