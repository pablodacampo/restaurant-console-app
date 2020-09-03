package dev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

public class PlatServiceVersion1Test {

	PlatServiceVersion1 serviceV1;
	IPlatDao mockedDao;

	@BeforeEach // Avant chaque test, on veut que cette methode s'execute, ici on fournis un
				// nouveau DAO pour indépendance des tests
	void setUp() {
		this.mockedDao = Mockito.mock(IPlatDao.class);
		this.serviceV1 = new PlatServiceVersion1(mockedDao); // On simule un DAO
	}

	@Test
	void testAjouterPlatInvalideNom() throws PlatException {
		PlatException exception = assertThrows(PlatException.class, () -> serviceV1.ajouterPlat("a", 10000));
		assertEquals("un plat doit avoir un nom de plus de 3 caractères", exception.getMessage());
	}

	@Test
	void testAjouterPlatInvalidePrix() throws PlatException {
		PlatException exception = assertThrows(PlatException.class, () -> serviceV1.ajouterPlat("abcd", 1));
		assertEquals("le prix d'un plat doit être supérieur à 5 €", exception.getMessage());
	}

	@Test
	void ajouterPlatNomPrixValide() {
		
		serviceV1.ajouterPlat("Pizza", 100000);
		verify(mockedDao).ajouterPlat("Pizza", 100000);

	}

}
