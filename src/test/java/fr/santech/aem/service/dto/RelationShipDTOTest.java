package fr.santech.aem.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.santech.aem.web.rest.TestUtil;

public class RelationShipDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelationShipDTO.class);
        RelationShipDTO relationShipDTO1 = new RelationShipDTO();
        relationShipDTO1.setId(1L);
        RelationShipDTO relationShipDTO2 = new RelationShipDTO();
        assertThat(relationShipDTO1).isNotEqualTo(relationShipDTO2);
        relationShipDTO2.setId(relationShipDTO1.getId());
        assertThat(relationShipDTO1).isEqualTo(relationShipDTO2);
        relationShipDTO2.setId(2L);
        assertThat(relationShipDTO1).isNotEqualTo(relationShipDTO2);
        relationShipDTO1.setId(null);
        assertThat(relationShipDTO1).isNotEqualTo(relationShipDTO2);
    }
}
