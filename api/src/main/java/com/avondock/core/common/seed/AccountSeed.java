package com.avondock.core.common.seed;

import com.avondock.app.system.iam.auth.model.Account;
import com.avondock.app.system.iam.auth.model.AccountId;
import com.avondock.app.system.iam.auth.repository.AccountRepository;
import com.avondock.core.shared.domain.GuestUUID;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AccountSeed {

    public static final String A1_UUID = "42552bde-e82a-4bc7-b3c3-fc8e434c94e7";
    public static final String A2_UUID = "d6da1b2d-92a0-4a90-90e9-986b7eefd788";
    public static final String PWD = "tomtom";

    private final AccountRepository accountRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountSeed(AccountRepository accountRepository, JdbcTemplate jdbcTemplate) {
        this.accountRepository = accountRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void seed() {
        seedAccount(new GuestUUID().getId(), "guest");
        seedAccount(A1_UUID, "admin");
        seedAccount(A2_UUID, "tom");
    }

    private void seedAccount(String uuid, String username) {
        String        sql = "SELECT * FROM `system`.account WHERE account_id = '" + uuid + "'";
        List<Account> rs  = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        Faker faker = new Faker();
        String fname = faker.name().firstName();
        String lname = faker.name().lastName();
        if (rs.size() <= 0) {
            Account account = new Account(
                    new AccountId(uuid),
                    fname,
                    lname,
                    username,
                    PWD
            );
            accountRepository.save(account);
            log.info("Account seeded with uuid: " + uuid);
        }
    }
}
