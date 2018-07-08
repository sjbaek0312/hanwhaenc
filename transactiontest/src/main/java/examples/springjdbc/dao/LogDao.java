package examples.springjdbc.dao;

import examples.springjdbc.dto.Info;
import examples.springjdbc.dto.Log;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

import static examples.springjdbc.dao.LogDaoSqls.SELECT_BY_ID;

@Repository
public class LogDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Info> rowMapper = BeanPropertyRowMapper.newInstance(Info.class);

    public LogDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("log")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(Log log) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(log);
        return insertAction.execute(params);
    }

    public Info selectById(Integer id) {
        try {
            Map<String, ?> params = Collections.singletonMap("id", id);
            return jdbc.queryForObject(SELECT_BY_ID, params, rowMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}

