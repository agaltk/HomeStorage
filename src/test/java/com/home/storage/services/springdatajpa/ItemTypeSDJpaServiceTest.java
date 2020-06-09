package com.home.storage.services.springdatajpa;

import com.home.storage.model.ItemType;
import com.home.storage.repositories.ItemTypeRepository;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemTypeSDJpaServiceTest {

    @Mock
    ItemTypeRepository itemTypeRepository;

    @InjectMocks
    ItemTypeSDJpaService service;

    ItemType itemType;

    @BeforeEach
    void setUp() {
        itemType = ItemType.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<ItemType> returnItemTypes = new HashSet<>();
        returnItemTypes.add(ItemType.builder().id(1L).build());
        returnItemTypes.add(ItemType.builder().id(2L).build());
        returnItemTypes.add(ItemType.builder().id(3L).build());

        when(itemTypeRepository.findAll()).thenReturn(returnItemTypes);

        Set<ItemType> itemTypes = service.findAll();
        assertNotNull(itemTypes);
        assertEquals(3,itemTypes.size());

    }

    @Test
    void findByIdNutNull() {
        when(itemTypeRepository.findById(anyLong())).thenReturn(Optional.of(itemType));
        ItemType retItemType = service.findById(1L);
        assertNotNull(retItemType);
    }
    @Test
    void findByIdNull() {
        when(itemTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        ItemType retItemType = service.findById(2L);
        assertNull(retItemType);
    }

    @Test
    void save() {
        ItemType itemTypeToSave = ItemType.builder().id(2L).build();
        when(itemTypeRepository.save(any())).thenReturn(itemType);
        ItemType savedItemType = service.save(itemTypeToSave);
        assertNotNull(savedItemType);
    }

    @Test
    void delete() {
        service.delete(itemType);
        verify(itemTypeRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(itemTypeRepository).deleteById(anyLong());
    }
}
