package fr.santech.aem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.santech.aem.web.rest.TestUtil;

public class RelationShipTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelationShip.class);
        RelationShip relationShip1 = new RelationShip();
        relationShip1.setId(1L);
        RelationShip relationShip2 = new RelationShip();
        relationShip2.setId(relationShip1.getId());
        assertThat(relationShip1).isEqualTo(relationShip2);
        relationShip2.setId(2L);
        assertThat(relationShip1).isNotEqualTo(relationShip2);
        relationShip1.setId(null);
        assertThat(relationShip1).isNotEqualTo(relationShip2);
    }
}
