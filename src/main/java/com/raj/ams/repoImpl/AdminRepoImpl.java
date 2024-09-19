package com.raj.ams.repoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raj.ams.model.Admin;
import com.raj.ams.model.Parent;
import com.raj.ams.model.Staff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminRepoImpl {
	
	@Autowired
	EntityManager eManager;
	
	public Staff findTeacherEmail(String email) {
        String hql = "FROM Staff WHERE email = :email";
        TypedQuery<Staff> query = eManager.createQuery(hql, Staff.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
	}
	
	public Admin findAdminEmail(String email) {
		String hql = "FROM Admin WHERE email = :email";
		TypedQuery<Admin> query = eManager.createQuery(hql, Admin.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}
	
	public Parent findParentEmail(String email) {
		String hql = "FROM Parent WHERE email = :email";
		TypedQuery<Parent> query = eManager.createQuery(hql, Parent.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}
	
	public Object ChangePassword(Staff staff) {
		return eManager.merge(staff);
	}
	
	public Staff getById(int id) {
		return eManager.find(Staff.class, id);
	}

	public void ChangePassword(Parent p) {
		eManager.merge(p);
	}
	
}
