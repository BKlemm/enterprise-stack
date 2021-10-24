package com.avondock.app.system.iam.auth.repository;

import com.avondock.app.system.iam.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findOneByUserName(String userName);
}
