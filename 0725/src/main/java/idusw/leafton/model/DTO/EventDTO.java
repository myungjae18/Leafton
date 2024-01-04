package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.Event;
import idusw.leafton.model.entity.Image;
import idusw.leafton.model.entity.Product;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Long eventId;

    private ProductDTO productDTO;

    private LocalDateTime registDate;

    private String content;

    private LocalDateTime endDate;

    private String mainImage;

    private String subImage;

    private String thumbImage;

    public static EventDTO toEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setEventId(event.getEventId());
        eventDTO.setProductDTO(ProductDTO.toProductDTO(event.getProduct()));
        eventDTO.setRegistDate(event.getRegistDate());
        eventDTO.setContent(event.getContent());
        eventDTO.setEndDate(event.getEndDate());
        eventDTO.setMainImage(event.getMainImage());
        eventDTO.setSubImage(event.getSubImage());
        eventDTO.setThumbImage(event.getThumbImage());

        return eventDTO;
    }
}
