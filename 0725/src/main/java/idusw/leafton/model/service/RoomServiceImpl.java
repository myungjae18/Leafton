package idusw.leafton.model.service;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.DTO.RoomDTO;
import idusw.leafton.model.entity.Member;
import idusw.leafton.model.entity.Room;
import idusw.leafton.model.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public List<RoomDTO> getByMember(MemberDTO memberDTO) {
        Member member = Member.toMemberEntity(memberDTO);
        List<Room> roomList = roomRepository.findAllByMember(member);
        List<RoomDTO> result = new ArrayList<>();

        for(Room room : roomList) {
            result.add(RoomDTO.toRoomDTO(room));
        }

        return result;
    }
}
