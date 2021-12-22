package by.SabinaGlinskaya.levon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@NoArgsConstructor
@Table(name = "Scooter")
public class Scooter extends BaseEntity {
    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private float price;

    public Scooter(String model, float price) {
        this.model = model;
        this.price = price;
    }
}
