package dev.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BasePlatDaoBddIntegrationTest {

    @Autowired
    private IPlatDao dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void listerPlatsNonVideTest() {
        int tailleListe = dao.listerPlats().size();
        assertThat(tailleListe > 0);
    }

    @Test
    public void ajouterPlatTest() {

        dao.ajouterPlat("abcdef", 100000);

        Integer prix = jdbcTemplate.queryForObject("select prix_plat from plat where nom_plat='abcdef'", Integer.class);

        assertThat(prix).isEqualTo(100000);
    }
}
