'use strict';

/**
* Module dependencies.
*/
 var passport = require('passport');
 
var reponse={
		code:"",
		objet:"",
		message:{}
}

module.exports = function(app) {
	
	var userCtrl = require('../controllers/userCtrl');
	var userService = require('../services/userservice');

	// User Routes
	app.get('/',function(req,res){
		res.end("Profile");
	});
	app.get('/auth',function(req,res){
		res.json("ok");
	});

        app.get('/success',function(req,res){
                
		res.json("{code : 'SUCCESS'}");
	});
	app.post('/api/user', userCtrl.creation);
	/*app.post('/api/auth', passport.authenticate('local', { successRedirect: '/auth',
                                   failureRedirect: '/auth',
                                   failureFlash: true })
            );*/

app.post('/api/auth', function(req, res, next) {
		  passport.authenticate('local', function(err, user, info) {
		    if (err) { return next(err); }

		    if (!user) { //SI l'email n'existe pas  
				    	   reponse.code ="AUTH_FAILED";
				    	   reponse.message =info;
				    	   return res.json(reponse);
		    	       }
		    req.logIn(user, function(err) {
		      if (err) { return next(err); }
		      // reponse = userService.AllAboutUser(user);
		      return res.json(reponse);
		    });
		  })(req, res, next);
		});
};