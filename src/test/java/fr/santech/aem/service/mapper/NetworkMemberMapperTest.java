package fr.santech.aem.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NetworkMemberMapperTest {

    private NetworkMemberMapper networkMemberMapper;

    @BeforeEach
    public void setUp() {
        networkMemberMapper = new NetworkMemberMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(networkMemberMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(networkMemberMapper.fromId(null)).isNull();
    }
}
