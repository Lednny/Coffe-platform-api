package com.coffe.platform_api.dto.address;


import com.coffe.platform_api.entity.Address;

public class AddressMapper {

    public static AddressDTO toDTO(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setUserId(address.getUser().getId());
        dto.setName(address.getName());
        dto.setLastName(address.getLastName());
        dto.setPhoneNumber(address.getPhoneNumber());
        dto.setEmail(address.getEmail());
        dto.setCountry(address.getCountry());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());
        return dto;
    }
}
