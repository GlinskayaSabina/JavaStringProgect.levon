package by.SabinaGlinskaya.levon.dto;

import by.SabinaGlinskaya.levon.model.Scooter;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ScooterDTO {
    @NotBlank(message = "Model cannot be empty")
    private String model;

    private float price;

    public Scooter toScooter() {
        return new Scooter(
                this.getModel(),
                this.getPrice()
        );
    }
}
