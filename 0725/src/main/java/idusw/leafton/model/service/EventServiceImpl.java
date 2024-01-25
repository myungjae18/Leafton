package idusw.leafton.model.service;

import idusw.leafton.model.DTO.EventDTO;
import idusw.leafton.model.entity.Event;
import idusw.leafton.model.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    //event를 list로 모두 가져오는 메서드
    @Override
    public List<EventDTO> getAll(){
        List<Event> eventList = eventRepository.findAll();
        List<EventDTO> eventDTOList = new ArrayList<>();

        for(Event event : eventList) {
            eventDTOList.add(EventDTO.toEventDTO(event));
        }

        return eventDTOList;
    }
}
