package models;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 10/06/15
 * Time: 01:17
 * To change this template use File | Settings | File Templates.
 */
public class JourInscription {

    public String day;
    public String value;

    public JourInscription() {
    }

    public JourInscription(String day, String value) {
        this.day = day;
        this.value = value;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
