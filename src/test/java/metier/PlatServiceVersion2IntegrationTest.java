package metier;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;
import dev.service.PlatServiceVersion2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PlatServiceVersion2.class, PlatDaoMemoire.class }) // liste des beans à créer
@ActiveProfiles({ "memoire", "service2" })
public class PlatServiceVersion2IntegrationTest {

	@Autowired
	private PlatServiceVersion2 platServiceVersion2;

	@Test
	public void ajouterPlatValide() {

		platServiceVersion2.ajouterPlat("pateCarbo", 1100);
		List<Plat> list = platServiceVersion2.listerPlats();
		Assertions.assertThat(list.size()).isEqualTo(1);
		
		// pas nécessaire
		assertThat(list).extracting(Plat::getNom).contains("pateCarbo");
		assertThat(list).extracting(Plat::getPrixEnCentimesEuros).contains(1100);
	}

	
	@Test
	public void ajouterPlatPrixInvalide() {

		Assertions.assertThatThrownBy(() -> platServiceVersion2.ajouterPlat("pateCarbo", 1))
				.isInstanceOf(PlatException.class).hasMessage(("le prix d'un plat doit être supérieur à 10 €"));

	}

}
