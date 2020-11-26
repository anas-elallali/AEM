package fr.santech.aem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A RelationShip.
 */
@Entity
@Table(name = "relation_ship")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelationShip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "relation_ship", length = 100, nullable = false)
    private String relationShip;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "relationShips", allowSetters = true)
    private NetworkMember networkMember;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public RelationShip relationShip(String relationShip) {
        this.relationShip = relationShip;
        return this;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public NetworkMember getNetworkMember() {
        return networkMember;
    }

    public RelationShip networkMember(NetworkMember networkMember) {
        this.networkMember = networkMember;
        return this;
    }

    public void setNetworkMember(NetworkMember networkMember) {
        this.networkMember = networkMember;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelationShip)) {
            return false;
        }
        return id != null && id.equals(((RelationShip) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelationShip{" +
            "id=" + getId() +
            ", relationShip='" + getRelationShip() + "'" +
            "}";
    }
}
