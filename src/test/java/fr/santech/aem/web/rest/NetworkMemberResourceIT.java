package fr.santech.aem.web.rest;

import fr.santech.aem.AemApp;
import fr.santech.aem.domain.NetworkMember;
import fr.santech.aem.domain.Network;
import fr.santech.aem.domain.Profile;
import fr.santech.aem.repository.NetworkMemberRepository;
import fr.santech.aem.service.NetworkMemberService;
import fr.santech.aem.service.dto.NetworkMemberDTO;
import fr.santech.aem.service.mapper.NetworkMemberMapper;

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
 * Integration tests for the {@link NetworkMemberResource} REST controller.
 */
@SpringBootTest(classes = AemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NetworkMemberResourceIT {

    @Autowired
    private NetworkMemberRepository networkMemberRepository;

    @Autowired
    private NetworkMemberMapper networkMemberMapper;

    @Autowired
    private NetworkMemberService networkMemberService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNetworkMemberMockMvc;

    private NetworkMember networkMember;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NetworkMember createEntity(EntityManager em) {
        NetworkMember networkMember = new NetworkMember();
        // Add required entity
        Network network;
        if (TestUtil.findAll(em, Network.class).isEmpty()) {
            network = NetworkResourceIT.createEntity(em);
            em.persist(network);
            em.flush();
        } else {
            network = TestUtil.findAll(em, Network.class).get(0);
        }
        networkMember.setNetwork(network);
        // Add required entity
        Profile profile;
        if (TestUtil.findAll(em, Profile.class).isEmpty()) {
            profile = ProfileResourceIT.createEntity(em);
            em.persist(profile);
            em.flush();
        } else {
            profile = TestUtil.findAll(em, Profile.class).get(0);
        }
        networkMember.setProfile(profile);
        return networkMember;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NetworkMember createUpdatedEntity(EntityManager em) {
        NetworkMember networkMember = new NetworkMember();
        // Add required entity
        Network network;
        if (TestUtil.findAll(em, Network.class).isEmpty()) {
            network = NetworkResourceIT.createUpdatedEntity(em);
            em.persist(network);
            em.flush();
        } else {
            network = TestUtil.findAll(em, Network.class).get(0);
        }
        networkMember.setNetwork(network);
        // Add required entity
        Profile profile;
        if (TestUtil.findAll(em, Profile.class).isEmpty()) {
            profile = ProfileResourceIT.createUpdatedEntity(em);
            em.persist(profile);
            em.flush();
        } else {
            profile = TestUtil.findAll(em, Profile.class).get(0);
        }
        networkMember.setProfile(profile);
        return networkMember;
    }

    @BeforeEach
    public void initTest() {
        networkMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createNetworkMember() throws Exception {
        int databaseSizeBeforeCreate = networkMemberRepository.findAll().size();
        // Create the NetworkMember
        NetworkMemberDTO networkMemberDTO = networkMemberMapper.toDto(networkMember);
        restNetworkMemberMockMvc.perform(post("/api/network-members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the NetworkMember in the database
        List<NetworkMember> networkMemberList = networkMemberRepository.findAll();
        assertThat(networkMemberList).hasSize(databaseSizeBeforeCreate + 1);
        NetworkMember testNetworkMember = networkMemberList.get(networkMemberList.size() - 1);
    }

    @Test
    @Transactional
    public void createNetworkMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = networkMemberRepository.findAll().size();

        // Create the NetworkMember with an existing ID
        networkMember.setId(1L);
        NetworkMemberDTO networkMemberDTO = networkMemberMapper.toDto(networkMember);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNetworkMemberMockMvc.perform(post("/api/network-members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkMemberDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NetworkMember in the database
        List<NetworkMember> networkMemberList = networkMemberRepository.findAll();
        assertThat(networkMemberList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNetworkMembers() throws Exception {
        // Initialize the database
        networkMemberRepository.saveAndFlush(networkMember);

        // Get all the networkMemberList
        restNetworkMemberMockMvc.perform(get("/api/network-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(networkMember.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getNetworkMember() throws Exception {
        // Initialize the database
        networkMemberRepository.saveAndFlush(networkMember);

        // Get the networkMember
        restNetworkMemberMockMvc.perform(get("/api/network-members/{id}", networkMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(networkMember.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingNetworkMember() throws Exception {
        // Get the networkMember
        restNetworkMemberMockMvc.perform(get("/api/network-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNetworkMember() throws Exception {
        // Initialize the database
        networkMemberRepository.saveAndFlush(networkMember);

        int databaseSizeBeforeUpdate = networkMemberRepository.findAll().size();

        // Update the networkMember
        NetworkMember updatedNetworkMember = networkMemberRepository.findById(networkMember.getId()).get();
        // Disconnect from session so that the updates on updatedNetworkMember are not directly saved in db
        em.detach(updatedNetworkMember);
        NetworkMemberDTO networkMemberDTO = networkMemberMapper.toDto(updatedNetworkMember);

        restNetworkMemberMockMvc.perform(put("/api/network-members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkMemberDTO)))
            .andExpect(status().isOk());

        // Validate the NetworkMember in the database
        List<NetworkMember> networkMemberList = networkMemberRepository.findAll();
        assertThat(networkMemberList).hasSize(databaseSizeBeforeUpdate);
        NetworkMember testNetworkMember = networkMemberList.get(networkMemberList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingNetworkMember() throws Exception {
        int databaseSizeBeforeUpdate = networkMemberRepository.findAll().size();

        // Create the NetworkMember
        NetworkMemberDTO networkMemberDTO = networkMemberMapper.toDto(networkMember);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNetworkMemberMockMvc.perform(put("/api/network-members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkMemberDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NetworkMember in the database
        List<NetworkMember> networkMemberList = networkMemberRepository.findAll();
        assertThat(networkMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNetworkMember() throws Exception {
        // Initialize the database
        networkMemberRepository.saveAndFlush(networkMember);

        int databaseSizeBeforeDelete = networkMemberRepository.findAll().size();

        // Delete the networkMember
        restNetworkMemberMockMvc.perform(delete("/api/network-members/{id}", networkMember.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NetworkMember> networkMemberList = networkMemberRepository.findAll();
        assertThat(networkMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
