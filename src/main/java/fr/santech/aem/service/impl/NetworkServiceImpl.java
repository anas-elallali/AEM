package fr.santech.aem.service.impl;

import fr.santech.aem.service.NetworkService;
import fr.santech.aem.domain.Network;
import fr.santech.aem.repository.NetworkRepository;
import fr.santech.aem.service.dto.NetworkDTO;
import fr.santech.aem.service.mapper.NetworkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Network}.
 */
@Service
@Transactional
public class NetworkServiceImpl implements NetworkService {

    private final Logger log = LoggerFactory.getLogger(NetworkServiceImpl.class);

    private final NetworkRepository networkRepository;

    private final NetworkMapper networkMapper;

    public NetworkServiceImpl(NetworkRepository networkRepository, NetworkMapper networkMapper) {
        this.networkRepository = networkRepository;
        this.networkMapper = networkMapper;
    }

    @Override
    public NetworkDTO save(NetworkDTO networkDTO) {
        log.debug("Request to save Network : {}", networkDTO);
        Network network = networkMapper.toEntity(networkDTO);
        network = networkRepository.save(network);
        return networkMapper.toDto(network);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NetworkDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Networks");
        return networkRepository.findAll(pageable)
            .map(networkMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NetworkDTO> findOne(Long id) {
        log.debug("Request to get Network : {}", id);
        return networkRepository.findById(id)
            .map(networkMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Network : {}", id);
        networkRepository.deleteById(id);
    }
}
