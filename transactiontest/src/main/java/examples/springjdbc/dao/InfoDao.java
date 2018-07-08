package examples.springjdbc.dao;

import static examples.springjdbc.dao.InfoDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import examples.springjdbc.dto.Info;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class InfoDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Info> rowMapper = BeanPropertyRowMapper.newInstance(Info.class);

    public InfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("info")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(Info info) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(info);
        Number number = insertAction.executeAndReturnKey(params);
        return number.intValue();
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

