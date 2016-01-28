package services;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 11/06/15
 * Time: 18:34
 * To change this template use File | Settings | File Templates.
 */
public class CodeRetour {
    String message;
    boolean isError;
    
    public static int RETOUR_OK=200;
    public static int RETOUR_KO=-100;
    public static int FORM_SUBMISSION_ERROR=-101;
    public static int EMAIL_DUPLIQUE=-300;
    public static int TEL_DUPLIQUE=-301;
    public static int PASSWORD_NOT_EQUAL=-302;
    public static int PASSWORD_NOT_VALID=-303;
    
    public static int EMAIL_NOT_VALIDE=-305;
    public static int TEL_NOT_VALIDE=-306;
    public static int NAME_NOT_VALIDE=-306;
    
    public static int EMPTY_FIELD=-400;
    public static int CNI_NOT_VALID=-304; 
    public static int NBELECTEUR_NOT_EMPTY=500;
    public static int NBOBJECTIF_NOT_EMPTY=501;
    public static int INPUT_LOCALITE_NOT_EMPTY=600;
    

    int codeRetour;

    public CodeRetour(String message, int codeRetour) {
        this.message = message;
        this.codeRetour = codeRetour;
    }

    public CodeRetour() {
    	message = "Action effectuée avec succès !";
    	codeRetour = RETOUR_OK;
    	isError= false;
    }

    public int getCodeRetour() {
        return codeRetour;
    }

    public void setCodeRetour(int codeRetour) {
        this.codeRetour = codeRetour;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}
    
    
}
