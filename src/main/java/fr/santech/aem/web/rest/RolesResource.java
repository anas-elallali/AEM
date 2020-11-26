package fr.santech.aem.web.rest;

import fr.santech.aem.service.RolesService;
import fr.santech.aem.web.rest.errors.BadRequestAlertException;
import fr.santech.aem.service.dto.RolesDTO;

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
 * REST controller for managing {@link fr.santech.aem.domain.Roles}.
 */
@RestController
@RequestMapping("/api")
public class RolesResource {

    private final Logger log = LoggerFactory.getLogger(RolesResource.class);

    private static final String ENTITY_NAME = "roles";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RolesService rolesService;

    public RolesResource(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    /**
     * {@code POST  /roles} : Create a new roles.
     *
     * @param rolesDTO the rolesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rolesDTO, or with status {@code 400 (Bad Request)} if the roles has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/roles")
    public ResponseEntity<RolesDTO> createRoles(@Valid @RequestBody RolesDTO rolesDTO) throws URISyntaxException {
        log.debug("REST request to save Roles : {}", rolesDTO);
        if (rolesDTO.getId() != null) {
            throw new BadRequestAlertException("A new roles cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RolesDTO result = rolesService.save(rolesDTO);
        return ResponseEntity.created(new URI("/api/roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /roles} : Updates an existing roles.
     *
     * @param rolesDTO the rolesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rolesDTO,
     * or with status {@code 400 (Bad Request)} if the rolesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rolesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/roles")
    public ResponseEntity<RolesDTO> updateRoles(@Valid @RequestBody RolesDTO rolesDTO) throws URISyntaxException {
        log.debug("REST request to update Roles : {}", rolesDTO);
        if (rolesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RolesDTO result = rolesService.save(rolesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rolesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /roles} : get all the roles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roles in body.
     */
    @GetMapping("/roles")
    public ResponseEntity<List<RolesDTO>> getAllRoles(Pageable pageable) {
        log.debug("REST request to get a page of Roles");
        Page<RolesDTO> page = rolesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /roles/:id} : get the "id" roles.
     *
     * @param id the id of the rolesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rolesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/roles/{id}")
    public ResponseEntity<RolesDTO> getRoles(@PathVariable Long id) {
        log.debug("REST request to get Roles : {}", id);
        Optional<RolesDTO> rolesDTO = rolesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rolesDTO);
    }

    /**
     * {@code DELETE  /roles/:id} : delete the "id" roles.
     *
     * @param id the id of the rolesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRoles(@PathVariable Long id) {
        log.debug("REST request to delete Roles : {}", id);
        rolesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
