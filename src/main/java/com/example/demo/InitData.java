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
        var tenant1 = Tenant.builder()
                .name("Tenant1").build();
        var tenant2 = Tenant.builder()
                .name("Tenant2").build();

        var owner1 = Owner.builder()
                .name("Owner1" + RandomUtils.nextInt()).build();

        var owner2 = Owner.builder()
                .name("Owner2").build();

        var apartament1 = Apartment.builder()
                .name("Apartment1")
                .area(50.0)
                .overnightFee(100.0)
                .owner(owner1)
                .description("The best apartment.")
                .build();
        LocalDate today = LocalDate.now();
        var reservation1 = Reservation.builder()
                .startDate(today)
                .stopDate(today.plusDays(10))
                .apartment(apartament1)
                .tenant(tenant1)
                .build();
        reservation1.countPrice();
        var reservation2 = Reservation.builder()
                .startDate(today.plusDays(11))
                .stopDate(today.plusDays(15))
                .apartment(apartament1)
                .tenant(tenant2)
                .build();
        reservation2.countPrice();


        ownerRepository.save(owner1);
        ownerRepository.save(owner2);
        tenantRepository.save(tenant1);
        tenantRepository.save(tenant2);
        apartmentRepository.save(apartament1);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
    }
}
