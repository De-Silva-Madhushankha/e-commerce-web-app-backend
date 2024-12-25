package com.bawantha.ecom.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("category")
    private String category;
    @JsonProperty("release_date")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") handled by front-end
    private Date releaseDate;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("available")
    private boolean available;

    @JsonProperty("image_name")
    private String imageName;
    @JsonProperty("image_type")
    private String imageType;

    @Lob
    @JsonProperty("image_data")
    private byte[] imageData;

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData(){return imageData;}

    public String getImageType(){return imageType;}

}
