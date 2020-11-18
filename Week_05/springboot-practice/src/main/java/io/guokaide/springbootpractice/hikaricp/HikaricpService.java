package io.guokaide.springbootpractice.hikaricp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
@Slf4j
public class HikaricpService {

    private final JdbcTemplate jdbcTemplate;

    public HikaricpService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        String createString = "CREATE table COFFEES " +
                "(COF_NAME varchar(32) NOT NULL, " +
                "SUP_ID int NOT NULL, " +
                "PRICE numeric(10,2) NOT NULL, " +
                "SALES integer NOT NULL, " +
                "TOTAL integer NOT NULL, " +
                "PRIMARY KEY (COF_NAME))";
        jdbcTemplate.execute(createString);
    }

    public void insertData() {
        jdbcTemplate.batchUpdate(
                "INSERT INTO COFFEES VALUES ('x', 1, 1.00, 1, 1)",
                "INSERT INTO COFFEES VALUES ('y', 2, 2.00, 2, 2)",
                "INSERT INTO COFFEES VALUES ('z', 3, 3.00, 3, 3)",
                "INSERT INTO COFFEES VALUES ('w', 4, 4.00, 4, 4)"
                );
    }

    @Transactional
    public void updateData() {
        jdbcTemplate.execute("UPDATE COFFEES SET PRICE = 2.0 WHERE COF_NAME = 'x'");
        // rollback
        jdbcTemplate.execute("INSERT INTO COFFEES VALUES ('y', 2, 2.00, 2, 2)");
        jdbcTemplate.batchUpdate("DELETE FROM COFFEES WHERE COF_NAME = 'z' OR COF_NAME = 'w'");
    }

    public void showData() {
        jdbcTemplate.queryForList("SELECT * FROM COFFEES")
                .forEach(row -> log.info(row.toString()));
    }

}
