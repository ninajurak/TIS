package zadatak.TIS.model;

public class PopularProductDTO {
    private String name;
    private Double averageRating;

    public PopularProductDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
