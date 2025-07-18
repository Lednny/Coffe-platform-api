package com.coffe.platform_api.repository;

import com.coffe.platform_api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(UUID userId);
}
