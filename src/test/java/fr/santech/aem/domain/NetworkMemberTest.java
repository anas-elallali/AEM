package fr.santech.aem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.santech.aem.web.rest.TestUtil;

public class NetworkMemberTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NetworkMember.class);
        NetworkMember networkMember1 = new NetworkMember();
        networkMember1.setId(1L);
        NetworkMember networkMember2 = new NetworkMember();
        networkMember2.setId(networkMember1.getId());
        assertThat(networkMember1).isEqualTo(networkMember2);
        networkMember2.setId(2L);
        assertThat(networkMember1).isNotEqualTo(networkMember2);
        networkMember1.setId(null);
        assertThat(networkMember1).isNotEqualTo(networkMember2);
    }
}
