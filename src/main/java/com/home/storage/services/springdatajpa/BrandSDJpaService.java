package com.home.storage.services.springdatajpa;

import com.home.storage.model.Brand;
import com.home.storage.repositories.BrandRepository;
import com.home.storage.services.BrandService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default","springdatajpa"})
public class BrandSDJpaService implements BrandService {

    private final BrandRepository brandRepository;

    public BrandSDJpaService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Set<Brand> findAll() {
        Set<Brand> brands = new HashSet<>();
        brandRepository.findAll().forEach(brands::add);
        return brands;
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public Brand save(Brand object) {
        return brandRepository.save(object);
    }

    @Override
    public void delete(Brand object) {
        brandRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}
