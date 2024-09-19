package com.pocimp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pocimp.entity.Company;
import com.pocimp.repository.CompanyRepository;
import com.pocimp.service.CompanyService;

@SpringBootTest
public class CompanyTest {
	
	@Autowired
	private CompanyService companyService;
	
	@MockBean
	private CompanyRepository companyRepository;
	
	
	 @Test
	    public void getCompaniesTest() {
	        // Mocking the behavior of companyRepository.findAll
	        when(companyRepository.findAll()).thenReturn(Stream
	                .of(new Company(1, "dell", "Dell laptops are available here", new Date(), "Lavanya", "Lavanya", new Date()),
	                	new Company(2, "samsung", "samsung product are availible here", new Date(), "Lavanya", "Lavanya", new Date()),
	                	new Company(3, "American Signature"," American Signature product are availible here", new Date(), "Lavanya", "Lavanya", new Date()),
	                	new Company(4, "Prima Plastics Ltd."," Prima Plastics Ltd. product are availible here ", new Date(), "Lavanya", "Lavanya", new Date()),
	                	new Company(5, "dnmx","dnmx product are availible here ", new Date(), "Lavanya", "Lavanya", new Date()))
	                	
	                .collect(Collectors.toList()));

	        // Calling the getall method from the service
	        assertEquals(5, companyService.getall().size());
	    }
	 @Test
	 public void getCompanyByNameTest() {
	     String name = "dnmx";
	     Company mockCompany = new Company(1, name, "DNMX products are available here", new Date(), "Lavanya", "Lavanya", new Date());
	     when(companyRepository.findByName(name)).thenReturn(mockCompany);

	     // Get the result from the service
	     Company result = companyService.getCompanyByName(name);

	     // Assert that the result is not null
	     assertNotNull(result);

	     // Add more assertions as needed based on your requirements
	     assertEquals(name, result.getName());
	 }
	 
	 @Test
	    public void createcompanyTest() {
	        // Creating a new ProductCategory object
	        Company company = new Company(6, "firebolt", "category for firebolt", new Date(), "Lavanya", "Lavanya", new Date());

	        // Mocking the behavior of the categoryRepository.save() method
	        when(companyRepository.save(company)).thenReturn(company);

	        // Calling the createProductCategory method from the service
	        Company result =companyService.createCompany(company);

	        // Asserting that the returned result matches the expected productCategory
	        assertEquals(company, result);
	    }
	 
	 
	 @Test
	    public void deletecompanyTest() {
	        // Given
	        int companyIdToDelete = 6;
	        Company company = new Company(companyIdToDelete, "firebolt", "category for firebolt", new Date(), "Lavanya", "Lavanya", new Date());

	        // Mocking the behavior of categoryRepository.findById
	        when(companyRepository.findById(companyIdToDelete)).thenReturn(Optional.of(company));

	        // When
	        companyService.deleteCompany(companyIdToDelete);

	        // Then
	        // Verify that the delete method of categoryRepository is called with the correct argument
	        verify(companyRepository, times(1)).delete(company);
	    }
	 
	 @Test
	    public void updateCompanyTest() {
	        int companyId = 1;

	        // Mocking the behavior of companyRepository.findById
	        Company existingCompany = new Company(companyId, "puma", "category for puma",
	                new Date(), "Lavanya", "Lavanya", new Date());
	        when(companyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));

	        // Mocking the behavior of companyRepository.save
	        Company updatedCompany = new Company(companyId, "puma", "category for puma",
	                new Date(), "Lavanya", "lavanya", new Date());
	        when(companyRepository.save(existingCompany)).thenReturn(updatedCompany);

	        // Call the updateCompany method from the service
	        Company requestCompany = new Company(companyId, "puma", "category for puma",
	                new Date(), "Lavanya", "Lavanya", new Date());
	        Company result = companyService.updateCompany(companyId, requestCompany);

	        // Assert that the result is not null
	        assertNotNull(result);

	        // Add more assertions based on your requirements
	        assertEquals(companyId, result.getId());
	       // assertEquals("puma", result.getName());
	       // assertEquals("Updated description", result.getDescription());
	    //    assertEquals("Lavanya", result.getModifiedby());

	        // Verify that the companyRepository.findById and companyRepository.save methods were called
	        verify(companyRepository, times(1)).findById(companyId);
	        verify(companyRepository, times(1)).save(existingCompany);
	    }
	}

