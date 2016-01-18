package sn.gandal.gesimmo.form;

import sn.gandal.gesimmo.dto.BasicResponse;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 06/02/15
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class RapportForm extends BasicResponse {



    public enum FORMAT_TYPE {
        PDF, CSV
    }
    public enum FREQUENCIES {
        //TOUS_LES_JOURS("Tous Les Jours"),
        TOUS_LES_LUNDIS("Les Lundis");
        //TOUS_LES_MOIS("Tous Les Mois");

        String desc;

        private FREQUENCIES(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum TRANSACTION_TYPE {
        ALL_TRANSACTIONS("Toutes Les Transactions"),
        TRANSACTIONS_FAILED("Les Transactions Rejettées"),
        TRANSACTIONS_SUCCESS("Les Transactions Réussies");

        String desc;

        private TRANSACTION_TYPE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    private String idRapport;

    //@NotNull @NotEmpty
    private String repertoireRapport;
    //@Email
    private String externalEmailToSend;  // à séparer par des ;

    //@NotNull @NotEmpty
    private FREQUENCIES frequence;
    //@NotNull @NotEmpty
    private TRANSACTION_TYPE typeTransaction;

    //@NotNull @NotEmpty
    private FORMAT_TYPE format;



    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }


    public String getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(String idRapport) {
        this.idRapport = idRapport;
    }

    public String getRepertoireRapport() {
        return repertoireRapport;
    }

    public void setRepertoireRapport(String repertoireRapport) {
        this.repertoireRapport = repertoireRapport;
    }

    public String getExternalEmailToSend() {
        return externalEmailToSend;
    }

    public void setExternalEmailToSend(String externalEmailToSend) {
        this.externalEmailToSend = externalEmailToSend;
    }

    public FREQUENCIES getFrequence() {
        return frequence;
    }

    public String getFrequenceValue() {
        if(frequence==null)
            return "";
        return frequence.name();
    }

    public String getFormatValue() {
        if(format==null)
            return "";
        return format.name();
    }

    public void setFrequence(FREQUENCIES frequence) {
        this.frequence = frequence;
    }


    public TRANSACTION_TYPE getTypeTransaction() {
        return typeTransaction;
    }

    public String getTransactionValue() {
        if(typeTransaction==null)
            return "";
        return typeTransaction.name();
    }

    public void setTypeTransaction(TRANSACTION_TYPE typeTransaction) {
        this.typeTransaction = typeTransaction;
    }



    public FORMAT_TYPE getFormat() {
        return format;
    }

    public void setFormat(FORMAT_TYPE format) {
        this.format = format;
    }


}
