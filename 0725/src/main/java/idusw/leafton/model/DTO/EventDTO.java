package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Long eventId;

    private LocalDateTime registDate;

    private String title;

    private String content;

    private LocalDateTime endDate;

    private Integer salePercentage;

    private String mainImage;

    private String subImage;

    private String thumbImage;

    public static EventDTO toEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setEventId(event.getEventId());
        eventDTO.setRegistDate(event.getRegistDate());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setContent(event.getContent());
        eventDTO.setEndDate(event.getEndDate());
        eventDTO.setSalePercentage(event.getSalePercentage());
        eventDTO.setMainImage(event.getMainImage());
        eventDTO.setSubImage(event.getSubImage());
        eventDTO.setThumbImage(event.getThumbImage());

        return eventDTO;
    }
}
