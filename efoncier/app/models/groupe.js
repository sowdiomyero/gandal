'use strict';

var db=require("../config/database");

module.exports={
	groupe:db.sequelize.define('groupe', {
   idGroupe:{
    type: db.Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
   libelleGroupe: {
    type: db.Sequelize.STRING
  }
},
{
  freezeTableName:true
})
}