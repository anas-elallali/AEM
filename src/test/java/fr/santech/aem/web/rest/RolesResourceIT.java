package fr.santech.aem.web.rest;

import fr.santech.aem.AemApp;
import fr.santech.aem.domain.Roles;
import fr.santech.aem.domain.NetworkMember;
import fr.santech.aem.repository.RolesRepository;
import fr.santech.aem.service.RolesService;
import fr.santech.aem.service.dto.RolesDTO;
import fr.santech.aem.service.mapper.RolesMapper;

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

import fr.santech.aem.domain.enumeration.Role;
/**
 * Integration tests for the {@link RolesResource} REST controller.
 */
@SpringBootTest(classes = AemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RolesResourceIT {

    private static final Role DEFAULT_ROLE = Role.ADMIN;
    private static final Role UPDATED_ROLE = Role.ANIMATOR;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRolesMockMvc;

    private Roles roles;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Roles createEntity(EntityManager em) {
        Roles roles = new Roles()
            .role(DEFAULT_ROLE);
        // Add required entity
        NetworkMember networkMember;
        if (TestUtil.findAll(em, NetworkMember.class).isEmpty()) {
            networkMember = NetworkMemberResourceIT.createEntity(em);
            em.persist(networkMember);
            em.flush();
        } else {
            networkMember = TestUtil.findAll(em, NetworkMember.class).get(0);
        }
        roles.setNetworkMember(networkMember);
        return roles;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Roles createUpdatedEntity(EntityManager em) {
        Roles roles = new Roles()
            .role(UPDATED_ROLE);
        // Add required entity
        NetworkMember networkMember;
        if (TestUtil.findAll(em, NetworkMember.class).isEmpty()) {
            networkMember = NetworkMemberResourceIT.createUpdatedEntity(em);
            em.persist(networkMember);
            em.flush();
        } else {
            networkMember = TestUtil.findAll(em, NetworkMember.class).get(0);
        }
        roles.setNetworkMember(networkMember);
        return roles;
    }

    @BeforeEach
    public void initTest() {
        roles = createEntity(em);
    }

    @Test
    @Transactional
    public void createRoles() throws Exception {
        int databaseSizeBeforeCreate = rolesRepository.findAll().size();
        // Create the Roles
        RolesDTO rolesDTO = rolesMapper.toDto(roles);
        restRolesMockMvc.perform(post("/api/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isCreated());

        // Validate the Roles in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeCreate + 1);
        Roles testRoles = rolesList.get(rolesList.size() - 1);
        assertThat(testRoles.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    public void createRolesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rolesRepository.findAll().size();

        // Create the Roles with an existing ID
        roles.setId(1L);
        RolesDTO rolesDTO = rolesMapper.toDto(roles);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRolesMockMvc.perform(post("/api/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Roles in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = rolesRepository.findAll().size();
        // set the field null
        roles.setRole(null);

        // Create the Roles, which fails.
        RolesDTO rolesDTO = rolesMapper.toDto(roles);


        restRolesMockMvc.perform(post("/api/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isBadRequest());

        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);

        // Get all the rolesList
        restRolesMockMvc.perform(get("/api/roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roles.getId().intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);

        // Get the roles
        restRolesMockMvc.perform(get("/api/roles/{id}", roles.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(roles.getId().intValue()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRoles() throws Exception {
        // Get the roles
        restRolesMockMvc.perform(get("/api/roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);

        int databaseSizeBeforeUpdate = rolesRepository.findAll().size();

        // Update the roles
        Roles updatedRoles = rolesRepository.findById(roles.getId()).get();
        // Disconnect from session so that the updates on updatedRoles are not directly saved in db
        em.detach(updatedRoles);
        updatedRoles
            .role(UPDATED_ROLE);
        RolesDTO rolesDTO = rolesMapper.toDto(updatedRoles);

        restRolesMockMvc.perform(put("/api/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isOk());

        // Validate the Roles in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeUpdate);
        Roles testRoles = rolesList.get(rolesList.size() - 1);
        assertThat(testRoles.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRoles() throws Exception {
        int databaseSizeBeforeUpdate = rolesRepository.findAll().size();

        // Create the Roles
        RolesDTO rolesDTO = rolesMapper.toDto(roles);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRolesMockMvc.perform(put("/api/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Roles in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);

        int databaseSizeBeforeDelete = rolesRepository.findAll().size();

        // Delete the roles
        restRolesMockMvc.perform(delete("/api/roles/{id}", roles.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
