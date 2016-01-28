var bcrypt = require('node-bcrypt');
var generator = require('generate-password');
var isPhone = require('is-phone');
var validator=require("validator");

var nodemailer = require("nodemailer");
var indicatifOpSen=["33","30","36","77","70","78","76"]
var roles=["admin","notaire","cadastre"];exports.generateHash = function(){
                        var pass = generator.generate({
                            length: 10,
                            numbers: true
                        });
                        var salt=bcrypt.gensalt();
                         var hached= bcrypt.hashpw(pass, salt);
                          return {pass: pass,
                                   hached : hached
                                 }
                        } 
  //verifir is un numero telephone tes valide
  exports.VerifTel=function(tel){
  var estTel=true;
   var str=tel.replace(/ /g,"");
 
  var taille=str.length;
  if(taille<9)
    estTel=false;
   else{
   var  deb=str.substr(0,1);
   var  zeros=str.substr(0,2);
   var indicatifSenplus=str.substr(1,3);//recupere l'incatif du nimero si le numero commence par un "+"
   var indicatifSenzero=str.substr(2,3);//recupere l'incatif du numero de téléphone s'il commence par "00";
   var indiOperazero=str.substr(5,2);//recupere l'indicatif de l'operateur senegalais si le numero commence par "00"
   var indiOperaplus=str.substr(4,2);
  if(!validator.isNumeric(str))
     estTel=false;
   else{
   if(deb=="-")
    estTel=false;
  else{

        if(deb=="+"){
            if(indicatifSenplus!="221")
             estTel=false;
            else{
              if(indicatifOpSen.indexOf(indiOperaplus)==-1)
                estTel=false;
              else
                if(str.substr(6).length<7)
                  estTel=false;
            }

        }
      else{
        if(zeros=="00"){
            if(indicatifSenzero!="221")
             estTel=false;
            else{
              if(indicatifOpSen.indexOf(indiOperazero)==-1)
                estTel=false;
              else
                if(str.substr(7).length<7)
                  estTel=false;
            }
          } 
        else{
              var sansindi=str.substr(0,2);
              if(indicatifOpSen.indexOf(sansindi)==-1)
                estTel=false;
              else
                if(str.length!=9)
                  estTel=false;
        } 

      }

      
   }
  
  }
  
}  
return estTel; 
}
// validation des parametre de l'utilisateurs
var expValidmail = new RegExp("^[a-z0-9._-]+@[a-z0-9._-]{2,}[\.]{1}[a-z]{2,4}$","gi");
//var expValidTel= new RegExp("^[([(\+)(00)]221)]?[(70)(77)(76)(30)(33)(36)]{1}[0-9]{7}$","gi");

exports.Insertions = function(user, User, Role, Compte){

                            //generation et hachage du password
                            user.passwordHached = exports.generateHash().hached;
                            user.password = exports.generateHash().pass;

                            User.create({
                                 prenomUser: user.prenom,
                                 nomUser: user.nom,
                                 fonction :user.fonction,
                                 telephone :user.telephone,
                                 fax:user.fax,
                                 adresse : user.adresse,
                                 cni : user.cni}).success(function(userCreated) {
                                                               console.log("User créé");
                                                               Compte.create({
                                                               email:user.email,
                                                               password : user.passwordHached,
                                                               userId : userCreated.id
                                                              });
                                                              Role.find({where : { libelleRole : user.role }}).then(function(result){
                                                                 userCreated.setRoles([result]).success(function() {
                                                                 console.log("CREATION SUCCESS");
                                                                //  return res.status(200).json(userCreated);
                                                               });
                                                             });
                                
                                                       });


                          }

exports.ValidUser=function(user,Role){
     var reponse={
    code:"",
    objet:"",
    message:{}
}

           if(!user.email.match(expValidmail)) {
                           reponse.code="CREATE_FAILED";
                           reponse.message.mail="email incorrect";
                         }
            if(!exports.VerifTel(user.telephone)) {
            //if(!user.telephone.match(expValidTel)){

                            reponse.code="CREATE_FAILED";
                             reponse.message.telephone="numero de telephone incorrect";                          
                            }
            if(!validator.isNumeric(user.cni.replace(/ /g,""))) {
                            reponse.code="CREATE_FAILED";
                            reponse.message.cni="verifier les caractères du numero d'identité nationale";                          
                            }
            if(!validator.isAlpha(user.nom) && !validator.isAlphanumeric(user.nom)) {
                            reponse.code="CREATE_FAILED";
                             reponse.message.nom="le nomm  doit etre composé uniquement de lettres et chiffres";                          
                            }
            if(!validator.isAlpha(user.prenom) && !validator.isAlphanumeric(user.prenom)) {
                            reponse.code="CREATE_FAILED";
                             reponse.message.prenom="le prenom doit etre composé uniquement de lettres et chiffres";                          
                            }
            if(validator.isNull(user.adresse)) 
                             {
                            reponse.code="CREATE_FAILED";
                             reponse.message.adresse="l'adresse ne doit pas etre nulle";                          
                            }
          return reponse;  
}

exports.EnvoiMail = function(user){
          var smtpTransport = nodemailer.createTransport();
              smtpTransport.sendMail({
                                       from: "Fred Foo <acolyid@gmail.com>",
                                       to: "beuguedabakh@hotmail.fr",
                                       subject: "Vos identifiants sur efoncier",
                                       text: "Bienvenue",
                                       html: "<b>Hello world</b>"
                                     }, function(error, response){
                                           if(error){
                                                 console.log(error);
                                              }
                                           else{
                                                 console.log("Message sent : " + response.message);
                                                }
                    smtpTransport.close();
              });
}

//Creation d'utilisateur
module.exports.creationUser = function(user, User, Role, Compte,res){
    var reponse={
        code:"",
        objet:"",
        message:{}
    }

                         var rep = exports.ValidUser(user);

                         if(rep.code=="CREATE_FAILED"){  //S'il y a un champ invalide

                            res.status(200).json(rep);
                          }
                          else{                           
                          Compte.find({ where: { email: user.email } })
                            .done(function(error, userFound) {

                              if(userFound) { //Si l'email est ddeja utilisé
                                rep.code = "EMAIL_DUPLICATED";
                                rep.message = "Email deja utilisé";
                                rep.objet ="";

                                res.status(200).json(rep);
                              }    
                              else{  // Si toutes les verifications sont faites et l'insertion est possible
                                    exports.Insertions(user);
                                    rep.code = "CREATE_SUCCESS";
                                    rep.message = "Création réussie";
                                    rep.objet =user;

                                    res.status(200).json(rep);

 
                                   }
                            });
                       }
} 
