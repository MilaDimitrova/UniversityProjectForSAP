package repositories;

import entities.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocodeRepository extends JpaRepository<Promocode, Integer> {
}