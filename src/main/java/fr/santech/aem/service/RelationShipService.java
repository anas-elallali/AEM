package fr.santech.aem.service;

import fr.santech.aem.service.dto.RelationShipDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link fr.santech.aem.domain.RelationShip}.
 */
public interface RelationShipService {

    /**
     * Save a relationShip.
     *
     * @param relationShipDTO the entity to save.
     * @return the persisted entity.
     */
    RelationShipDTO save(RelationShipDTO relationShipDTO);

    /**
     * Get all the relationShips.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelationShipDTO> findAll(Pageable pageable);


    /**
     * Get the "id" relationShip.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelationShipDTO> findOne(Long id);

    /**
     * Delete the "id" relationShip.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
