package fr.santech.aem.service.impl;

import fr.santech.aem.service.RelationShipService;
import fr.santech.aem.domain.RelationShip;
import fr.santech.aem.repository.RelationShipRepository;
import fr.santech.aem.service.dto.RelationShipDTO;
import fr.santech.aem.service.mapper.RelationShipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RelationShip}.
 */
@Service
@Transactional
public class RelationShipServiceImpl implements RelationShipService {

    private final Logger log = LoggerFactory.getLogger(RelationShipServiceImpl.class);

    private final RelationShipRepository relationShipRepository;

    private final RelationShipMapper relationShipMapper;

    public RelationShipServiceImpl(RelationShipRepository relationShipRepository, RelationShipMapper relationShipMapper) {
        this.relationShipRepository = relationShipRepository;
        this.relationShipMapper = relationShipMapper;
    }

    @Override
    public RelationShipDTO save(RelationShipDTO relationShipDTO) {
        log.debug("Request to save RelationShip : {}", relationShipDTO);
        RelationShip relationShip = relationShipMapper.toEntity(relationShipDTO);
        relationShip = relationShipRepository.save(relationShip);
        return relationShipMapper.toDto(relationShip);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelationShipDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelationShips");
        return relationShipRepository.findAll(pageable)
            .map(relationShipMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RelationShipDTO> findOne(Long id) {
        log.debug("Request to get RelationShip : {}", id);
        return relationShipRepository.findById(id)
            .map(relationShipMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelationShip : {}", id);
        relationShipRepository.deleteById(id);
    }
}
