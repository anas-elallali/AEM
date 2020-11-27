package fr.santech.aem.service;

import fr.santech.aem.service.dto.RolesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link fr.santech.aem.domain.Roles}.
 */
public interface RolesService {

    /**
     * Save a roles.
     *
     * @param rolesDTO the entity to save.
     * @return the persisted entity.
     */
    RolesDTO save(RolesDTO rolesDTO);

    /**
     * Get all the roles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RolesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" roles.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RolesDTO> findOne(Long id);

    /**
     * Delete the "id" roles.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
