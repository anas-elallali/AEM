package fr.santech.aem.service.impl;

import fr.santech.aem.service.NetworkMemberService;
import fr.santech.aem.domain.NetworkMember;
import fr.santech.aem.repository.NetworkMemberRepository;
import fr.santech.aem.service.dto.NetworkMemberDTO;
import fr.santech.aem.service.mapper.NetworkMemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NetworkMember}.
 */
@Service
@Transactional
public class NetworkMemberServiceImpl implements NetworkMemberService {

    private final Logger log = LoggerFactory.getLogger(NetworkMemberServiceImpl.class);

    private final NetworkMemberRepository networkMemberRepository;

    private final NetworkMemberMapper networkMemberMapper;

    public NetworkMemberServiceImpl(NetworkMemberRepository networkMemberRepository, NetworkMemberMapper networkMemberMapper) {
        this.networkMemberRepository = networkMemberRepository;
        this.networkMemberMapper = networkMemberMapper;
    }

    @Override
    public NetworkMemberDTO save(NetworkMemberDTO networkMemberDTO) {
        log.debug("Request to save NetworkMember : {}", networkMemberDTO);
        NetworkMember networkMember = networkMemberMapper.toEntity(networkMemberDTO);
        networkMember = networkMemberRepository.save(networkMember);
        return networkMemberMapper.toDto(networkMember);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NetworkMemberDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NetworkMembers");
        return networkMemberRepository.findAll(pageable)
            .map(networkMemberMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NetworkMemberDTO> findOne(Long id) {
        log.debug("Request to get NetworkMember : {}", id);
        return networkMemberRepository.findById(id)
            .map(networkMemberMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NetworkMember : {}", id);
        networkMemberRepository.deleteById(id);
    }
}
