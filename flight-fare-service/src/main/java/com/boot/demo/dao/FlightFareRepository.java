package com.boot.demo.dao;

import com.boot.demo.model.FlightFare;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author JayendraB
 * Created on 08/07/21
 */
public interface FlightFareRepository extends JpaRepository<FlightFare, Long> {
}
