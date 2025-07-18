package com.coffe.platform_api.controller;

import com.coffe.platform_api.dto.address.AddressDTO;
import com.coffe.platform_api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    // Crear una nueva dirección para el usuario autenticado
    @PostMapping
    public AddressDTO createAddress(@RequestBody AddressDTO addressDTO) {
        addressDTO.setUserId(getCurrentUserId());
        return addressService.createAddress(addressDTO);
    }

    //Actualizar una dirección existente
    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        addressDTO.setUserId(getCurrentUserId());
        return addressService.updateAddress(id, addressDTO, getCurrentUserId());
    }

    // Obtener dirección por ID
    @GetMapping("/{id}")
    public AddressDTO getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    // Listar direcciones del usuario autenticado
    @GetMapping("/me")
    public List<AddressDTO> getMyAddresses() {
        UUID userId = getCurrentUserId();
        return addressService.getAddressesByUserId(userId);
    }

    //Eliminar una dirección
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id, getCurrentUserId());
        if (addressService.getAddressById(id) == null) {
            return; 
        }
        throw new IllegalStateException("No se pudo eliminar la dirección");
    }

    // Método para obtener el UUID del usuario autenticado
    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof com.coffe.platform_api.security.user.CustomUserDetails userDetails) {
            return userDetails.getId();
        }
        throw new IllegalStateException("No se pudo obtener el usuario autenticado");
    }
}