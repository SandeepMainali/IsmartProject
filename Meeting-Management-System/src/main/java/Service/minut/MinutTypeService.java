package Service.minut;

import dto.minut.MinutTypeDTO;
import Entity.MinutType;
import Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.MinutTypeRepo;
import Repository.UserRepo;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MinutTypeService {

    @Autowired
    private MinutTypeRepo minutTypeRepo;

    @Autowired
    private UserRepo userRepo;

    // Method to get all MinutType records
    public List<MinutTypeDTO> getAllMinutTypes() {
        List<MinutType> minutTypes = minutTypeRepo.findAll();
        return minutTypes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get MinutType by ID
    public MinutTypeDTO getMinutTypeById(Long id) {
        MinutType minutType = minutTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("MinutType not found"));
        return convertToDTO(minutType);
    }

    // Method to create a new MinutType
    public MinutTypeDTO createMinutType(MinutTypeDTO minutTypeDTO) {
        MinutType minutType = new MinutType();
        minutType.setMinuteName(minutTypeDTO.getMinuteName());
        minutType.setMituteTypeLocale(minutTypeDTO.getMituteTypeLocale());
        minutType.setDescription(minutTypeDTO.getDescription());
        minutType.setStatus(minutTypeDTO.getStatus() != null ? minutTypeDTO.getStatus() : true);
        minutType.setRemarks(minutTypeDTO.getRemarks());
        minutType.setInsertDate(ZonedDateTime.now());

        // Get current user - in a real app, you'd get this from security context
        User currentUser = userRepo.findById(1L) // Replace with actual user ID
                .orElseThrow(() -> new RuntimeException("User not found"));
        minutType.setInsertUser(currentUser);

        minutType = minutTypeRepo.save(minutType);
        return convertToDTO(minutType);
    }

    // Method to update an existing MinutType
    public MinutTypeDTO updateMinutType(Long id, MinutTypeDTO minutTypeDTO) {
        MinutType minutType = minutTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("MinutType not found"));
        minutType.setMinuteName(minutTypeDTO.getMinuteName());
        minutType.setMituteTypeLocale(minutTypeDTO.getMituteTypeLocale());
        minutType.setDescription(minutTypeDTO.getDescription());
        minutType.setStatus(minutTypeDTO.getStatus());
        minutType.setRemarks(minutTypeDTO.getRemarks());
        minutType.setEditDate(ZonedDateTime.now());
        minutType.setEditUser(null); // Assuming you'd set the logged-in user
        minutType = minutTypeRepo.save(minutType);
        return convertToDTO(minutType);
    }

    // Method to delete a MinutType by ID
    public void deleteMinutType(Long id) {
        MinutType minutType = minutTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("MinutType not found"));
        minutTypeRepo.delete(minutType);
    }

    // Convert Entity to DTO
    private MinutTypeDTO convertToDTO(MinutType minutType) {
        MinutTypeDTO minutTypeDTO = new MinutTypeDTO();
        minutTypeDTO.setId(minutType.getId());
        minutTypeDTO.setMinuteName(minutType.getMinuteName());
        minutTypeDTO.setMituteTypeLocale(minutType.getMituteTypeLocale());
        minutTypeDTO.setDescription(minutType.getDescription());
        minutTypeDTO.setStatus(minutType.getStatus());
        minutTypeDTO.setRemarks(minutType.getRemarks());
        minutTypeDTO.setInsertUser(minutType.getInsertUser() != null ? minutType.getInsertUser().getUserName() : null);
        minutTypeDTO.setInsertDate(minutType.getInsertDate());
        minutTypeDTO.setEditUser(minutType.getEditUser() != null ? minutType.getEditUser().getUserName() : null);
        minutTypeDTO.setEditDate(minutType.getEditDate());
        return minutTypeDTO;
    }
}