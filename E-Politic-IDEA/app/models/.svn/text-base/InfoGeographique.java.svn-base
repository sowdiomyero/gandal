package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 02/06/15
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class InfoGeographique{

    @Column(name = "INFO_GEO_LNG")
    private double longitude;
    @Column(name = "INFO_GEO_GOO_CODE")
    private String googleCodeQuartier;
    @Column(name = "INFO_GEO_LAT")
    private double latitude;
    @Column(name = "INFO_GEO_ALT")
    private double altitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getGoogleCodeQuartier() {
        return googleCodeQuartier;
    }

    public void setGoogleCodeQuartier(String googleCodeQuartier) {
        this.googleCodeQuartier = googleCodeQuartier;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
