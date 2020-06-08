package com.home.storage.repositories;

import com.home.storage.model.ItemType;
import org.springframework.data.repository.CrudRepository;

public interface ItemTypeRepository extends CrudRepository<ItemType, Long> {
}
