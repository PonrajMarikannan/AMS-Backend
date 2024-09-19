package com.raj.ams.serviceImpl;
import com.raj.ams.controller.ChildController;
import com.raj.ams.model.Children;
import com.raj.ams.model.Parent;
import com.raj.ams.service.ChildService;
import com.raj.ams.repoImpl.ParentRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ChildServiceImplTest {

    @InjectMocks
    private ChildController childController;

    @Mock
    private ChildService childService;

    @Mock
    private ParentRepoImpl repoImpl;

    @Mock
    private MultipartFile photo;

    @Mock
    private MultipartFile birthCertificate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("deprecation")
	@Test
    void testSubmitChild() throws IOException {
        String name = "John";
        String dateOfBirth = "2020-01-01";
        String gender = "Male";
        Double weight = 20.0;
        Double height = 100.0;
        String nutritionalStatus = "Good";
        String deficiency = "None";
        int parentId = 1;

        byte[] photoBytes = new byte[0];
        byte[] birthCertificateBytes = new byte[0];

        when(photo.getBytes()).thenReturn(photoBytes);
        when(birthCertificate.getBytes()).thenReturn(birthCertificateBytes);

        Parent parent = new Parent();
        when(repoImpl.findParent(parentId)).thenReturn(parent);

        ResponseEntity<String> response = childController.submitChild(
                name, dateOfBirth, gender, weight, height, nutritionalStatus, deficiency, photo, birthCertificate, parentId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Success", response.getBody());

        verify(childService, times(1)).addChildren(any(Children.class));
    }

    @Test
    void testViewAllChild() {
        List<Children> childrenList = Collections.singletonList(new Children());
        when(childService.getAllChild()).thenReturn(childrenList);

        List<Children> result = childController.viewAllChild();

        assertEquals(childrenList, result);
    }

    @Test
    void testGetChildDetailsById() {
        int childId = 1;
        Children child = new Children();
        when(childService.getChildById(childId)).thenReturn(child);

        Children result = childController.getChildDetailsById(childId);

        assertEquals(child, result);
    }

}
