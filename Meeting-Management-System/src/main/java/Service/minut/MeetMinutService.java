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
import exception.ResourceNotFoundException;

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
        MeetMinut meetMinut = meetMinutRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting minute not found for ID: " + id));
        return convertToDTO(meetMinut);
    }

    // Create method
    public MeetMinutDTO createMeetMinut(MeetMinutDTO dto) {
        MinutType minutType = minutTypeRepo.findById(Long.valueOf(dto.getMintType()))
                .orElseThrow(() -> new ResourceNotFoundException("Minute type not found for ID: " + dto.getMintType()));

        Member chairPerson = memberRepo.findById(Long.valueOf(dto.getChairPerson()))
                .orElseThrow(() -> new ResourceNotFoundException("Chair person not found for ID: " + dto.getChairPerson()));

        User currentUser = userRepo.findById(1L) // Replace with actual user ID
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: 1"));

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
                .orElseThrow(() -> new ResourceNotFoundException("Meeting minute not found for ID: " + id));

        MinutType minutType = minutTypeRepo.findById(Long.valueOf(dto.getMintType()))
                .orElseThrow(() -> new ResourceNotFoundException("Minute type not found for ID: " + dto.getMintType()));

        Member chairPerson = memberRepo.findById(Long.valueOf(dto.getChairPerson()))
                .orElseThrow(() -> new ResourceNotFoundException("Chair person not found for ID: " + dto.getChairPerson()));

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
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: 1"));
        existingEntity.setEditUser(currentUser);
        existingEntity.setEditDate(ZonedDateTime.now());

        existingEntity = meetMinutRepo.save(existingEntity);
        return convertToDTO(existingEntity);
    }

    // Delete method
    public void deleteMeetMinut(Long id) {
        if (!meetMinutRepo.existsById(id)) {
            throw new ResourceNotFoundException("Meeting minute not found for ID: " + id);
        }
        meetMinutRepo.deleteById(id);
    }

    // Convert Entity to DTO
    private MeetMinutDTO convertToDTO(MeetMinut meetMinut) {
        MeetMinutDTO meetMinutDTO = new MeetMinutDTO();
        meetMinutDTO.setId(meetMinut.getId());
        meetMinutDTO.setMintType(meetMinut.getMintType().getMinuteName());
        meetMinutDTO.setFinYear(meetMinut.getFinYear());
        meetMinutDTO.setMeetCount(meetMinut.getMeetCount());
        meetMinutDTO.setMeetDate(meetMinut.getMeetDate());
        meetMinutDTO.setTimeFrom(meetMinut.getTimeFrom());
        meetMinutDTO.setTimeTo(meetMinut.getTimeTo());
        meetMinutDTO.setMeetPlace(meetMinut.getMeetPlace());
        meetMinutDTO.setChairPerson(meetMinut.getChairPerson().getFullName());
        meetMinutDTO.setPropositions(meetMinut.getPropositions());
        meetMinutDTO.setDecisions(meetMinut.getDecisions());
        meetMinutDTO.setStatus(meetMinut.getStatus());
        meetMinutDTO.setRemarks(meetMinut.getRemarks());
        meetMinutDTO.setInsertUser(meetMinut.getInsertUser() != null ? meetMinut.getInsertUser().getUserName() : null);
        meetMinutDTO.setInsertDate(meetMinut.getInsertDate());
        meetMinutDTO.setEditUser(meetMinut.getEditUser() != null ? meetMinut.getEditUser().getUserName() : null);
        meetMinutDTO.setEditDate(meetMinut.getEditDate());
        return meetMinutDTO;
    }
}