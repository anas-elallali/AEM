package fr.santech.aem.web.rest;

import fr.santech.aem.AemApp;
import fr.santech.aem.domain.RelationShip;
import fr.santech.aem.domain.NetworkMember;
import fr.santech.aem.repository.RelationShipRepository;
import fr.santech.aem.service.RelationShipService;
import fr.santech.aem.service.dto.RelationShipDTO;
import fr.santech.aem.service.mapper.RelationShipMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RelationShipResource} REST controller.
 */
@SpringBootTest(classes = AemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RelationShipResourceIT {

    private static final String DEFAULT_RELATION_SHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_SHIP = "BBBBBBBBBB";

    @Autowired
    private RelationShipRepository relationShipRepository;

    @Autowired
    private RelationShipMapper relationShipMapper;

    @Autowired
    private RelationShipService relationShipService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelationShipMockMvc;

    private RelationShip relationShip;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelationShip createEntity(EntityManager em) {
        RelationShip relationShip = new RelationShip()
            .relationShip(DEFAULT_RELATION_SHIP);
        // Add required entity
        NetworkMember networkMember;
        if (TestUtil.findAll(em, NetworkMember.class).isEmpty()) {
            networkMember = NetworkMemberResourceIT.createEntity(em);
            em.persist(networkMember);
            em.flush();
        } else {
            networkMember = TestUtil.findAll(em, NetworkMember.class).get(0);
        }
        relationShip.setNetworkMember(networkMember);
        return relationShip;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelationShip createUpdatedEntity(EntityManager em) {
        RelationShip relationShip = new RelationShip()
            .relationShip(UPDATED_RELATION_SHIP);
        // Add required entity
        NetworkMember networkMember;
        if (TestUtil.findAll(em, NetworkMember.class).isEmpty()) {
            networkMember = NetworkMemberResourceIT.createUpdatedEntity(em);
            em.persist(networkMember);
            em.flush();
        } else {
            networkMember = TestUtil.findAll(em, NetworkMember.class).get(0);
        }
        relationShip.setNetworkMember(networkMember);
        return relationShip;
    }

    @BeforeEach
    public void initTest() {
        relationShip = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelationShip() throws Exception {
        int databaseSizeBeforeCreate = relationShipRepository.findAll().size();
        // Create the RelationShip
        RelationShipDTO relationShipDTO = relationShipMapper.toDto(relationShip);
        restRelationShipMockMvc.perform(post("/api/relation-ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relationShipDTO)))
            .andExpect(status().isCreated());

        // Validate the RelationShip in the database
        List<RelationShip> relationShipList = relationShipRepository.findAll();
        assertThat(relationShipList).hasSize(databaseSizeBeforeCreate + 1);
        RelationShip testRelationShip = relationShipList.get(relationShipList.size() - 1);
        assertThat(testRelationShip.getRelationShip()).isEqualTo(DEFAULT_RELATION_SHIP);
    }

    @Test
    @Transactional
    public void createRelationShipWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relationShipRepository.findAll().size();

        // Create the RelationShip with an existing ID
        relationShip.setId(1L);
        RelationShipDTO relationShipDTO = relationShipMapper.toDto(relationShip);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelationShipMockMvc.perform(post("/api/relation-ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relationShipDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelationShip in the database
        List<RelationShip> relationShipList = relationShipRepository.findAll();
        assertThat(relationShipList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRelationShipIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationShipRepository.findAll().size();
        // set the field null
        relationShip.setRelationShip(null);

        // Create the RelationShip, which fails.
        RelationShipDTO relationShipDTO = relationShipMapper.toDto(relationShip);


        restRelationShipMockMvc.perform(post("/api/relation-ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relationShipDTO)))
            .andExpect(status().isBadRequest());

        List<RelationShip> relationShipList = relationShipRepository.findAll();
        assertThat(relationShipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRelationShips() throws Exception {
        // Initialize the database
        relationShipRepository.saveAndFlush(relationShip);

        // Get all the relationShipList
        restRelationShipMockMvc.perform(get("/api/relation-ships?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relationShip.getId().intValue())))
            .andExpect(jsonPath("$.[*].relationShip").value(hasItem(DEFAULT_RELATION_SHIP)));
    }
    
    @Test
    @Transactional
    public void getRelationShip() throws Exception {
        // Initialize the database
        relationShipRepository.saveAndFlush(relationShip);

        // Get the relationShip
        restRelationShipMockMvc.perform(get("/api/relation-ships/{id}", relationShip.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relationShip.getId().intValue()))
            .andExpect(jsonPath("$.relationShip").value(DEFAULT_RELATION_SHIP));
    }
    @Test
    @Transactional
    public void getNonExistingRelationShip() throws Exception {
        // Get the relationShip
        restRelationShipMockMvc.perform(get("/api/relation-ships/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelationShip() throws Exception {
        // Initialize the database
        relationShipRepository.saveAndFlush(relationShip);

        int databaseSizeBeforeUpdate = relationShipRepository.findAll().size();

        // Update the relationShip
        RelationShip updatedRelationShip = relationShipRepository.findById(relationShip.getId()).get();
        // Disconnect from session so that the updates on updatedRelationShip are not directly saved in db
        em.detach(updatedRelationShip);
        updatedRelationShip
            .relationShip(UPDATED_RELATION_SHIP);
        RelationShipDTO relationShipDTO = relationShipMapper.toDto(updatedRelationShip);

        restRelationShipMockMvc.perform(put("/api/relation-ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relationShipDTO)))
            .andExpect(status().isOk());

        // Validate the RelationShip in the database
        List<RelationShip> relationShipList = relationShipRepository.findAll();
        assertThat(relationShipList).hasSize(databaseSizeBeforeUpdate);
        RelationShip testRelationShip = relationShipList.get(relationShipList.size() - 1);
        assertThat(testRelationShip.getRelationShip()).isEqualTo(UPDATED_RELATION_SHIP);
    }

    @Test
    @Transactional
    public void updateNonExistingRelationShip() throws Exception {
        int databaseSizeBeforeUpdate = relationShipRepository.findAll().size();

        // Create the RelationShip
        RelationShipDTO relationShipDTO = relationShipMapper.toDto(relationShip);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelationShipMockMvc.perform(put("/api/relation-ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relationShipDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelationShip in the database
        List<RelationShip> relationShipList = relationShipRepository.findAll();
        assertThat(relationShipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelationShip() throws Exception {
        // Initialize the database
        relationShipRepository.saveAndFlush(relationShip);

        int databaseSizeBeforeDelete = relationShipRepository.findAll().size();

        // Delete the relationShip
        restRelationShipMockMvc.perform(delete("/api/relation-ships/{id}", relationShip.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelationShip> relationShipList = relationShipRepository.findAll();
        assertThat(relationShipList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
