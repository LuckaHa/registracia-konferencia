package sk.upjs.registracia_konferencia.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.registracia_konferencia.entity.Workshop;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;

public class MysqlWorkshopDAO implements WorkshopDAO {
	
	private JdbcTemplate jdbcTemplate;

	public MysqlWorkshopDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Workshop> getAll() {
		String sql = "SELECT id, name, start, end, pricefull, pricestudent, pricefulllate, pricestudentlate FROM workshop";
		// return jdbcTemplate.query(sql, new RowMapper<Workshop>().getClass(); // nieco take by malo nahradit ten dlhy kod
		return jdbcTemplate.query(sql, new RowMapper<Workshop>() { // anonymna vnutorna trieda

			@Override
			public Workshop mapRow(ResultSet rs, int rowNum) throws SQLException {
				Workshop workshop = new Workshop();
				workshop.setId(rs.getLong("rs"));
				workshop.setName(rs.getString("name"));
				Timestamp timestamp = rs.getTimestamp("start");
				if (timestamp != null) {
					workshop.setStart(timestamp.toLocalDateTime().toLocalDate());
				}
				timestamp = rs.getTimestamp("end");
				if (timestamp != null) {
					workshop.setEnd(timestamp.toLocalDateTime().toLocalDate());
				}
				workshop.setPriceFull(rs.getDouble("pricefull")); // premenuj v DB pricefull na price_full, lebo vtedy setXXX vie, ze sa jedna o 2 slova
				workshop.setPriceStudent(rs.getDouble("pricestudent"));
				workshop.setPriceFullLate(rs.getDouble("pricefulllate"));
				workshop.setPriceStudentLate(rs.getDouble("pricestudentlate"));
				return workshop;
			}
		});
	}

	@Override
	public Workshop save(Workshop workshop) {
		if (workshop == null)
			return null;
		if (workshop.getId() == null) { // CREATE
			SimpleJdbcInsert sjinsert = new SimpleJdbcInsert(jdbcTemplate);
			sjinsert.withTableName("workshop");
			sjinsert.usingGeneratedKeyColumns("id"); // stringy vsetkych generovanych stlpcov
			sjinsert.usingColumns("name", "start", "end", "pricefull", "pricestudent", "pricefulllate", "pricestudentlate");
			Map<String, Object> hodnoty = new HashMap<>();
			hodnoty.put("name", workshop.getName());
			hodnoty.put("start", workshop.getStart());
			hodnoty.put("end", workshop.getEnd());
			hodnoty.put("pricefull", workshop.getPriceFull());
			hodnoty.put("pricestudent", workshop.getPriceStudent());
			hodnoty.put("pricefulllate", workshop.getPriceStudent());
			hodnoty.put("pricestudentlate", workshop.getPriceStudentLate());
			Long id = sjinsert.executeAndReturnKey(hodnoty).longValue(); // velky Long je objekt, v ktorom moze byt aj null, s malym by neslo workshop.getId == null
			workshop.setId(id);
		} else { // UPDATE
			String sql = "UPDATE workshop SET name = ?, start = ?, end = ?, pricefull = ?, pricestudent = ?, pricefulllate = ?, pricestudentlate = ? WHERE id = ?";
			jdbcTemplate.update(sql, workshop.getName(), workshop.getStart(), workshop.getEnd(), workshop.getPriceFull(),
					workshop.getPriceFullLate(), workshop.getPriceStudent(), workshop.getPriceStudentLate(), workshop.getId()); // vracia int, kolko riadkov updatol alebo null
		}
		return workshop;
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM workshop WHERE id = " + id; // mazeme riadky
		jdbcTemplate.update(sql);
	}

}
