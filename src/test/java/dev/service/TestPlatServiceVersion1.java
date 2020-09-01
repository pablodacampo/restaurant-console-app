
package dev.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

class TestPlatServiceVersion1 {
//	private Service service1;
	private IPlatDao dao;
	private PlatServiceVersion1 platService;

	@BeforeEach
	public void setUp() {
		dao = Mockito.mock(IPlatDao.class);
		platService = new PlatServiceVersion1(dao);
	}

	@Test
	void AjouterPlatNomInvalide() {
		try {
			platService.ajouterPlat("a", 1000);
			fail("doit produire une exception");
		} catch (PlatException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("un plat doit avoir un nom de plus de 3 caractères");
		}

	}

	@Test
	void AjouterPlatPrixInvalide() {
		try {
			platService.ajouterPlat("azerty", 1);
			fail("doit produire une exception");
		} catch (PlatException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("le prix d'un plat doit être supérieur à 5 €");
		}
	}

	@Test
	void AjouterPlatNomEtPrixValide() {
		platService.ajouterPlat("azerty", 5000);
		Mockito.verify(dao).ajouterPlat("azerty", 5000);

	}

}
