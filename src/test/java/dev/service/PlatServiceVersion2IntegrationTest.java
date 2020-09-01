package dev.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

// @SpringJUnitConfig({ PlatServiceVersion2.class, PlatDaoMemoire.class })

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PlatServiceVersion2.class, PlatDaoMemoire.class }) // conf context spring
class PlatServiceVersion2IntegrationTest {

	@Autowired
	private PlatServiceVersion2 psv2;

	@Test
	public void testIntegrateAjoutPlat() {

		psv2.ajouterPlat("azerty", 6000);
		List<Plat> list = psv2.listerPlats();
		assertThat(list.size()).isEqualTo(1);
		assertThat(list).extracting(Plat::getNom).contains("azerty");
		assertThat(list).extracting(Plat::getPrixEnCentimesEuros).contains(6000);
	}

	@Test
	public void testIntegratePrixInvalid() {
		assertThatThrownBy(
				() ->  	psv2.ajouterPlat("azerty", 6)
		).isInstanceOf(PlatException.class)
		.hasMessage("le prix d'un plat doit être supérieur à 10 €");

		/*
		try {

			fail("doit produire une exception");
		} catch (PlatException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("le prix d'un plat doit être supérieur à 10 €");
		}

		 */
	}
}
