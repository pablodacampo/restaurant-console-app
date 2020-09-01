package dev.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import dev.exception.PlatException;
import dev.service.PlatServiceVersion1;

public class PlatServiceVersion1MockitoTest {
	
	private PlatServiceVersion1 platServiceVersion1;
	private IPlatDao mockedPlatDao;
	
	@BeforeEach
	public void setUp() {
		mockedPlatDao = mock(IPlatDao.class);
		platServiceVersion1 = new PlatServiceVersion1(mockedPlatDao);
	}
	
	@Test
	void ajouterPlatNomInvalide() throws PlatException {

		PlatException exception = assertThrows(
				PlatException.class, // 1
				() -> platServiceVersion1.ajouterPlat("a", 500) // 2
		);
		assertEquals("un plat doit avoir un nom de plus de 3 caractères", exception.getMessage());
	}
	
	@Test
	void ajouterPlatPrixInvalide() throws PlatException {
		assertThrows(PlatException.class, () -> platServiceVersion1.ajouterPlat("couscous", 5));
	}
	
	@Test
	void ajouterPlatNomValidePrixValide() {
		platServiceVersion1.ajouterPlat("couscous", 600);

		verify(mockedPlatDao).ajouterPlat("couscous", 600);
	}

}
