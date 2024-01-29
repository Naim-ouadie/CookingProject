package fr.bonitasoft.cookingproject.repository;

import fr.bonitasoft.cookingproject.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
