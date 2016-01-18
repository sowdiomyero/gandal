/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier.utils;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mskane
 */
@Service
public class PasswordGenerator {
    
    
      public static final int NChar=8;
    private static final String[] maj={"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
                          "O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static final String[] min={"a","b","c","d","e","f","g","h","i","j","k","l","m","n",
                          "o","p","q","r","s","t","u","v","w","x","y","z"};
    private static final int[] chiffre={0,1,2,3,4,5,6,7,8,9,};
    private static final String[] charSpecial={"$","Â£","@","Âµ","*","%","&","#","{","(","[",
                                  "-",")","]","}","+"};

    public PasswordGenerator() {
    }
    
    public static String genererDefaultPassword() {
        int n;
        String password="";
        Random r = new Random();
        for(int i=0;i<NChar;i++) {
            n=r.nextInt(4); // gÃ©nere un nombre alÃ©atoire entre 0 et 3
            if(n==0) password += maj[r.nextInt(maj.length)];
            else if(n==1)
                password += min[r.nextInt(min.length)];
            else if(n==2)
                password += chiffre[r.nextInt(chiffre.length)];
            else password += charSpecial[r.nextInt(charSpecial.length)];
        }
        return password;
    }
    
     public static String genererDefaultPasswordAndCrypt() {
        int n;
        String password="";
        Random r = new Random();
        for(int i=0;i<NChar;i++) {
            n=r.nextInt(4); // gÃ©nere un nombre alÃ©atoire entre 0 et 3
            if(n==0) password += maj[r.nextInt(maj.length)];
            else if(n==1)
                password += min[r.nextInt(min.length)];
            else if(n==2)
                password += chiffre[r.nextInt(chiffre.length)];
            else password += charSpecial[r.nextInt(charSpecial.length)];
        }
        return crypter(password);
    }
    
   
    public static String crypter(String password) {
        StringBuilder stringBuilder = null;
        System.out.println("+++++++++++++++++++++++++++++++++++++ password::::::+ "+password);
        try {
             MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
             byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
             stringBuilder = new StringBuilder();
             for (int i = 0; i < hash.length; i++) {
                 stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
             }
         } catch (UnsupportedEncodingException ex) {
              Logger.getLogger(PasswordGenerator.class.getName()).log(Level.SEVERE, null, ex);
           } catch (NoSuchAlgorithmException ex) {
                 Logger.getLogger(PasswordGenerator.class.getName()).log(Level.SEVERE, null, ex);
             }
         return stringBuilder.toString();
    }
    
    //
    public static String splitEmailForLogin(String email){
        
        String[] tokens = email.trim().split("@");

          return tokens[0];
        
    }
    
    //Recupere la partie du email en partant du caractere @
    public static String splitEmailFqn(String email){
        
        String[] tokens = email.trim().split("@");

          return tokens[1];
        
    }
    
    //Recupere la partie du email en partant du caractere @
    public static String CryptLeftEmail (String email){
        
        String[] tokens = email.trim().split("@");
        String emailcrypt = "";
        for(int i=0; i< tokens[0].length(); i++)
            emailcrypt+="*";
        tokens[0] = emailcrypt;
          return emailcrypt+"@"+tokens[1];
        
    }
    
}
