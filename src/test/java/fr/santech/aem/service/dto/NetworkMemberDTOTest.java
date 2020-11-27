package fr.santech.aem.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.santech.aem.web.rest.TestUtil;

public class NetworkMemberDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NetworkMemberDTO.class);
        NetworkMemberDTO networkMemberDTO1 = new NetworkMemberDTO();
        networkMemberDTO1.setId(1L);
        NetworkMemberDTO networkMemberDTO2 = new NetworkMemberDTO();
        assertThat(networkMemberDTO1).isNotEqualTo(networkMemberDTO2);
        networkMemberDTO2.setId(networkMemberDTO1.getId());
        assertThat(networkMemberDTO1).isEqualTo(networkMemberDTO2);
        networkMemberDTO2.setId(2L);
        assertThat(networkMemberDTO1).isNotEqualTo(networkMemberDTO2);
        networkMemberDTO1.setId(null);
        assertThat(networkMemberDTO1).isNotEqualTo(networkMemberDTO2);
    }
}
