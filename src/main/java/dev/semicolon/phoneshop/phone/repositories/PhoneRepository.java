package dev.semicolon.phoneshop.phone.repositories;

import dev.semicolon.phoneshop.phone.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
