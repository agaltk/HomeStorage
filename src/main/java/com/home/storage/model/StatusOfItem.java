package com.home.storage.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "status_of_item")
public class StatusOfItem extends BaseEntity {
    @Column(name="name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Item> items = new HashSet<>();

    @Builder
    public StatusOfItem(Long id, String name, Set<Item> items) {
        super(id);
        this.name = name;
        if(items == null || items.size() > 0)
            this.items = items;
    }
}
