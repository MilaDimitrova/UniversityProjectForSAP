package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
