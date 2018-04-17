package lt.akademija.reactexam;

public class Flight {

    public enum Status {
        LANDED, LATE, WAITING
    }
    //Data	Laikas	Atvyksta iš	Skrydžio Nr.	Kompanija	Statusas	SM
    private Long id;
    private String flightNumber;
    private String date;
    private String time;
    private String company;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
