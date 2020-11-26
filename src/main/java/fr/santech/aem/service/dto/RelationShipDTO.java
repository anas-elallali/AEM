package fr.santech.aem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.santech.aem.domain.RelationShip} entity.
 */
public class RelationShipDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String relationShip;


    private Long networkMemberId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
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
        if (!(o instanceof RelationShipDTO)) {
            return false;
        }

        return id != null && id.equals(((RelationShipDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelationShipDTO{" +
            "id=" + getId() +
            ", relationShip='" + getRelationShip() + "'" +
            ", networkMemberId=" + getNetworkMemberId() +
            "}";
    }
}
