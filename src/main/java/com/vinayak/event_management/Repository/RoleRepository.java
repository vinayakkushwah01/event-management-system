package com.vinayak.event_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinayak.event_management.entity.Role;



@Repository
public interface RoleRepository  extends JpaRepository <Role , Long>{
   Role findByName(String name);
}
