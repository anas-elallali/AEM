package fr.santech.aem.service.mapper;


import fr.santech.aem.domain.*;
import fr.santech.aem.service.dto.RelationShipDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelationShip} and its DTO {@link RelationShipDTO}.
 */
@Mapper(componentModel = "spring", uses = {NetworkMemberMapper.class})
public interface RelationShipMapper extends EntityMapper<RelationShipDTO, RelationShip> {

    @Mapping(source = "networkMember.id", target = "networkMemberId")
    RelationShipDTO toDto(RelationShip relationShip);

    @Mapping(source = "networkMemberId", target = "networkMember")
    RelationShip toEntity(RelationShipDTO relationShipDTO);

    default RelationShip fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelationShip relationShip = new RelationShip();
        relationShip.setId(id);
        return relationShip;
    }
}
