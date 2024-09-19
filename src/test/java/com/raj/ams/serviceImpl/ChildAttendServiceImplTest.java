package com.raj.ams.serviceImpl;

import com.raj.ams.controller.ChildAttendController;
import com.raj.ams.model.Children;
import com.raj.ams.model.Staff;
import com.raj.ams.service.ChildAttendService;
import com.raj.ams.service.ChildService;
import com.raj.ams.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChildAttendServiceImplTest {

    @InjectMocks
    private ChildAttendController childAttendController;

    @Mock
    private ChildAttendService childAttendService;

    @Mock
    private ChildService childService;

    @Mock
    private StaffService staffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("deprecation")
	@Test
    void testAttendChild() throws Exception {
        int staffId = 1;
        int childId = 1;
        String status = "Present";

        Staff staff = new Staff();
        Children child = new Children();

        when(staffService.getStaffById(staffId)).thenReturn(staff);
        when(childService.getChildById(childId)).thenReturn(child);

        ResponseEntity<String> response = childAttendController.attendChild(status, staffId, childId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Success", response.getBody());
    }
}
