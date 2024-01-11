package idusw.leafton.model.service;

import idusw.leafton.model.DTO.StyleDTO;
import idusw.leafton.model.entity.Style;

import idusw.leafton.model.repository.StyleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;
    
    //pk를 이용하여 한 건 조회 후 DTO로 리턴
    public StyleDTO findById(Long styleId) {
        Optional<Style> opStyle = styleRepository.findById(styleId);
        if(opStyle.isPresent()) {
           Style style = opStyle.get();
           return StyleDTO.toStyleDTO(style);
        } else return null;
    }
}
