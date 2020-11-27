package fr.santech.aem.service.mapper;


import fr.santech.aem.domain.*;
import fr.santech.aem.service.dto.ProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profile} and its DTO {@link ProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, UserMapper.class})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "user.id", target = "userId")
    ProfileDTO toDto(Profile profile);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "networks", ignore = true)
    @Mapping(target = "removeNetworks", ignore = true)
    @Mapping(target = "networkMembers", ignore = true)
    @Mapping(target = "removeNetworkMember", ignore = true)
    Profile toEntity(ProfileDTO profileDTO);

    default Profile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(id);
        return profile;
    }
}
