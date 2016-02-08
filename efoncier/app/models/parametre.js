'use strict';

var db=require("../config/database");

module.exports={
	
parametre:db.sequelize.define('parametre',{
   idParametre:{
    type: db.Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
   libelleParametre: {
    type: db.Sequelize.STRING
  }
},
{
  freezeTableName:true
})
}