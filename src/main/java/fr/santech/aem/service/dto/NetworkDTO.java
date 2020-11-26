package fr.santech.aem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;
import fr.santech.aem.domain.enumeration.NetworkType;

/**
 * A DTO for the {@link fr.santech.aem.domain.Network} entity.
 */
public class NetworkDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @Size(max = 500)
    private String description;

    @Lob
    private byte[] avatar;

    private String avatarContentType;
    @NotNull
    private NetworkType type;

    @NotNull
    private Boolean status;


    private Long parentNetworkId;

    private Long addressId;

    private Long ownerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getAvatarContentType() {
        return avatarContentType;
    }

    public void setAvatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
    }

    public NetworkType getType() {
        return type;
    }

    public void setType(NetworkType type) {
        this.type = type;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getParentNetworkId() {
        return parentNetworkId;
    }

    public void setParentNetworkId(Long networkId) {
        this.parentNetworkId = networkId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long profileId) {
        this.ownerId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetworkDTO)) {
            return false;
        }

        return id != null && id.equals(((NetworkDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NetworkDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + isStatus() + "'" +
            ", parentNetworkId=" + getParentNetworkId() +
            ", addressId=" + getAddressId() +
            ", ownerId=" + getOwnerId() +
            "}";
    }
}
