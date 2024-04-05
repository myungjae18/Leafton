package idusw.leafton.model.repository;

import idusw.leafton.model.entity.Member;
import idusw.leafton.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByMember(Member member);
}
