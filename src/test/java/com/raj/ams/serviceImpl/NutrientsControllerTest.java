package com.raj.ams.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.raj.ams.controller.NutrientsController;
import com.raj.ams.model.Nutrient;
import com.raj.ams.service.NutrientService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NutrientsControllerTest {

    @InjectMocks
    private NutrientsController nutrientsController;

    @Mock
    private NutrientService nutrientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNutrientDetailsByDef_Success() {
        String deficiency = "Vitamin C";
        Nutrient nutrient = new Nutrient();
        nutrient.setDeficiency(deficiency);
        when(nutrientService.getNutrientById(deficiency)).thenReturn(nutrient);
        Nutrient result = nutrientsController.getNutrientDetailsByDef(deficiency);
        assertEquals(nutrient, result);
    }

    @Test
    void testGetNutrientDetailsByDef_NotFound() {
        
        String deficiency = "Vitamin X";
        when(nutrientService.getNutrientById(deficiency)).thenReturn(null);
        Nutrient result = nutrientsController.getNutrientDetailsByDef(deficiency);
        assertEquals(null, result);
        
    }
}
