package Meeting_Management.System.Service.BranchInfo;

import Meeting_Management.System.ConvertUtil.ConvertUtilityBranchInfo;
import Meeting_Management.System.Dto.BranchInfoDTO;

import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Entity.BranchInfo;
import Meeting_Management.System.Repository.BranchInfoRepo;
import Meeting_Management.System.Service.Impl.IBranchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class BranchInfoService implements IBranchInfoService {

    @Autowired
    BranchInfoRepo branchInfoRepository;



    @Override
    public ResponseDTO getAllBranchInfos() {
        try {
            List<BranchInfo> branchInfos = branchInfoRepository.findAll();

            if (branchInfos.isEmpty()) {
                System.out.println("No data found");
                return new ResponseDTO(
                        "success",
                        "204",
                        "No branch information found",
                        Collections.emptyMap(),
                        null
                );
            }

            List<BranchInfoDTO> branchInfoDTOs = ConvertUtilityBranchInfo.ConvertListBranchInfoDTO(branchInfos);
            Map<String, Object> details = new HashMap<>();
            details.put("branchInfos", branchInfoDTOs);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Branch information retrieved successfully",
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
    public ResponseDTO getBranchInfoById(Integer id) {
        try {
            Optional<BranchInfo> optionalBranchInfo = branchInfoRepository.findById(id);

            if (optionalBranchInfo.isPresent()) {
                BranchInfoDTO branchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoDTO(optionalBranchInfo.get());
                Map<String, Object> detail = new HashMap<>();
                detail.put("branchInfo", branchInfoDTO);

                return new ResponseDTO(
                        "success",
                        "200",
                        "Branch information retrieved successfully",
                        null,
                        detail
                );
            } else {
                System.out.println("No branch found with ID: " + id);
                return new ResponseDTO(
                        "error",
                        "404",
                        "No branch found with ID: " + id,
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
    public ResponseDTO getBranchInfoByAlias(String alias) {
        try {
            Optional<BranchInfo> optionalBranchInfo = branchInfoRepository.findBybrAlias(alias);

            if (optionalBranchInfo.isPresent()) {
                BranchInfoDTO branchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoDTO(optionalBranchInfo.get());
                Map<String, Object> detail = new HashMap<>();
                detail.put("branchInfo", branchInfoDTO);

                return new ResponseDTO(
                        "success",
                        "200",
                        "Branch information retrieved successfully",
                        null,
                        detail
                );
            } else {
                System.out.println("No branch found with this Alias: " + alias);
                return new ResponseDTO(
                        "error",
                        "404",
                        "No branch found with Alias: " + alias,
                        null,
                        null
                );
            }
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error while searching by alias: " + e.getMessage(),
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

    @Transactional
    @Override
    public ResponseDTO createBranchInfo(BranchInfoDTO branchInfoDTO) {
        try {
            BranchInfo parent = null;
            if(branchInfoDTO.getParentId() != null){
                Optional<BranchInfo> parentBranchInfo = branchInfoRepository.findById(branchInfoDTO.getParentId());
                if (parentBranchInfo.isPresent()) {
                    parent = parentBranchInfo.get();
                } else {
                    System.out.println("No such parent branch found");
                    return new ResponseDTO(
                            "error",
                            "404",
                            "Parent branch not found with ID: " + branchInfoDTO.getParentId(),
                            null,
                            null
                    );
                }
            }

            // Check for duplicate brAlias
            if (branchInfoDTO.getBrAlias() != null &&
                    branchInfoRepository.findBybrAlias(branchInfoDTO.getBrAlias()).isPresent()) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Branch with alias '" + branchInfoDTO.getBrAlias() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate fullName
            if (branchInfoDTO.getFullName() != null &&
                    branchInfoRepository.existsByFullName(branchInfoDTO.getFullName())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Branch with full name '" + branchInfoDTO.getFullName() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate fullNameLocale
            if (branchInfoDTO.getFullNameLocale() != null &&
                    branchInfoRepository.existsByFullNameLocale(branchInfoDTO.getFullNameLocale())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Branch with locale full name '" + branchInfoDTO.getFullNameLocale() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate shortName
            if (branchInfoDTO.getShortName() != null &&
                    branchInfoRepository.existsByShortName(branchInfoDTO.getShortName())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Branch with short name '" + branchInfoDTO.getShortName() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate regNo if provided
            if (branchInfoDTO.getRegNo() != null &&
                    branchInfoRepository.existsByRegNo(branchInfoDTO.getRegNo())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Branch with registration number '" + branchInfoDTO.getRegNo() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate PAN if provided
            if (branchInfoDTO.getPan() != null &&
                    branchInfoRepository.existsByPan(branchInfoDTO.getPan())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Branch with PAN '" + branchInfoDTO.getPan() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate email if provided
            if (branchInfoDTO.getEmail() != null &&
                    branchInfoRepository.existsByEmail(branchInfoDTO.getEmail())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Branch with email '" + branchInfoDTO.getEmail() + "' already exists",
                        null,
                        null
                );
            }

            BranchInfo branchInfo = ConvertUtilityBranchInfo.convertToBranchInfoEntity(branchInfoDTO, parent);
            branchInfo.setInsertDate(ZonedDateTime.now());
            branchInfo.setStatus(true);

            BranchInfo savedBranchInfo = branchInfoRepository.save(branchInfo);
            BranchInfoDTO savedBranchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoDTO(savedBranchInfo);

            Map<String, Object> detail = new HashMap<>();
            detail.put("branchInfo", savedBranchInfoDTO);

            return new ResponseDTO(
                    "success",
                    "201",
                    "Branch created successfully",
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
        }
        catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during branch creation: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during branch creation: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    @Transactional
    public ResponseDTO updateBranchInfo(Integer id, BranchInfoDTO branchInfoDTO) {
        try {
            Optional<BranchInfo> branchInfoOptional = branchInfoRepository.findById(id);

            if (branchInfoOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Branch info not found with id: " + id,
                        null,
                        null
                );
            }

            BranchInfo existingBranchInfo = branchInfoOptional.get();

            BranchInfo parent = null;
            if(branchInfoDTO.getParentId() != null){
                Optional<BranchInfo> parentBranchInfo = branchInfoRepository.findById(branchInfoDTO.getParentId());
                if (parentBranchInfo.isPresent()) {
                    parent = parentBranchInfo.get();
                } else {
                    System.out.println("No such parent branch found");
                    return new ResponseDTO(
                            "error",
                            "404",
                            "Parent branch not found with ID: " + branchInfoDTO.getParentId(),
                            null,
                            null
                    );
                }
            }

            // Update the existing entity with values from DTO
            BranchInfo updatedBranchInfo = ConvertUtilityBranchInfo.updateBranchInfoFields(existingBranchInfo, branchInfoDTO, parent);

            // Set update timestamp
            updatedBranchInfo.setEditDate(ZonedDateTime.now());

            // Save the updated entity
            BranchInfo savedBranchInfo = branchInfoRepository.save(updatedBranchInfo);
            BranchInfoDTO updatedBranchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoDTO(savedBranchInfo);

            Map<String, Object> detail = new HashMap<>();
            detail.put("branchInfo", updatedBranchInfoDTO);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Branch updated successfully",
                    null,
                    detail
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Data integrity violation during update: " + e.getMessage() + ". Possible duplicate entry or constraint violation.",
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during branch update: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during branch update: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    @Transactional
    public ResponseDTO deleteBranchInfo(Integer id) {
        try {
            Optional<BranchInfo> branchInfoOptional = branchInfoRepository.findById(id);

            if (branchInfoOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Branch info not found with id: " + id,
                        null,
                        null
                );
            }

            BranchInfo branchInfo = branchInfoOptional.get();
            branchInfoRepository.delete(branchInfo);

            Map<String, Object> detail = new HashMap<>();
            detail.put("deletedId", id);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Branch deleted successfully",
                    null,
                    detail
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Cannot delete this branch as it is referenced by other entities: " + e.getMessage(),
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during branch deletion: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during branch deletion: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    public ResponseDTO getBranchInfosByParentId(Integer parentId) {
        // Implementation for getting branches by parent ID
        return new ResponseDTO(
                "success",
                "200",
                "Method not yet implemented",
                null,
                null
        );
    }

    @Override
    public ResponseDTO getActiveBranchInfos() {
        // Implementation for getting active branches
        return new ResponseDTO(
                "success",
                "200",
                "Method not yet implemented",
                null,
                null
        );
    }

    @Override
    public ResponseDTO getBranchInfosByCity(String city) {
        // Implementation for getting branches by city
        return new ResponseDTO(
                "success",
                "200",
                "Method not yet implemented",
                null,
                null
        );
    }

    @Override
    public ResponseDTO getBranchInfosByProvince(String province) {
        // Implementation for getting branches by province
        return new ResponseDTO(
                "success",
                "200",
                "Method not yet implemented",
                null,
                null
        );
    }
}
