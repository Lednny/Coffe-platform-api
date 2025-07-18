package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.address.AddressDTO;
import java.util.List;
import java.util.UUID;

public interface AddressService {

    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO getAddressById(Long id);
    List<AddressDTO> getAddressesByUserId(UUID userId);
    AddressDTO updateAddress(Long id, AddressDTO addressDTO, UUID userId);
    void deleteAddress(Long id, UUID userId);
}
