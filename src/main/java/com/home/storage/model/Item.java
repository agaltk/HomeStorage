package com.home.storage.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item  extends BaseEntity {

    @Builder
    public Item(Long id, String name, String description, Date purchaseDate, Date openDate, Long percentageOfUse, Long price, ItemType itemType, Brand brand, StatusOfItem statusOfItem) {
        super(id);
        this.name = name;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.openDate = openDate;
        this.percentageOfUse = percentageOfUse;
        this.price = price;
        this.itemType = itemType;
        this.brand = brand;
        this.statusOfItem = statusOfItem;
    }

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name ="purchase_date")
    private Date purchaseDate;

    @Column(name ="open_date")
    private Date openDate;

    @Column(name ="percentage_of_use")
    private Long percentageOfUse;

    @Column(name ="price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusOfItem statusOfItem;

}
