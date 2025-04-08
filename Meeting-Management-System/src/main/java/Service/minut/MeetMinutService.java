package Service.minut;

import dto.minut.MeetMinutDTO;
import Entity.MeetMinut;
import Entity.MinutType;
import Entity.Member;
import Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.MeetMinutRepo;
import Repository.MinutTypeRepo;
import Repository.MemberRepo;
import Repository.UserRepo;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetMinutService {

    @Autowired
    private MeetMinutRepo meetMinutRepo;

    @Autowired
    private MinutTypeRepo minutTypeRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    // Method to get all MeetMinut records
    public List<MeetMinutDTO> getAllMeetMinuts() {
        List<MeetMinut> meetMinuts = meetMinutRepo.findAll();
        return meetMinuts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get MeetMinut by ID
    public MeetMinutDTO getMeetMinutById(Long id) {
        MeetMinut meetMinut = meetMinutRepo.findById(id).orElseThrow(() -> new RuntimeException("MeetMinut not found"));
        return convertToDTO(meetMinut);
    }

    // Create method
    public MeetMinutDTO createMeetMinut(MeetMinutDTO dto) {
        MinutType minutType = minutTypeRepo.findById(Long.valueOf(dto.getMintType()))
                .orElseThrow(() -> new RuntimeException("MinutType not found"));

        Member chairPerson = memberRepo.findById(Long.valueOf(dto.getChairPerson()))
                .orElseThrow(() -> new RuntimeException("Chair person not found"));

        User currentUser = userRepo.findById(1L) // Replace with actual user ID
                .orElseThrow(() -> new RuntimeException("User not found"));

        MeetMinut entity = new MeetMinut();
        entity.setMintType(minutType);
        entity.setFinYear(dto.getFinYear());
        entity.setMeetCount(dto.getMeetCount());
        entity.setMeetDate(dto.getMeetDate());
        entity.setTimeFrom(dto.getTimeFrom());
        entity.setTimeTo(dto.getTimeTo());
        entity.setMeetPlace(dto.getMeetPlace());
        entity.setChairPerson(chairPerson);
        entity.setPropositions(dto.getPropositions());
        entity.setDecisions(dto.getDecisions());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        entity.setRemarks(dto.getRemarks());
        entity.setInsertUser(currentUser);
        entity.setInsertDate(ZonedDateTime.now());

        entity = meetMinutRepo.save(entity);
        return convertToDTO(entity);
    }

    // Update method
    public MeetMinutDTO updateMeetMinut(Long id, MeetMinutDTO dto) {
        MeetMinut existingEntity = meetMinutRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("MeetMinut not found"));

        MinutType minutType = minutTypeRepo.findById(Long.valueOf(dto.getMintType()))
                .orElseThrow(() -> new RuntimeException("MinutType not found"));

        Member chairPerson = memberRepo.findById(Long.valueOf(dto.getChairPerson()))
                .orElseThrow(() -> new RuntimeException("Chair person not found"));

        existingEntity.setMintType(minutType);
        existingEntity.setFinYear(dto.getFinYear());
        existingEntity.setMeetCount(dto.getMeetCount());
        existingEntity.setMeetDate(dto.getMeetDate());
        existingEntity.setTimeFrom(dto.getTimeFrom());
        existingEntity.setTimeTo(dto.getTimeTo());
        existingEntity.setMeetPlace(dto.getMeetPlace());
        existingEntity.setChairPerson(chairPerson);
        existingEntity.setPropositions(dto.getPropositions());
        existingEntity.setDecisions(dto.getDecisions());
        existingEntity.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        existingEntity.setRemarks(dto.getRemarks());

        // If you want to update the edit user and date as well
        User currentUser = userRepo.findById(1L) // Replace with actual user ID
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingEntity.setEditUser(currentUser);
        existingEntity.setEditDate(ZonedDateTime.now());

        existingEntity = meetMinutRepo.save(existingEntity);
        return convertToDTO(existingEntity);
    }

    // Delete method
    public void deleteMeetMinut(Long id) {
        meetMinutRepo.deleteById(id);
    }

    // Convert Entity to DTO
    private MeetMinutDTO convertToDTO(MeetMinut meetMinut) {
        MeetMinutDTO meetMinutDTO = new MeetMinutDTO();
        meetMinutDTO.setId(meetMinut.getId());
        meetMinutDTO.setMintType(meetMinut.getMintType().getMinuteName()); // Changed getName() to getMinuteName()
        meetMinutDTO.setFinYear(meetMinut.getFinYear());
        meetMinutDTO.setMeetCount(meetMinut.getMeetCount());
        meetMinutDTO.setMeetDate(meetMinut.getMeetDate());
        meetMinutDTO.setTimeFrom(meetMinut.getTimeFrom());
        meetMinutDTO.setTimeTo(meetMinut.getTimeTo());
        meetMinutDTO.setMeetPlace(meetMinut.getMeetPlace());
        meetMinutDTO.setChairPerson(meetMinut.getChairPerson().getFullName()); // Changed getName() to getFullName()
        meetMinutDTO.setPropositions(meetMinut.getPropositions());
        meetMinutDTO.setDecisions(meetMinut.getDecisions());
        meetMinutDTO.setStatus(meetMinut.getStatus());
        meetMinutDTO.setRemarks(meetMinut.getRemarks());
        meetMinutDTO.setInsertUser(meetMinut.getInsertUser().getUserName()); // Changed getUsername() to getUserName()
        meetMinutDTO.setInsertDate(meetMinut.getInsertDate());
        meetMinutDTO.setEditUser(meetMinut.getEditUser() != null ? meetMinut.getEditUser().getUserName() : null); // Changed getUsername() to getUserName()
        meetMinutDTO.setEditDate(meetMinut.getEditDate());
        return meetMinutDTO;
    }
}
