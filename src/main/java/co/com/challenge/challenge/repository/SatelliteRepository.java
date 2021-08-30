package co.com.challenge.challenge.repository;

import co.com.challenge.challenge.entity.SatelliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteEntity,String> {
}
