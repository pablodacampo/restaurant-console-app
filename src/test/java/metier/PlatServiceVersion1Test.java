package metier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.dao.IPlatDao;
import dev.exception.PlatException;
import dev.service.PlatServiceVersion1;

public class PlatServiceVersion1Test {

	private PlatServiceVersion1 platServiceVersion1;
	private IPlatDao dao;

	@BeforeEach
	public void setUp() {

		dao = mock(IPlatDao.class);
		platServiceVersion1 = new PlatServiceVersion1(dao);
		
	}

	@Test
	public void ajouterPlatNomInvalide() throws PlatException {

		PlatException exception = assertThrows(PlatException.class, () -> platServiceVersion1.ajouterPlat("ppp", 500));
		assertEquals("un plat doit avoir un nom de plus de 3 caractères", exception.getMessage());

	}

	@Test
	public void ajouterPlatPrixInvalide() throws PlatException {

		assertThrows(PlatException.class, () -> platServiceVersion1.ajouterPlat("patesPesto", 6));

	}

	@Test
	public void ajouterPlatValide() {

		platServiceVersion1.ajouterPlat("pateCarbo", 600);
		verify(dao, atLeastOnce()).ajouterPlat("pateCarbo", 600);
	}

}
