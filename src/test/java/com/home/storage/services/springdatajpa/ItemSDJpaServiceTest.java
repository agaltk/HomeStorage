package com.home.storage.services.springdatajpa;

import com.home.storage.model.Item;
import com.home.storage.repositories.ItemRepository;
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
class ItemSDJpaServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemSDJpaService service;

    Item item;

    @BeforeEach
    void setUp() {
        item = Item.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Item> returnItems = new HashSet<>();
        returnItems.add(Item.builder().id(1L).build());
        returnItems.add(Item.builder().id(2L).build());
        returnItems.add(Item.builder().id(3L).build());

        when(itemRepository.findAll()).thenReturn(returnItems);

        Set<Item> items = service.findAll();
        assertNotNull(items);
        assertEquals(3,items.size());

    }

    @Test
    void findByIdNutNull() {
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(item));
        Item retItem = service.findById(1L);
        assertNotNull(retItem);
    }
    @Test
    void findByIdNull() {
        when(itemRepository.findById(anyLong())).thenReturn(Optional.empty());
        Item retItem = service.findById(2L);
        assertNull(retItem);
    }

    @Test
    void save() {
        Item itemToSave = Item.builder().id(2L).build();
        when(itemRepository.save(any())).thenReturn(item);
        Item savedItem = service.save(itemToSave);
        assertNotNull(savedItem);
    }

    @Test
    void delete() {
        service.delete(item);
        verify(itemRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(itemRepository).deleteById(anyLong());
    }
}