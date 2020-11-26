package fr.santech.aem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import fr.santech.aem.domain.enumeration.AddressType;

/**
 * A DTO for the {@link fr.santech.aem.domain.Address} entity.
 */
public class AddressDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 255)
    private String street;

    @Size(max = 255)
    private String additional;

    @NotNull
    @Size(max = 255)
    private String city;

    @NotNull
    private AddressType type;

    @NotNull
    @Size(max = 10)
    private String zipCode;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }

        return id != null && id.equals(((AddressDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", street='" + getStreet() + "'" +
            ", additional='" + getAdditional() + "'" +
            ", city='" + getCity() + "'" +
            ", type='" + getType() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            "}";
    }
}
