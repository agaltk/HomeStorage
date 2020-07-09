package com.home.storage.controllers;

import com.home.storage.model.Brand;
import com.home.storage.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/brands")
@Controller
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/list")
    public String listOfBrands(Model model)
    {
        model.addAttribute("brands", brandService.findAll());
        return "/brands/listOfBrands";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model)
    {
        model.addAttribute("brand", Brand.builder().build());
        return "brands/createOrUpdateBrand";
    }
    @PostMapping("/new")
    public String processCreationForm(Brand brand, BindingResult result){
        if(result.hasErrors())
            return "brands/createOrUpdateBrand";
        else {
            Brand savedBranch = this.brandService.save(brand);
            return "redirect:/brands/list";
        }
    }
}
