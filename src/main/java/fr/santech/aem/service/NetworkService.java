package fr.santech.aem.service;

import fr.santech.aem.service.dto.NetworkDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link fr.santech.aem.domain.Network}.
 */
public interface NetworkService {

    /**
     * Save a network.
     *
     * @param networkDTO the entity to save.
     * @return the persisted entity.
     */
    NetworkDTO save(NetworkDTO networkDTO);

    /**
     * Get all the networks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NetworkDTO> findAll(Pageable pageable);


    /**
     * Get the "id" network.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NetworkDTO> findOne(Long id);

    /**
     * Delete the "id" network.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
