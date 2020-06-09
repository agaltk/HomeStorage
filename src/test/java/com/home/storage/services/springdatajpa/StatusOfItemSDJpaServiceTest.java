package com.home.storage.services.springdatajpa;

import com.home.storage.model.StatusOfItem;
import com.home.storage.repositories.StatusOfItemRepository;
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
class StatusOfItemSDJpaServiceTest {

    @Mock
    StatusOfItemRepository statusOfItemRepository;

    @InjectMocks
    StatusOfItemSDJpaService service;

    StatusOfItem statusOfItem;

    @BeforeEach
    void setUp() {
        statusOfItem = StatusOfItem.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<StatusOfItem> returnStatusOfItem = new HashSet<>();
        returnStatusOfItem.add(StatusOfItem.builder().id(1L).build());
        returnStatusOfItem.add(StatusOfItem.builder().id(2L).build());
        returnStatusOfItem.add(StatusOfItem.builder().id(3L).build());

        when(statusOfItemRepository.findAll()).thenReturn(returnStatusOfItem);

        Set<StatusOfItem> items = service.findAll();
        assertNotNull(items);
        assertEquals(3,items.size());

    }

    @Test
    void findByIdNutNull() {
        when(statusOfItemRepository.findById(anyLong())).thenReturn(Optional.of(statusOfItem));
        StatusOfItem retStatusOfItem = service.findById(1L);
        assertNotNull(retStatusOfItem);
    }
    @Test
    void findByIdNull() {
        when(statusOfItemRepository.findById(anyLong())).thenReturn(Optional.empty());
        StatusOfItem retStatusOfItem = service.findById(2L);
        assertNull(retStatusOfItem);
    }

    @Test
    void save() {
        StatusOfItem statusOfItemToSave = StatusOfItem.builder().id(2L).build();
        when(statusOfItemRepository.save(any())).thenReturn(statusOfItem);
        StatusOfItem savedStatusOfItem = service.save(statusOfItemToSave);
        assertNotNull(savedStatusOfItem);
    }

    @Test
    void delete() {
        service.delete(statusOfItem);
        verify(statusOfItemRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(statusOfItemRepository).deleteById(anyLong());
    }
}