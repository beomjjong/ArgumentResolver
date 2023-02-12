package portfolio.beom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.beom.domain.member.Member;

import java.util.Optional;

//todo @Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);
}
