package fr.santech.aem.repository;

import fr.santech.aem.domain.RelationShip;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelationShip entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelationShipRepository extends JpaRepository<RelationShip, Long> {
}
