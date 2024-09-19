package com.raj.ams.serviceImpl;
import com.raj.ams.controller.StaffController;
import com.raj.ams.model.Staff;
import com.raj.ams.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StaffServiceImplTest {

    @InjectMocks
    private StaffController staffController;

    @Mock
    private StaffService staffService;

    @Mock
    private CustomMail mailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStaff_Success() {
        Staff staff = new Staff();
        staff.setPassword("password");
        String response = staffController.addStaff(staff);
        assertEquals("Success", response);
        verify(staffService, times(1)).addStaff(any(Staff.class));
    }

    @Test
    void testAddStaff_Failure() {
        Staff staff = new Staff();
        staff.setPassword("password");
        doThrow(new RuntimeException()).when(staffService).addStaff(any(Staff.class));
        String response = staffController.addStaff(staff);
        assertEquals("Failure", response);
    }

    @Test
    void testViewAllStaff() {
        Staff staff1 = new Staff();
        Staff staff2 = new Staff();
        List<Staff> staffList = Arrays.asList(staff1, staff2);
        when(staffService.getAllStaff()).thenReturn(staffList);
        List<Staff> result = staffController.viewAllStaff();
        assertEquals(staffList, result);
    }

    @Test
    void testGetStaffDetailsById() {
        int id = 1;
        Staff staff = new Staff();
        when(staffService.getStaffById(id)).thenReturn(staff);
        Staff result = staffController.getStaffDetailsById(id);
        assertEquals(staff, result);
    }

}
