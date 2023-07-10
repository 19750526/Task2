package com.example.demo;

import com.example.demo.domain.Apartment;
import com.example.demo.domain.Owner;
import com.example.demo.domain.Reservation;
import com.example.demo.domain.Tenant;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.TenantRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InitData {
    private final ApartmentRepository apartmentRepository;
    private final OwnerRepository ownerRepository;
    private final ReservationRepository reservationRepository;
    private final TenantRepository tenantRepository;

    @PostConstruct
    void init() {
        var tenant1 = new Tenant().builder()
                .name("Tenant" + RandomUtils.nextInt()).build();
        var tenant2 = new Tenant().builder()
                .name("Tenant" + RandomUtils.nextInt()).build();

        var owner1 = new Owner().builder()
                .name("Owner" + RandomUtils.nextInt()).build();

        var owner2 = new Owner().builder()
                .name("Owner" + RandomUtils.nextInt()).build();

        var apartament1 = new Apartment().builder()
                .name("Apartment" + RandomUtils.nextInt())
                .area(50.0)
                .unitPrice(100.0)
                .owner(owner1)
                .description("The best apartment.")
                .build();
        LocalDate today = LocalDate.now();
        var reservation1 = new Reservation().builder()
                .startDate(today)
                .stopDate(today.plusDays(10))
                .apartment(apartament1)
                .tenant(tenant1)
                .build();
        ownerRepository.save(owner1);
        ownerRepository.save(owner2);
        tenantRepository.save(tenant1);
        tenantRepository.save(tenant2);
        apartmentRepository.save(apartament1);
        reservationRepository.save(reservation1);
    }
}
