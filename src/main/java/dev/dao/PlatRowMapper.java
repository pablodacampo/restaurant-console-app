package dev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import dev.entite.Plat;

public class PlatRowMapper implements RowMapper<Plat> {

	 @Override
	    public Plat mapRow(ResultSet resultSet, int i) throws SQLException {
	        Plat plat = new Plat();
	        plat.setPrixEnCentimesEuros(resultSet.getInt("prix"));
	        plat.setNom(resultSet.getString("nom"));
	        return plat;
	    }

	
}
