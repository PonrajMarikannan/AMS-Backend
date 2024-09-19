package com.raj.ams.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.raj.ams.controller.LeaveController;
import com.raj.ams.model.LeaveRequest;
import com.raj.ams.model.Staff;
import com.raj.ams.service.LeaveService;
import com.raj.ams.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LeaveServiceImplTest {

    @InjectMocks
    private LeaveController leaveController;

    @Mock
    private LeaveService leaveService;

    @Mock
    private StaffService staffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRequest_Success() throws Exception {

    	String leaveType = "Sick Leave";
        String startDate = "2024-09-01";
        String endDate = "2024-09-10";
        String reason = "Illness";
        int staffId = 1;

        Staff staff = new Staff();
        LeaveRequest leaveRequest = new LeaveRequest();
        when(staffService.getStaffById(staffId)).thenReturn(staff);

        String response = leaveController.addStaff(leaveType, startDate, endDate, reason, staffId);

        assertEquals("Success", response);
        verify(leaveService, times(1)).addRequest(any(LeaveRequest.class));
    }

    @Test
    void testAddRequest_Failure() throws Exception {

    	String leaveType = "Sick Leave";
        String startDate = "2024-09-01";
        String endDate = "2024-09-10";
        String reason = "Illness";
        int staffId = 1;

        when(staffService.getStaffById(staffId)).thenThrow(new RuntimeException("Error"));

        String response = leaveController.addStaff(leaveType, startDate, endDate, reason, staffId);

        assertEquals("Failure", response);
    }

    @Test
    void testViewAllRequest() {
        LeaveRequest leaveRequest = new LeaveRequest();
        List<LeaveRequest> leaveRequests = Collections.singletonList(leaveRequest);
        when(leaveService.getAllRequest()).thenReturn(leaveRequests);

        List<LeaveRequest> result = leaveController.viewAllRequest();

        assertEquals(leaveRequests, result);
    }

    @Test
    void testGetRequestDetailsById() {
        int requestId = 1;
        LeaveRequest leaveRequest = new LeaveRequest();
        when(leaveService.getRequestById(requestId)).thenReturn(leaveRequest);

        LeaveRequest result = leaveController.getRequestDetailsById(requestId);

        assertEquals(leaveRequest, result);
    }

   }

	