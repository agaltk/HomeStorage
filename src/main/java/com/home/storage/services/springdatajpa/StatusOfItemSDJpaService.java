package com.home.storage.services.springdatajpa;

import com.home.storage.model.ItemType;
import com.home.storage.model.StatusOfItem;
import com.home.storage.repositories.ItemTypeRepository;
import com.home.storage.repositories.StatusOfItemRepository;
import com.home.storage.services.StatusOfItemService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default","springdatajpa"})
public class StatusOfItemSDJpaService implements StatusOfItemService {

    private final StatusOfItemRepository statusOfItemRepository;

    public StatusOfItemSDJpaService(StatusOfItemRepository statusOfItemRepository) {
        this.statusOfItemRepository = statusOfItemRepository;
    }


    @Override
    public Set<StatusOfItem> findAll() {

        Set<StatusOfItem> items = new HashSet<>();
        statusOfItemRepository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public StatusOfItem findById(Long aLong) {

        return statusOfItemRepository.findById(aLong).orElse(null);
    }

    @Override
    public StatusOfItem save(StatusOfItem object) {

        return statusOfItemRepository.save(object);
    }

    @Override
    public void delete(StatusOfItem object) {
        statusOfItemRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        statusOfItemRepository.deleteById(aLong);
    }
}
