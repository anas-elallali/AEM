package fr.santech.aem.service;

import fr.santech.aem.service.dto.NetworkMemberDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link fr.santech.aem.domain.NetworkMember}.
 */
public interface NetworkMemberService {

    /**
     * Save a networkMember.
     *
     * @param networkMemberDTO the entity to save.
     * @return the persisted entity.
     */
    NetworkMemberDTO save(NetworkMemberDTO networkMemberDTO);

    /**
     * Get all the networkMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NetworkMemberDTO> findAll(Pageable pageable);


    /**
     * Get the "id" networkMember.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NetworkMemberDTO> findOne(Long id);

    /**
     * Delete the "id" networkMember.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
