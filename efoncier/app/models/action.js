'use strict';

var db=require("../config/database");

module.exports={
	actions:db.sequelize.define('action',{
	   idAction:{
	    type: db.Sequelize.INTEGER,
	    primaryKey: true,
	    autoIncrement: true
	  },
	   libelleAction: {
	    type: db.Sequelize.STRING
	  }

	},
	{
	  freezeTableName:true
	})
}