package fr.santech.aem.web.rest;

import fr.santech.aem.service.NetworkMemberService;
import fr.santech.aem.web.rest.errors.BadRequestAlertException;
import fr.santech.aem.service.dto.NetworkMemberDTO;

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
 * REST controller for managing {@link fr.santech.aem.domain.NetworkMember}.
 */
@RestController
@RequestMapping("/api")
public class NetworkMemberResource {

    private final Logger log = LoggerFactory.getLogger(NetworkMemberResource.class);

    private static final String ENTITY_NAME = "networkMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NetworkMemberService networkMemberService;

    public NetworkMemberResource(NetworkMemberService networkMemberService) {
        this.networkMemberService = networkMemberService;
    }

    /**
     * {@code POST  /network-members} : Create a new networkMember.
     *
     * @param networkMemberDTO the networkMemberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new networkMemberDTO, or with status {@code 400 (Bad Request)} if the networkMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/network-members")
    public ResponseEntity<NetworkMemberDTO> createNetworkMember(@Valid @RequestBody NetworkMemberDTO networkMemberDTO) throws URISyntaxException {
        log.debug("REST request to save NetworkMember : {}", networkMemberDTO);
        if (networkMemberDTO.getId() != null) {
            throw new BadRequestAlertException("A new networkMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NetworkMemberDTO result = networkMemberService.save(networkMemberDTO);
        return ResponseEntity.created(new URI("/api/network-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /network-members} : Updates an existing networkMember.
     *
     * @param networkMemberDTO the networkMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated networkMemberDTO,
     * or with status {@code 400 (Bad Request)} if the networkMemberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the networkMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/network-members")
    public ResponseEntity<NetworkMemberDTO> updateNetworkMember(@Valid @RequestBody NetworkMemberDTO networkMemberDTO) throws URISyntaxException {
        log.debug("REST request to update NetworkMember : {}", networkMemberDTO);
        if (networkMemberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NetworkMemberDTO result = networkMemberService.save(networkMemberDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, networkMemberDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /network-members} : get all the networkMembers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of networkMembers in body.
     */
    @GetMapping("/network-members")
    public ResponseEntity<List<NetworkMemberDTO>> getAllNetworkMembers(Pageable pageable) {
        log.debug("REST request to get a page of NetworkMembers");
        Page<NetworkMemberDTO> page = networkMemberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /network-members/:id} : get the "id" networkMember.
     *
     * @param id the id of the networkMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the networkMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/network-members/{id}")
    public ResponseEntity<NetworkMemberDTO> getNetworkMember(@PathVariable Long id) {
        log.debug("REST request to get NetworkMember : {}", id);
        Optional<NetworkMemberDTO> networkMemberDTO = networkMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(networkMemberDTO);
    }

    /**
     * {@code DELETE  /network-members/:id} : delete the "id" networkMember.
     *
     * @param id the id of the networkMemberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/network-members/{id}")
    public ResponseEntity<Void> deleteNetworkMember(@PathVariable Long id) {
        log.debug("REST request to delete NetworkMember : {}", id);
        networkMemberService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
