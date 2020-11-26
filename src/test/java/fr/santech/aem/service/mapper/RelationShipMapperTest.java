package fr.santech.aem.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RelationShipMapperTest {

    private RelationShipMapper relationShipMapper;

    @BeforeEach
    public void setUp() {
        relationShipMapper = new RelationShipMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relationShipMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relationShipMapper.fromId(null)).isNull();
    }
}
