package com.raj.ams.model;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Attendance_Child")
public class AttendanceChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    private java.sql.Date date;

    @ManyToOne
    @JoinColumn(name = "childId")
    private Children child;

    private String status;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Staff teacher;

	public AttendanceChild(Long attendanceId, Date date, Children child, String status, Staff teacher) {
		super();
		this.attendanceId = attendanceId;
		this.date = date;
		this.child = child;
		this.status = status;
		this.teacher = teacher;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Children getChild() {
		return child;
	}

	public void setChild(Children child) {
		this.child = child;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Staff getTeacher() {
		return teacher;
	}

	public void setTeacher(Staff teacher) {
		this.teacher = teacher;
	}

	public AttendanceChild() {
		super();
	}
    
    

}


