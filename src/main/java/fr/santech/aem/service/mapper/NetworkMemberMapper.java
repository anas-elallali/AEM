package fr.santech.aem.service.mapper;


import fr.santech.aem.domain.*;
import fr.santech.aem.service.dto.NetworkMemberDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NetworkMember} and its DTO {@link NetworkMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = {NetworkMapper.class, ProfileMapper.class})
public interface NetworkMemberMapper extends EntityMapper<NetworkMemberDTO, NetworkMember> {

    @Mapping(source = "network.id", target = "networkId")
    @Mapping(source = "profile.id", target = "profileId")
    NetworkMemberDTO toDto(NetworkMember networkMember);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "removeRoles", ignore = true)
    @Mapping(source = "networkId", target = "network")
    @Mapping(source = "profileId", target = "profile")
    NetworkMember toEntity(NetworkMemberDTO networkMemberDTO);

    default NetworkMember fromId(Long id) {
        if (id == null) {
            return null;
        }
        NetworkMember networkMember = new NetworkMember();
        networkMember.setId(id);
        return networkMember;
    }
}
