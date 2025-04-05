package Meeting_Management.System.Service.Office;


import Meeting_Management.System.ConvertUtil.ConvertUtilityOffice;
import Meeting_Management.System.Dto.OfficeDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Entity.BranchInfo;
import Meeting_Management.System.Entity.Office;
import Meeting_Management.System.Entity.User;
import Meeting_Management.System.Repository.BranchInfoRepo;
import Meeting_Management.System.Repository.OfficeRepo;
import Meeting_Management.System.Repository.UserRepo;
import Meeting_Management.System.Service.Impl.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class OfficeService implements IOfficeService {

    @Autowired
    private OfficeRepo officeRepository;

    @Autowired
    private BranchInfoRepo branchInfoRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    public ResponseDTO getAllOffices() {
        try {
            List<Office> offices = officeRepository.findAll();

            if (offices.isEmpty()) {
                System.out.println("No data found");
                return new ResponseDTO(
                        "success",
                        "204",
                        "No office information found",
                        Collections.emptyMap(),
                        null
                );
            }

            List<OfficeDTO> officeDTOs = ConvertUtilityOffice.convertListToOfficeDTOs(offices);
            Map<String, Object> details = new HashMap<>();
            details.put("offices", officeDTOs);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Office information retrieved successfully",
                    details,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    public ResponseDTO getOfficeById(Long id) {
        try {
            Optional<Office> optionalOffice = officeRepository.findById(Math.toIntExact(id));

            if (optionalOffice.isPresent()) {
                OfficeDTO officeDTO = ConvertUtilityOffice.convertToOfficeDTO(optionalOffice.get());
                Map<String, Object> detail = new HashMap<>();
                detail.put("office", officeDTO);

                return new ResponseDTO(
                        "success",
                        "200",
                        "Office information retrieved successfully",
                        null,
                        detail
                );
            } else {
                System.out.println("No office found with ID: " + id);
                return new ResponseDTO(
                        "error",
                        "404",
                        "No office found with ID: " + id,
                        null,
                        null
                );
            }
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    public ResponseDTO getOfficesByBranchId(Integer branchId) {
        try {
            List<Office> offices = officeRepository.findByBranchInfoId(branchId);

            if (offices.isEmpty()) {
                return new ResponseDTO(
                        "success",
                        "204",
                        "No offices found for branch ID: " + branchId,
                        Collections.emptyMap(),
                        null
                );
            }

            List<OfficeDTO> officeDTOs = ConvertUtilityOffice.convertListToOfficeDTOs(offices);
            Map<String, Object> details = new HashMap<>();
            details.put("offices", officeDTOs);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Offices retrieved successfully for branch ID: " + branchId,
                    details,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    @Transactional
    public ResponseDTO createOffice(OfficeDTO officeDTO) {
        try {
            // Validate branch info
            Optional<BranchInfo> branchInfoOptional = branchInfoRepository.findById(officeDTO.getBranchInfoId());
            if (branchInfoOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Branch info not found with ID: " + officeDTO.getBranchInfoId(),
                        null,
                        null
                );
            }

            // Validate insert user
            Optional<User> insertUserOptional = userRepository.findById(officeDTO.getInsertUserId());
            if (insertUserOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Insert user not found with ID: " + officeDTO.getInsertUserId(),
                        null,
                        null
                );
            }

            Office newOffice = ConvertUtilityOffice.convertToEntity(branchInfoOptional.get(),insertUserOptional.get(),officeDTO);
            Office savedOffice = officeRepository.save(newOffice);
            OfficeDTO savedOfficeDTO = ConvertUtilityOffice.convertToOfficeDTO(savedOffice);

            Map<String, Object> detail = new HashMap<>();
            detail.put("office", savedOfficeDTO);

            return new ResponseDTO(
                    "success",
                    "201",
                    "Office created successfully",
                    null,
                    detail
            );
        } catch (UnexpectedRollbackException | DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Exception: Possible duplicate entry or constraint violation.",
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during office creation: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during office creation: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    @Transactional
    public ResponseDTO updateOffice(Long id, OfficeDTO officeDTO) {
        try {
            Optional<Office> officeOptional = officeRepository.findById(Math.toIntExact(id));

            if (officeOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Office not found with id: " + id,
                        null,
                        null
                );
            }

            Office existingOffice = officeOptional.get();

            // Validate branch info if provided
            if (officeDTO.getBranchInfoId() != null) {
                Optional<BranchInfo> branchInfoOptional = branchInfoRepository.findById(officeDTO.getBranchInfoId());
                if (branchInfoOptional.isEmpty()) {
                    return new ResponseDTO(
                            "error",
                            "404",
                            "Branch info not found with ID: " + officeDTO.getBranchInfoId(),
                            null,
                            null
                    );
                }
                existingOffice.setBranchInfo(branchInfoOptional.get());
            }

            // Validate edit user if provided
            if (officeDTO.getEditUserId() != null) {
                Optional<User> editUserOptional = userRepository.findById(officeDTO.getEditUserId());
                if (editUserOptional.isEmpty()) {
                    return new ResponseDTO(
                            "error",
                            "404",
                            "Edit user not found with ID: " + officeDTO.getEditUserId(),
                            null,
                            null
                    );
                }
                existingOffice.setEditUser(editUserOptional.get());
            }

            // Update fields
            existingOffice.setOfficeName(officeDTO.getOfficeName());
            existingOffice.setOfficeLocale(officeDTO.getOfficeLocale());
            existingOffice.setDescription(officeDTO.getDescription());
            existingOffice.setStatus(officeDTO.getStatus());
            existingOffice.setRemarks(officeDTO.getRemarks());
            existingOffice.setEditDate(ZonedDateTime.now());

            Office updatedOffice = officeRepository.save(existingOffice);
            OfficeDTO updatedOfficeDTO = ConvertUtilityOffice.convertToOfficeDTO(updatedOffice);

            Map<String, Object> detail = new HashMap<>();
            detail.put("office", updatedOfficeDTO);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Office updated successfully",
                    null,
                    detail
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Data integrity violation during update: " + e.getMessage(),
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during office update: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during office update: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    @Transactional
    public ResponseDTO deleteOffice(Long id) {
        try {
            Optional<Office> officeOptional = officeRepository.findById(Math.toIntExact(id));

            if (officeOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Office not found with id: " + id,
                        null,
                        null
                );
            }

            Office office = officeOptional.get();
            officeRepository.delete(office);

            Map<String, Object> detail = new HashMap<>();
            detail.put("deletedId", id);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Office deleted successfully",
                    null,
                    detail
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Cannot delete this office as it is referenced by other entities: " + e.getMessage(),
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during office deletion: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during office deletion: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    public ResponseDTO getActiveOffices() {
        try {
            List<Office> offices = officeRepository.findByStatusTrue();

            if (offices.isEmpty()) {
                return new ResponseDTO(
                        "success",
                        "204",
                        "No active offices found",
                        Collections.emptyMap(),
                        null
                );
            }

            List<OfficeDTO> officeDTOs = ConvertUtilityOffice.convertListToOfficeDTOs(offices);
            Map<String, Object> details = new HashMap<>();
            details.put("offices", officeDTOs);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Active offices retrieved successfully",
                    details,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred: " + e.getMessage(),
                    null,
                    null
            );
        }
    }
}
