package fr.santech.aem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.santech.aem.domain.NetworkMember} entity.
 */
public class NetworkMemberDTO implements Serializable {
    
    private Long id;


    private Long networkId;

    private Long profileId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetworkMemberDTO)) {
            return false;
        }

        return id != null && id.equals(((NetworkMemberDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NetworkMemberDTO{" +
            "id=" + getId() +
            ", networkId=" + getNetworkId() +
            ", profileId=" + getProfileId() +
            "}";
    }
}
