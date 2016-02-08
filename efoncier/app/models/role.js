//les models 
'use strict';
//var connexion = require("../config/sequelize");

var db=require("../config/database");
// recuperation des objets de la connexion
var Sequelize = db.Sequelize;
var sequelize = db.sequelize;

module.exports={
role: db.sequelize.define('role',
{
  idRole:{
    type: db.Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
   libelleRole: {
    type: db.Sequelize.STRING
  }
},
{
   freezeTableName: true
})

}