package com.home.storage.controllers;

import com.home.storage.model.Brand;
import com.home.storage.services.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {

    @Mock
    BrandService brandService;

    @InjectMocks
    BrandController brandController;

    Set<Brand> brands;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        brands = new HashSet<>();
        brands.add(Brand.builder().id(1L).build());
        brands.add(Brand.builder().id(2L).build());
        brands.add(Brand.builder().id(3L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
    }

    @Test
    void displayBrands() throws Exception {
        when(brandService.findAll()).thenReturn(brands);

        mockMvc.perform(get("/brands/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/brands/listOfBrands"))
                .andExpect(model().attribute("brands",hasSize(3)));
    }

    @Test
    void addBrand() throws Exception{
        mockMvc.perform(get("/brands/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("brands/createOrUpdateBrand"))
                .andExpect(model().attributeExists("brand"));
        verifyNoInteractions(brandService);
    }

    @Test
    void createBrand() throws Exception{
        when(brandService.save(ArgumentMatchers.any())).thenReturn(Brand.builder().id(1l).build());

        mockMvc.perform(post("/brands/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands/list"));

        verify(brandService).save(ArgumentMatchers.any());
    }
}
