//les models 
'use strict';

var db=require("../config/database");

module.exports={
	user:db.sequelize.define('user',
{
          	id:{
              type: db.Sequelize.INTEGER,
              primaryKey: true,
              autoIncrement: true
            },
             nomUser: {
              type: db.Sequelize.STRING
            },
            prenomUser:{
            	type:db.Sequelize.STRING
            },
             adresse:{
              type:db.Sequelize.STRING
            },
             telephone:{
              type:db.Sequelize.STRING
            },
            cni:{
              type:db.Sequelize.STRING
            },

},
{
   freezeTableName: true
}),
/*compte:sequelize.define('compte',
{
            idCompte:{
              type: Sequelize.INTEGER,
              primaryKey: true,
              autoIncrement: true
            },
            email:{
              type:Sequelize.STRING
            },
            password:{
              type:Sequelize.STRING
            },
            etat:{
              type:Sequelize.STRING
            },
}
,
{
   freezeTableName: true
}), */

/*role:sequelize.define('role',
{
  idRole:{
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
   libelleRole: {
    type: Sequelize.STRING
  }
},
{
   freezeTableName: true
}), */
/*actions:sequelize.define('action',{
   idAction:{
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
   libelleAction: {
    type: Sequelize.STRING
  }

},
{
  freezeTableName:true
}), */
/*groupe:sequelize.define('groupe',{
   idGroupe:{
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
   libelleGroupe: {
    type: Sequelize.STRING
  }
},
{
  freezeTableName:true
}),
parametre:sequelize.define('groupe',{
   idParametre:{
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
   libelleParametre: {
    type: Sequelize.STRING
  }
},
{
  freezeTableName:true
}) */
}
