package idusw.leafton.model.service;


import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.DTO.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> getByMember(MemberDTO memberDTO);
}
