package com.example.garbandgo.service;

import com.example.garbandgo.entities.Address;
import com.example.garbandgo.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AddressService{
        private final AddressRepository addressRepository;

        public AddressService(AddressRepository addressRepository) {
            this.addressRepository = addressRepository;
        }

        public List<Address> getAddressRepository() {
            return addressRepository.findAll();
        }

        public void saveAddress(Address address) {
            addressRepository.save(address);
        }
    }