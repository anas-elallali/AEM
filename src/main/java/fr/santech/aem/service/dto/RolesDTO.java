package fr.santech.aem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import fr.santech.aem.domain.enumeration.Role;

/**
 * A DTO for the {@link fr.santech.aem.domain.Roles} entity.
 */
public class RolesDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Role role;


    private Long networkMemberId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getNetworkMemberId() {
        return networkMemberId;
    }

    public void setNetworkMemberId(Long networkMemberId) {
        this.networkMemberId = networkMemberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RolesDTO)) {
            return false;
        }

        return id != null && id.equals(((RolesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RolesDTO{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            ", networkMemberId=" + getNetworkMemberId() +
            "}";
    }
}
