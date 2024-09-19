package com.pocimp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocimp.entity.Company;
import com.pocimp.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/create")
	    public Company createCompany(@RequestBody Company company) {
	        return companyService.createCompany(company);
	    }
	 @PutMapping("update/{id}")
	    public Company updateCompany(@PathVariable int id, @RequestBody Company company) {
	        return companyService.updateCompany(id, company);
	    }
	 @DeleteMapping("delete/{id}")
	    public void deleteCompany(@PathVariable int id) {
	        companyService.deleteCompany(id);
	    }
	 @GetMapping("/getall")
		public List<Company> getall(){
			return  companyService.getall();
		}
	 @GetMapping("/get/{name}")
	 public Company getCompanyByName(@PathVariable String name) {
	         return companyService.getCompanyByName(name);
	     }
}
