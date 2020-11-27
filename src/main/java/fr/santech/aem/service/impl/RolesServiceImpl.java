package fr.santech.aem.service.impl;

import fr.santech.aem.service.RolesService;
import fr.santech.aem.domain.Roles;
import fr.santech.aem.repository.RolesRepository;
import fr.santech.aem.service.dto.RolesDTO;
import fr.santech.aem.service.mapper.RolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Roles}.
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService {

    private final Logger log = LoggerFactory.getLogger(RolesServiceImpl.class);

    private final RolesRepository rolesRepository;

    private final RolesMapper rolesMapper;

    public RolesServiceImpl(RolesRepository rolesRepository, RolesMapper rolesMapper) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
    }

    @Override
    public RolesDTO save(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RolesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Roles");
        return rolesRepository.findAll(pageable)
            .map(rolesMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findOne(Long id) {
        log.debug("Request to get Roles : {}", id);
        return rolesRepository.findById(id)
            .map(rolesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Roles : {}", id);
        rolesRepository.deleteById(id);
    }
}
