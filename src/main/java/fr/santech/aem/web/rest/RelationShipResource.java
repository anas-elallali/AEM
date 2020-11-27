package fr.santech.aem.web.rest;

import fr.santech.aem.service.RelationShipService;
import fr.santech.aem.web.rest.errors.BadRequestAlertException;
import fr.santech.aem.service.dto.RelationShipDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.santech.aem.domain.RelationShip}.
 */
@RestController
@RequestMapping("/api")
public class RelationShipResource {

    private final Logger log = LoggerFactory.getLogger(RelationShipResource.class);

    private static final String ENTITY_NAME = "relationShip";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelationShipService relationShipService;

    public RelationShipResource(RelationShipService relationShipService) {
        this.relationShipService = relationShipService;
    }

    /**
     * {@code POST  /relation-ships} : Create a new relationShip.
     *
     * @param relationShipDTO the relationShipDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relationShipDTO, or with status {@code 400 (Bad Request)} if the relationShip has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relation-ships")
    public ResponseEntity<RelationShipDTO> createRelationShip(@Valid @RequestBody RelationShipDTO relationShipDTO) throws URISyntaxException {
        log.debug("REST request to save RelationShip : {}", relationShipDTO);
        if (relationShipDTO.getId() != null) {
            throw new BadRequestAlertException("A new relationShip cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelationShipDTO result = relationShipService.save(relationShipDTO);
        return ResponseEntity.created(new URI("/api/relation-ships/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relation-ships} : Updates an existing relationShip.
     *
     * @param relationShipDTO the relationShipDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relationShipDTO,
     * or with status {@code 400 (Bad Request)} if the relationShipDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relationShipDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relation-ships")
    public ResponseEntity<RelationShipDTO> updateRelationShip(@Valid @RequestBody RelationShipDTO relationShipDTO) throws URISyntaxException {
        log.debug("REST request to update RelationShip : {}", relationShipDTO);
        if (relationShipDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelationShipDTO result = relationShipService.save(relationShipDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relationShipDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /relation-ships} : get all the relationShips.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relationShips in body.
     */
    @GetMapping("/relation-ships")
    public ResponseEntity<List<RelationShipDTO>> getAllRelationShips(Pageable pageable) {
        log.debug("REST request to get a page of RelationShips");
        Page<RelationShipDTO> page = relationShipService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relation-ships/:id} : get the "id" relationShip.
     *
     * @param id the id of the relationShipDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relationShipDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relation-ships/{id}")
    public ResponseEntity<RelationShipDTO> getRelationShip(@PathVariable Long id) {
        log.debug("REST request to get RelationShip : {}", id);
        Optional<RelationShipDTO> relationShipDTO = relationShipService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relationShipDTO);
    }

    /**
     * {@code DELETE  /relation-ships/:id} : delete the "id" relationShip.
     *
     * @param id the id of the relationShipDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relation-ships/{id}")
    public ResponseEntity<Void> deleteRelationShip(@PathVariable Long id) {
        log.debug("REST request to delete RelationShip : {}", id);
        relationShipService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
