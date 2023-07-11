package com.example.demo.repository;

import com.example.demo.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.apartment.id = :id")
    List<Reservation> findAllByApartamentId(Long id);

    @Query("SELECT r FROM Reservation r WHERE r.tenant.name = :tenantName")
    List<Reservation> findAllByTenantName(String tenantName);
}
