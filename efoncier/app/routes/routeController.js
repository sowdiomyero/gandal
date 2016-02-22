module.exports = function (app) {
	app.get("/home", function(req, resp){
		console.log("routeController /home");
		resp.render("home.html");
	});

	app.get("/site/home", function(req, resp){
		console.log("routeController /site/home");
		resp.render("site/index.html");
	});
	app.get("/site/index", function(req, resp){
		console.log("routeController /site/index");
		resp.render("site/index2.html");
	});
}
