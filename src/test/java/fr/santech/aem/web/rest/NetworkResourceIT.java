package fr.santech.aem.web.rest;

import fr.santech.aem.AemApp;
import fr.santech.aem.domain.Network;
import fr.santech.aem.repository.NetworkRepository;
import fr.santech.aem.service.NetworkService;
import fr.santech.aem.service.dto.NetworkDTO;
import fr.santech.aem.service.mapper.NetworkMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.santech.aem.domain.enumeration.NetworkType;
/**
 * Integration tests for the {@link NetworkResource} REST controller.
 */
@SpringBootTest(classes = AemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NetworkResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_AVATAR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_AVATAR = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_AVATAR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AVATAR_CONTENT_TYPE = "image/png";

    private static final NetworkType DEFAULT_TYPE = NetworkType.AEM_ENTITY;
    private static final NetworkType UPDATED_TYPE = NetworkType.AEM_RESIDENCE;

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private NetworkMapper networkMapper;

    @Autowired
    private NetworkService networkService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNetworkMockMvc;

    private Network network;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Network createEntity(EntityManager em) {
        Network network = new Network()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .avatar(DEFAULT_AVATAR)
            .avatarContentType(DEFAULT_AVATAR_CONTENT_TYPE)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS);
        return network;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Network createUpdatedEntity(EntityManager em) {
        Network network = new Network()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .avatar(UPDATED_AVATAR)
            .avatarContentType(UPDATED_AVATAR_CONTENT_TYPE)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);
        return network;
    }

    @BeforeEach
    public void initTest() {
        network = createEntity(em);
    }

    @Test
    @Transactional
    public void createNetwork() throws Exception {
        int databaseSizeBeforeCreate = networkRepository.findAll().size();
        // Create the Network
        NetworkDTO networkDTO = networkMapper.toDto(network);
        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isCreated());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeCreate + 1);
        Network testNetwork = networkList.get(networkList.size() - 1);
        assertThat(testNetwork.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNetwork.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNetwork.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testNetwork.getAvatarContentType()).isEqualTo(DEFAULT_AVATAR_CONTENT_TYPE);
        assertThat(testNetwork.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testNetwork.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createNetworkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = networkRepository.findAll().size();

        // Create the Network with an existing ID
        network.setId(1L);
        NetworkDTO networkDTO = networkMapper.toDto(network);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = networkRepository.findAll().size();
        // set the field null
        network.setName(null);

        // Create the Network, which fails.
        NetworkDTO networkDTO = networkMapper.toDto(network);


        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isBadRequest());

        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = networkRepository.findAll().size();
        // set the field null
        network.setType(null);

        // Create the Network, which fails.
        NetworkDTO networkDTO = networkMapper.toDto(network);


        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isBadRequest());

        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = networkRepository.findAll().size();
        // set the field null
        network.setStatus(null);

        // Create the Network, which fails.
        NetworkDTO networkDTO = networkMapper.toDto(network);


        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isBadRequest());

        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNetworks() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList
        restNetworkMockMvc.perform(get("/api/networks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(network.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].avatarContentType").value(hasItem(DEFAULT_AVATAR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(Base64Utils.encodeToString(DEFAULT_AVATAR))))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getNetwork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get the network
        restNetworkMockMvc.perform(get("/api/networks/{id}", network.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(network.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.avatarContentType").value(DEFAULT_AVATAR_CONTENT_TYPE))
            .andExpect(jsonPath("$.avatar").value(Base64Utils.encodeToString(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingNetwork() throws Exception {
        // Get the network
        restNetworkMockMvc.perform(get("/api/networks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNetwork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        int databaseSizeBeforeUpdate = networkRepository.findAll().size();

        // Update the network
        Network updatedNetwork = networkRepository.findById(network.getId()).get();
        // Disconnect from session so that the updates on updatedNetwork are not directly saved in db
        em.detach(updatedNetwork);
        updatedNetwork
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .avatar(UPDATED_AVATAR)
            .avatarContentType(UPDATED_AVATAR_CONTENT_TYPE)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);
        NetworkDTO networkDTO = networkMapper.toDto(updatedNetwork);

        restNetworkMockMvc.perform(put("/api/networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isOk());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeUpdate);
        Network testNetwork = networkList.get(networkList.size() - 1);
        assertThat(testNetwork.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNetwork.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNetwork.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testNetwork.getAvatarContentType()).isEqualTo(UPDATED_AVATAR_CONTENT_TYPE);
        assertThat(testNetwork.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testNetwork.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingNetwork() throws Exception {
        int databaseSizeBeforeUpdate = networkRepository.findAll().size();

        // Create the Network
        NetworkDTO networkDTO = networkMapper.toDto(network);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNetworkMockMvc.perform(put("/api/networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNetwork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        int databaseSizeBeforeDelete = networkRepository.findAll().size();

        // Delete the network
        restNetworkMockMvc.perform(delete("/api/networks/{id}", network.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
