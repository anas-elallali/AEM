package fr.santech.aem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.santech.aem.domain.enumeration.NetworkType;

/**
 * A Network.
 */
@Entity
@Table(name = "network")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Network implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Lob
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "avatar_content_type")
    private String avatarContentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NetworkType type;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToOne
    @JoinColumn(unique = true)
    private Network parentNetwork;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "network")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NetworkMember> networkMembers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "networks", allowSetters = true)
    private Profile owner;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Network name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Network description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public Network avatar(byte[] avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getAvatarContentType() {
        return avatarContentType;
    }

    public Network avatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
        return this;
    }

    public void setAvatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
    }

    public NetworkType getType() {
        return type;
    }

    public Network type(NetworkType type) {
        this.type = type;
        return this;
    }

    public void setType(NetworkType type) {
        this.type = type;
    }

    public Boolean isStatus() {
        return status;
    }

    public Network status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Network getParentNetwork() {
        return parentNetwork;
    }

    public Network parentNetwork(Network network) {
        this.parentNetwork = network;
        return this;
    }

    public void setParentNetwork(Network network) {
        this.parentNetwork = network;
    }

    public Address getAddress() {
        return address;
    }

    public Network address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<NetworkMember> getNetworkMembers() {
        return networkMembers;
    }

    public Network networkMembers(Set<NetworkMember> networkMembers) {
        this.networkMembers = networkMembers;
        return this;
    }

    public Network addNetworkMember(NetworkMember networkMember) {
        this.networkMembers.add(networkMember);
        networkMember.setNetwork(this);
        return this;
    }

    public Network removeNetworkMember(NetworkMember networkMember) {
        this.networkMembers.remove(networkMember);
        networkMember.setNetwork(null);
        return this;
    }

    public void setNetworkMembers(Set<NetworkMember> networkMembers) {
        this.networkMembers = networkMembers;
    }

    public Profile getOwner() {
        return owner;
    }

    public Network owner(Profile profile) {
        this.owner = profile;
        return this;
    }

    public void setOwner(Profile profile) {
        this.owner = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Network)) {
            return false;
        }
        return id != null && id.equals(((Network) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Network{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", avatarContentType='" + getAvatarContentType() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
