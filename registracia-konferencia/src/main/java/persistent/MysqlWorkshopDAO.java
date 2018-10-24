package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import entity.Workshop;
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
					workshop.setStart(timestamp.toLocalDateTime());
				}
				timestamp = rs.getTimestamp("end");
				if (timestamp != null) {
					workshop.setEnd(timestamp.toLocalDateTime());
				}
				workshop.setPriceFull(rs.getDouble("pricefull"));
				workshop.setPriceStudent(rs.getDouble("pricestudent"));
				workshop.setPriceFullLate(rs.getDouble("pricefulllate"));
				workshop.setPriceStudentLate(rs.getDouble("pricestudentlate"));
				return workshop;
			}
		});
	}

}
