package fr.santech.aem.service.mapper;


import fr.santech.aem.domain.*;
import fr.santech.aem.service.dto.NetworkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Network} and its DTO {@link NetworkDTO}.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, ProfileMapper.class})
public interface NetworkMapper extends EntityMapper<NetworkDTO, Network> {

    @Mapping(source = "parentNetwork.id", target = "parentNetworkId")
    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "owner.id", target = "ownerId")
    NetworkDTO toDto(Network network);

    @Mapping(source = "parentNetworkId", target = "parentNetwork")
    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "networkMembers", ignore = true)
    @Mapping(target = "removeNetworkMember", ignore = true)
    @Mapping(source = "ownerId", target = "owner")
    Network toEntity(NetworkDTO networkDTO);

    default Network fromId(Long id) {
        if (id == null) {
            return null;
        }
        Network network = new Network();
        network.setId(id);
        return network;
    }
}
