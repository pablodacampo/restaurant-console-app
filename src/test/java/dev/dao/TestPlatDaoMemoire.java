package dev.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

class TestPlatDaoMemoire {

	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp(){
		this.platDaoMemoire=new PlatDaoMemoire();
	}
	
	
	@Test
	void listerPlatsVideALInitialisation(){
		
		List<Plat> data=platDaoMemoire.listerPlats();
		Assertions.assertThat(data).isEmpty();
	}
	
	@Test
	void ajouterPlatCasPassants(){
		platDaoMemoire.ajouterPlat("platA", 1500);
		List<Plat> data=platDaoMemoire.listerPlats();
		Assertions.assertThat(data).isNotEmpty();		  
	}
	
	
	
}