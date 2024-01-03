package zadatak.TIS.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CurrencyRate {

    @Id
    private Long id;
    @Column(name = "srednji_tecaj")
    @JsonProperty("srednji_tecaj")
    private String srednjiTecaj;


    public String getSrednjiTecaj() {
        return srednjiTecaj;
    }

    public void setSrednjiTecaj(String srednjiTecaj) {
        this.srednjiTecaj = srednjiTecaj;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
