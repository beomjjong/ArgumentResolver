package portfolio.beom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.beom.domain.member.Member;

import java.util.Optional;

//todo @Repository
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    Optional<Member> findByEmail(String email);
}
