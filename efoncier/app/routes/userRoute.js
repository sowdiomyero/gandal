'use strict';

module.exports = function(app) {
 

	/*
	var userCtrl = require('../controllers/userCtrl');
	var userService = require('../services/userservice');
	var passport = require('passport');
	var reponse={code:"",objet:"",message:{}};

	// User Routes
	app.get('/home',function(req,res){
		console.log('Appel au index.html catché, je vais rendre le fichier se trouvant sous public/index.html');
		res.render("index.html");
	});

	
	app.post('/api/user', userCtrl.creation);
	
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

*/
};