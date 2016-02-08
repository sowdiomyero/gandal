

// MODEL RELATION DEFINITION
var Action=require("../models/action").actions;
var Compte=require("../models/compte").compte;
var Groupe=require("../models/groupe").groupe;
var Parametre=require("../models/parametre").parametre;
var Role=require("../models/role").role;
var User=require("../models/users").user;

   //creaions des association entre les tables 

User.hasOne(Compte);  //pour un utilisateurs a droit à un seul Compte
    //associatio utilisateurs et roles
Role.hasMany(User,{through:"user_role"});
User.hasMany(Role,{through:"user_role"});
     //association roles et actions
Role.hasMany(Action,{through:"action_role"});
Action.hasMany(Role,{through:"action_role"});
     //Association groupe et Utilisateurs
Groupe.hasMany(User,{through:"groupe_user"});
User.hasMany(Groupe,{through:"groupe_user"});
     //association compte et parametre
Compte.hasMany(Parametre,{through:"param_compte"});
Parametre.hasMany(Compte,{through:"param_compte"});
    //creation des tables
var db=require("./database");
db.sequelize.sync();  


    