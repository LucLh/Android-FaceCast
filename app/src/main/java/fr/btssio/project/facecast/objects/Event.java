package fr.btssio.project.facecast.objects;

/**
 * Created by BTS SIO on 22/04/2017.
 */

public class Event {

    private String name;
    private String date;
    private Integer numberDays;
    private String departement;
    private String city;
    private String eventType;

    public Event(String name,String date,Integer numberDays,String departement,String city,String eventType) {
        super();
        this.name = name;
        this.date = date;
        this.numberDays = numberDays;
        this.departement = departement;
        this.city = city;
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Integer getNumberDays() {
        return numberDays;
    }

    public String getDepartement() {
        return departement;
    }

    public String getCity() {
        return city;
    }

    public String getEventType() { return eventType; }

    public void setName(String value) {
        name = value;
    }

    public void setDate(String value) {
        date = value;
    }

    public void setNumberDays(Integer value) {
        numberDays = value;
    }

    public void setDepartement(String value) {
        departement = value;
    }

    public void setCity(String value) {
        city = value;
    }

    public void setEventType(String value) { eventType = value; }

    @Override
    public String toString() {
        return name + " " + date + " " + numberDays + " " + departement + " " + city;
    }

}
