package com.home.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "status_of_item")
public class StatusOfItem extends BaseEntity {
    @Column(name="name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Item> items = new HashSet<>();

    public StatusOfItem(Long id, String name, Set<Item> items) {
        super(id);
        this.name = name;
        if(items == null || items.size() > 0)
            this.items = items;
    }
}
