package idusw.leafton.model.repository;

import idusw.leafton.model.entity.Event;
import idusw.leafton.model.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Override
    List<Event> findAll();

    Optional<Event> findById(Long eventId);

    @Override
    <S extends Event> S save(S event);
}