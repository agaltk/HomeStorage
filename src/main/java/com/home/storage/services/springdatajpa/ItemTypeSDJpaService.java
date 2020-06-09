package com.home.storage.services.springdatajpa;

import com.home.storage.model.Item;
import com.home.storage.model.ItemType;
import com.home.storage.repositories.ItemTypeRepository;
import com.home.storage.services.ItemTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default","springdatajpa"})
public class ItemTypeSDJpaService implements ItemTypeService {
    private final ItemTypeRepository itemTypeRepository;

    public ItemTypeSDJpaService(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    @Override
    public Set<ItemType> findAll() {

        Set<ItemType> items = new HashSet<>();
        itemTypeRepository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public ItemType findById(Long id) {

        return itemTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ItemType save(ItemType object) {

        return itemTypeRepository.save(object);
    }

    @Override
    public void delete(ItemType object) {
        itemTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        itemTypeRepository.deleteById(id);
    }
}
