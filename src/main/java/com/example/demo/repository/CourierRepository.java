package com.example.demo.repository;

import com.example.demo.entity.CourierInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<CourierInfo,Long> {
}
