package Service.minut;

import dto.minut.MinutTypeDTO;
import Entity.MinutType;
import Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.MinutTypeRepo;
import Repository.UserRepo;
import exception.ResourceNotFoundException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinutTypeService {

    @Autowired
    private MinutTypeRepo minutTypeRepo;

    @Autowired
    private UserRepo userRepo;

    // Get all minute types
    public List<MinutTypeDTO> getAllMinutTypes() {
        try {
            List<MinutType> minutTypes = minutTypeRepo.findAll();
            return minutTypes.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error retrieving minute types: " + e.getMessage());
        }
    }

    // Get by ID
    public MinutTypeDTO getMinutTypeById(Long id) {
        try {
            MinutType minutType = minutTypeRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Minute type not found with ID: " + id));
            return convertToDTO(minutType);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error retrieving minute type: " + e.getMessage());
        }
    }

    // Create
    public MinutTypeDTO createMinutType(MinutTypeDTO dto) {
        dto.validateForCreate();
        try {
            MinutType minutType = new MinutType();
            minutType.setMinuteName(dto.getMinuteName());
            minutType.setMituteTypeLocale(dto.getMituteTypeLocale());
            minutType.setDescription(dto.getDescription());
            minutType.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
            minutType.setRemarks(dto.getRemarks());
            minutType.setInsertDate(ZonedDateTime.now());

            // Replace with real user from auth context
            User currentUser = userRepo.findById(1L)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            minutType.setInsertUser(currentUser);

            minutType = minutTypeRepo.save(minutType);
            return convertToDTO(minutType);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error creating minute type: " + e.getMessage());
        }
    }

    // Update
    public MinutTypeDTO updateMinutType(Long id, MinutTypeDTO dto) {
        dto.validateForUpdate();
        try {
            MinutType minutType = minutTypeRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Minute type not found with ID: " + id));

            if (dto.getMinuteName() != null) minutType.setMinuteName(dto.getMinuteName());
            if (dto.getMituteTypeLocale() != null) minutType.setMituteTypeLocale(dto.getMituteTypeLocale());
            if (dto.getDescription() != null) minutType.setDescription(dto.getDescription());
            if (dto.getStatus() != null) minutType.setStatus(dto.getStatus());
            if (dto.getRemarks() != null) minutType.setRemarks(dto.getRemarks());

            minutType.setEditDate(ZonedDateTime.now());

            // Replace with actual user from security
            User currentUser = userRepo.findById(1L)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            minutType.setEditUser(currentUser);

            minutType = minutTypeRepo.save(minutType);
            return convertToDTO(minutType);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error updating minute type: " + e.getMessage());
        }
    }

    // Helper: convert Entity -> DTO
    private MinutTypeDTO convertToDTO(MinutType minutType) {
        MinutTypeDTO dto = new MinutTypeDTO();
        dto.setId(minutType.getId());
        dto.setMinuteName(minutType.getMinuteName());
        dto.setMituteTypeLocale(minutType.getMituteTypeLocale());
        dto.setDescription(minutType.getDescription());
        dto.setStatus(minutType.getStatus());
        dto.setRemarks(minutType.getRemarks());

        if (minutType.getInsertUser() != null)
            dto.setInsertUser(minutType.getInsertUser().getUserName());

        dto.setInsertDate(minutType.getInsertDate());

        if (minutType.getEditUser() != null)
            dto.setEditUser(minutType.getEditUser().getUserName());

        dto.setEditDate(minutType.getEditDate());

        return dto;
    }
}
