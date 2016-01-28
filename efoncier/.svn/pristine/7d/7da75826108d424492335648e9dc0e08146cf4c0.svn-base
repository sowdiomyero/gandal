/*creation des tables*/
var Sequelize=require("sequelize");
module.exports.Sequelize=Sequelize;
var config=require('./config.json')['foncier'];
var sequelize =new Sequelize(config.database,config.username,config.password,config);
module.exports.db =sequelize; 


var models=require("../models/users");
var Compte=models.compte;
var User = models.user;
var Role = models.role;
var Parametre=models.parametre;
var Action=models.actions;
var Groupe=models.groupe;

   //creaions des association entre les tables 

    User.hasOne(Compte);  //pour un utilisateurs a droit à un seul Compte
    //associatio utilisateurs et roles
     Role.hasMany(User,{through:"UserRole"});
     User.hasMany(Role,{through:"UserRole"});
     //association roles et actions
     Role.hasMany(Action,{through:"ActionRole"});
     Action.hasMany(Role,{through:"ActionRole"});
     //Association groupe et Utilisateurs
     Groupe.hasMany(User,{through:"GroupeUser"});
     User.hasMany(Groupe,{through:"GroupeUser"});
     //association compte et parametre
     Compte.hasMany(Parametre,{through:"ParamCompte"});
     Parametre.hasMany(Compte,{through:"ParamCompte"});
    //creation des tables
    sequelize.sync();  

 
    