package fr.santech.aem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NetworkMember.
 */
@Entity
@Table(name = "network_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NetworkMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "networkMember")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Roles> roles = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "networkMembers", allowSetters = true)
    private Network network;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "networkMembers", allowSetters = true)
    private Profile profile;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public NetworkMember roles(Set<Roles> roles) {
        this.roles = roles;
        return this;
    }

    public NetworkMember addRoles(Roles roles) {
        this.roles.add(roles);
        roles.setNetworkMember(this);
        return this;
    }

    public NetworkMember removeRoles(Roles roles) {
        this.roles.remove(roles);
        roles.setNetworkMember(null);
        return this;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Network getNetwork() {
        return network;
    }

    public NetworkMember network(Network network) {
        this.network = network;
        return this;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Profile getProfile() {
        return profile;
    }

    public NetworkMember profile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetworkMember)) {
            return false;
        }
        return id != null && id.equals(((NetworkMember) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NetworkMember{" +
            "id=" + getId() +
            "}";
    }
}
