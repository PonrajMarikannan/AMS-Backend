package com.raj.ams.serviceImpl;

import com.raj.ams.controller.ParentController;
import com.raj.ams.model.Parent;
import com.raj.ams.service.AuthService;
import com.raj.ams.service.ParentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ParentControllerTest {

    @InjectMocks
    private ParentController parentController;

    @Mock
    private ParentService parentService;

    @Mock
    private AuthService authService;

    @Mock
    private CustomMail mailService;

    @Mock
    private MultipartFile photo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetParentDetailsById() {
        int id = 1;
        Parent parent = new Parent();
        when(parentService.getById(anyInt())).thenReturn(parent);

        Parent result = parentController.getParentDetailsById(id);

        assertEquals(parent, result);
    }

    @Test
    void testViewAllParent() {
        Parent parent1 = new Parent();
        Parent parent2 = new Parent();
        List<Parent> parents = Arrays.asList(parent1, parent2);
        when(parentService.getAll()).thenReturn(parents);

        List<Parent> result = parentController.viewAllParent();

        assertEquals(parents, result);
    }

}
