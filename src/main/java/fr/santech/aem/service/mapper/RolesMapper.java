package fr.santech.aem.service.mapper;


import fr.santech.aem.domain.*;
import fr.santech.aem.service.dto.RolesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roles} and its DTO {@link RolesDTO}.
 */
@Mapper(componentModel = "spring", uses = {NetworkMemberMapper.class})
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {

    @Mapping(source = "networkMember.id", target = "networkMemberId")
    RolesDTO toDto(Roles roles);

    @Mapping(source = "networkMemberId", target = "networkMember")
    Roles toEntity(RolesDTO rolesDTO);

    default Roles fromId(Long id) {
        if (id == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(id);
        return roles;
    }
}
