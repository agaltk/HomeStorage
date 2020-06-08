package com.home.storage.services.springdatajpa;

import com.home.storage.model.Brand;
import com.home.storage.repositories.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandSDJpaServiceTest {

    @Mock
    BrandRepository brandRepository;

    @InjectMocks
    BrandSDJpaService service;

    Brand brand;

    @BeforeEach
    void setUp() {
        brand = Brand.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Brand> returnBrands = new HashSet<>();
        returnBrands.add(Brand.builder().id(1L).build());
        returnBrands.add(Brand.builder().id(2L).build());
        returnBrands.add(Brand.builder().id(3L).build());

        when(brandRepository.findAll()).thenReturn(returnBrands);

        Set<Brand> brands = service.findAll();
        assertNotNull(brands);
        assertEquals(3,brands.size());

    }

    @Test
    void findByIdNutNull() {
        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
        Brand retBrand = service.findById(1L);
        assertNotNull(retBrand);
    }
    @Test
    void findByIdNull() {
        when(brandRepository.findById(anyLong())).thenReturn(Optional.empty());
        Brand retBrand = service.findById(2L);
        assertNull(retBrand);
    }

    @Test
    void save() {
        Brand brandToSave = Brand.builder().id(2L).build();
        when(brandRepository.save(any())).thenReturn(brand);
        Brand savedBrand = service.save(brandToSave);
        assertNotNull(savedBrand);
    }

    @Test
    void delete() {
        service.delete(brand);
        verify(brandRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(brandRepository).deleteById(anyLong());
    }
}