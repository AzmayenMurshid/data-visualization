package excelOperations;

public class gdpStructure {
    private String country;
    private String region;
    private double IMFestimate;
    private double UNestimate;
    private double WBestimate;
    private double IMFyear;
    private double UNyear;
    private double WByear;

    public gdpStructure(String country, String region, double IMFestimate, double UNestimate, double WBestimate,
                        double IMFyear, double UNyear, double WByear) {
        this.country = country;
        this.region = region;
        this.IMFestimate = IMFestimate;
        this.UNestimate = UNestimate;
        this.WBestimate = WBestimate;
        this.IMFyear = IMFyear;
        this.UNyear = UNyear;
        this.WByear = WByear;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public double getIMFestimate() {
        return IMFestimate;
    }

    public double getUNestimate() {
        return UNestimate;
    }

    public double getWBestimate() {
        return WBestimate;
    }

    public double getIMFyear() {
        return IMFyear;
    }

    public double getUNyear() {
        return UNyear;
    }

    public double getWByear() {
        return WByear;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setIMFestimate(double IMFestimate) {
        this.IMFestimate = IMFestimate;
    }

    public void setUNestimate(double UNestimate) {
        this.UNestimate = UNestimate;
    }

    public void setWBestimate(double WBestimate) {
        this.WBestimate = WBestimate;
    }

    public void setIMFyear(double IMFyear) {
        this.IMFyear = IMFyear;
    }

    public void setUNyear(double UNyear) {
        this.UNyear = UNyear;
    }

    public void setWByear(double WByear) {
        this.WByear = WByear;
    }

    @Override
    public String toString() {
        return "gdpStructure{" + "name=" + country +
                ", IMFestimate=" + IMFestimate +
                ", UNestimate=" + UNestimate +
                ", WBestimate=" + WBestimate +
                ", year=" + IMFyear + '}';
    }
}