package com.telefast.sfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.Service;

@Repository
public interface OrderedServiceRepository extends JpaRepository<OrderedService, Integer> {

	@Query("from OrderedService o where o.employee.employeeId=?1")
	List<OrderedService> findAllOrderedServicesByServiceManagerId(int serviceManagerId);
	
	@Query("from OrderedService o where o.project.projectId IN :projectIds")
	List<OrderedService> findAllOrderedServicesByProjectIds(List<Integer> projectIds);
	
	
//	List<OrderedService> findAllOrderedServicesByProjectManagerId(int projectManagerId);
//
//	@Query("select o.service from OrderedService o where o.project.projectId=?1")
//	List<Service> findServicesByProjectId(int projectId);
	@Query("select o.service from OrderedService o where o.employee.employeeId=?1")
	List<Service> findAllByManagerId(int managerId);

	@Query("select o.service from OrderedService o where o.project.projectId=?1")
	List<Service> findServicesByProjectId(int projectId);

	@Query("from OrderedService o where o.service.serviceId=?1")
	OrderedService findByServiceId(int serviceId);

}
