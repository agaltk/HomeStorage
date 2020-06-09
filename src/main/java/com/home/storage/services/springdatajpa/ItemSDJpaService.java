package com.home.storage.services.springdatajpa;

import com.home.storage.model.Item;
import com.home.storage.repositories.ItemRepository;
import com.home.storage.services.ItemService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default","springdatajpa"})
public class ItemSDJpaService implements ItemService{
    private final ItemRepository itemRepository;

    public ItemSDJpaService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> findAll() {
        Set<Item> items = new HashSet<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public Item findById(Long aLong) {
        return itemRepository.findById(aLong).orElse(null);
    }

    @Override
    public Item save(Item object) {
        return itemRepository.save(object);
    }

    @Override
    public void delete(Item object) {
        itemRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        itemRepository.deleteById(aLong);
    }
}
