package com.raj.ams.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.raj.ams.model.Children;
import com.raj.ams.model.Parent;
import com.raj.ams.service.ChildService;
import com.raj.ams.service.ParentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/child")
public class ChildController {

    private ChildService childservice;
    private ParentService parentservice;
    
    public ChildController(ChildService childservice, ParentService parentservice) {
		super();
		this.childservice = childservice;
		this.parentservice = parentservice;
	}


	@PostMapping("/addchild")
    public ResponseEntity<String> submitChild(
            @RequestParam("name") String name,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("weight") Double weight,
            @RequestParam("height") Double height,
            @RequestParam("nutritionalStatus") String nutritionalStatus,
            @RequestParam("deficiency") String deficiency,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("birthCertificate") MultipartFile birthCertificate,
            @RequestParam("parentId") int parentId) throws IOException {

        byte[] photoBytes = null;
        byte[] birthCertificateBytes = null;

        if (photo != null && !photo.isEmpty()) {
            photoBytes = photo.getBytes();
        }
        if (birthCertificate != null && !birthCertificate.isEmpty()) {
            birthCertificateBytes = birthCertificate.getBytes();
        }

        Children child = new Children();
        child.setName(name);
        child.setDateOfBirth(java.sql.Date.valueOf(dateOfBirth));
        child.setGender(gender);
        child.setWeight(weight);
        child.setHeight(height);
        child.setNutritionalStatus(nutritionalStatus);
        child.setDeficiency(deficiency);
        child.setPhoto(photoBytes);
        child.setBirthCertificate(birthCertificateBytes);
        child.setHealthUpdateDate(new java.sql.Date(System.currentTimeMillis()));

        Parent parent = parentservice.getById(parentId);

        child.setParent(parent);
        childservice.addChildren(child);
        return ResponseEntity.ok("Success");
    }

    
    @GetMapping("/all")
    public List<Children> viewAllChild() {
        return childservice.getAllChild();
    }
    
    @GetMapping("/getDef/all")
    public List<Children> viewAllChildDef() {
        return childservice.getAllChildDef();
    }
    
    @GetMapping("/getChild/{id}")
	public Children getChildDetailsById(@PathVariable("id") int id) {
		return childservice.getChildById(id);
	}
    
    @GetMapping("/getChildByParent/{id}")
    public List<Children> getChildDetailsByParentId(@PathVariable("id") int id) {
    	return childservice.getChildByParentId(id);
    }

    @PutMapping("/update/{id}")
	public String updateChild(@RequestBody Children child) {
		String msg = "";
		Long id = child.getChildId();
		System.out.print(id);
		try {
			childservice.updateChild(child);
			msg="Success";
		}
		catch(Exception e) {
			msg="Failure";
		}
		return msg;
	}

}

