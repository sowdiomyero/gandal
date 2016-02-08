'use strict';

var db=require("../config/database");

module.exports={
	compte:db.sequelize.define('compte',
	{
	            idCompte:{
	              type: db.Sequelize.INTEGER,
	              primaryKey: true,
	              autoIncrement: true
	            },
	            email:{
	              type:db.Sequelize.STRING
	            },
	            password:{
	              type:db.Sequelize.STRING
	            },
	            etat:{
	              type:db.Sequelize.STRING
	            },
	}
	,
	{
	   freezeTableName: true
	})
}