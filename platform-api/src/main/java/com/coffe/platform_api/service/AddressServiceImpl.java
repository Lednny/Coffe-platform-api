package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.address.AddressDTO;
import com.coffe.platform_api.dto.address.AddressMapper;
import com.coffe.platform_api.entity.Address;
import com.coffe.platform_api.entity.User;
import com.coffe.platform_api.repository.AddressRepository;
import com.coffe.platform_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Optional<User> userOpt = userRepository.findById(addressDTO.getUserId());
        if (userOpt.isEmpty()) throw new IllegalArgumentException("User not found");

        Address address = new Address();
        address.setUser(userOpt.get());
        address.setName(addressDTO.getName());
        address.setLastName(addressDTO.getLastName());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setEmail(addressDTO.getEmail());
        address.setCountry(addressDTO.getCountry());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());

        Address saved = addressRepository.save(address);
        return AddressMapper.toDTO(saved);
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(AddressMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<AddressDTO> getAddressesByUserId(UUID userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO, UUID userId) {
        Address address = addressRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException("Dirección no encontrada."));
        if (!address.getUser().getId().equals(userId)) {
            throw new SecurityException("No tienes permisos par amodificar esta direccíón.");
        }
        address.setName(addressDTO.getName());
        address.setLastName(addressDTO.getLastName());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setEmail(addressDTO.getEmail());
        address.setCountry(addressDTO.getCountry());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        Address updated = addressRepository.save(address);
        return AddressMapper.toDTO(updated);
    }

    @Override
    public void deleteAddress(Long id, UUID userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dirección no encontrada."));
        if (!address.getUser().getId().equals(userId)) {
            throw new SecurityException("No tienes permisos para eliminar esta dirección.");
        }
        addressRepository.deleteById(id);
    }
}