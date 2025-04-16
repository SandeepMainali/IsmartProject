package Meeting_Management.System.Service.BranchInfo;

import Meeting_Management.System.ConvertUtil.ConvertUtilityBranchInfo;
import Meeting_Management.System.Dto.BranchInfoDTO;
import Meeting_Management.System.Dto.BranchInfoOutDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Entity.BranchInfo;
import Meeting_Management.System.Filter.JWTFilter;
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

@Service("child")
public class BranchInfoChildService implements IBranchInfoService {

    @Autowired
    private BranchInfoRepo branchInfoRepository;

    @Override
    public ResponseDTO getAllBranchInfos() {
        try {
            List<BranchInfo> childBranches = branchInfoRepository.findByParentIdIsNotNullOrderByParentIdId();

            if (childBranches.isEmpty()) {
                System.out.println("No child branches found");
                return new ResponseDTO(
                        "success",
                        "204",
                        "No child branches found",
                        Collections.emptyMap(),
                        null
                );
            }

            List<BranchInfoOutDTO> branchInfoDTOs = ConvertUtilityBranchInfo.ConvertListBranchInfoResponseDTO(childBranches);

            Map<String, Object> details = new HashMap<>();
            details.put("childBranches", branchInfoDTOs);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Child branches retrieved successfully",
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

            if (optionalBranchInfo.isPresent() && optionalBranchInfo.get().getParentId() != null) {
                BranchInfoOutDTO branchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoOutDTO(optionalBranchInfo.get());
                Map<String, Object> detail = new HashMap<>();
                detail.put("childBranch", branchInfoDTO);

                return new ResponseDTO(
                        "success",
                        "200",
                        "Child branch information retrieved successfully",
                        null,
                        detail
                );
            } else {
                return new ResponseDTO(
                        "error",
                        "404",
                        "No child branch found with ID: " + id,
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

            if (optionalBranchInfo.isPresent() && optionalBranchInfo.get().getParentId() != null) {
                BranchInfoOutDTO branchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoOutDTO(optionalBranchInfo.get());
                Map<String, Object> detail = new HashMap<>();
                detail.put("childBranch", branchInfoDTO);

                return new ResponseDTO(
                        "success",
                        "200",
                        "Child branch information retrieved successfully",
                        null,
                        detail
                );
            } else {
                return new ResponseDTO(
                        "error",
                        "404",
                        "No child branch found with Alias: " + alias,
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
            // Ensure this has a valid parent
            if (branchInfoDTO.getParentId() == null) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Child branch must have a parent branch ID",
                        null,
                        null
                );
            }

            Optional<BranchInfo> parentBranchInfo = branchInfoRepository.findById(branchInfoDTO.getParentId());
            if (parentBranchInfo.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Parent branch not found with ID: " + branchInfoDTO.getParentId(),
                        null,
                        null
                );
            }

            BranchInfo parent = parentBranchInfo.get();

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

            branchInfoDTO.setInsertUser(JWTFilter.getCurrentUserId());
            BranchInfo branchInfo = ConvertUtilityBranchInfo.convertToBranchInfoEntity(branchInfoDTO, parent);
            branchInfo.setInsertDate(ZonedDateTime.now());
            branchInfo.setStatus(true);
            branchInfo.setEditUser(null);
            branchInfo.setEditDate(null);
            BranchInfo savedBranchInfo = branchInfoRepository.save(branchInfo);
            BranchInfoOutDTO savedBranchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoOutDTO(savedBranchInfo);

            Map<String, Object> detail = new HashMap<>();
            detail.put("childBranch", savedBranchInfoDTO);

            return new ResponseDTO(
                    "success",
                    "201",
                    "Child branch created successfully",
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
                    "Database error during child branch creation: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during child branch creation: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Transactional
    @Override
    public ResponseDTO updateBranchInfo(Integer id, BranchInfoDTO branchInfoDTO) {
        try {
            Optional<BranchInfo> branchInfoOptional = branchInfoRepository.findById(id);

            if (branchInfoOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Child branch not found with id: " + id,
                        null,
                        null
                );
            }

            BranchInfo existingBranchInfo = branchInfoOptional.get();

            branchInfoDTO.setParentId(existingBranchInfo.getParentId().getId());

            // Update the existing entity with values from DTO
            BranchInfo updatedBranchInfo = ConvertUtilityBranchInfo.updateBranchInfoFields(existingBranchInfo, branchInfoDTO);

            // Set update timestamp
            updatedBranchInfo.setEditDate(ZonedDateTime.now());
            updatedBranchInfo.setEditUser(JWTFilter.getCurrentUserId());

            // Save the updated entity
            BranchInfo savedBranchInfo = branchInfoRepository.save(updatedBranchInfo);
            BranchInfoOutDTO updatedBranchInfoDTO = ConvertUtilityBranchInfo.convertToBranchInfoOutDTO(savedBranchInfo);

            Map<String, Object> detail = new HashMap<>();
            detail.put("parentBranch", updatedBranchInfoDTO);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Child branch updated successfully",
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
                    "Database error during Child branch update: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during Child branch update: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    @Transactional
    public ResponseDTO deleteBranchInfo(Integer id) {
        String delete;
        try {
            Optional<BranchInfo> branchInfoOptional = branchInfoRepository.findById(id);

            if (branchInfoOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "branch not found with id: " + id,
                        null,
                        null
                );
            }

            BranchInfo childBranch = branchInfoOptional.get();

            delete = childBranch.getFullName();
            branchInfoRepository.delete(childBranch);

            Map<String, Object> detail = new HashMap<>();
            detail.put("deletedBranch", delete);

            return new ResponseDTO(
                    "success",
                    "200",
                    "the branch deleted successfully",
                    null,
                    detail
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Cannot delete this parent branch as it is referenced by other entities: " + e.getMessage(),
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during parent branch deletion: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during parent branch deletion: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Override
    public ResponseDTO getBranchInfosByParentId(Integer parentId) {
        try {
            Optional<BranchInfo> parentBranchOptional = branchInfoRepository.findById(parentId);

            if (parentBranchOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Parent branch not found with ID: " + parentId,
                        null,
                        null
                );
            }

            BranchInfo parentBranch = parentBranchOptional.get();
            List<BranchInfo> childBranches = branchInfoRepository.findByParentId(parentBranch);

            if (childBranches.isEmpty()) {
                return new ResponseDTO(
                        "success",
                        "204",
                        "No child branches found for parent ID: " + parentId,
                        Collections.emptyMap(),
                        null
                );
            }

            List<BranchInfoOutDTO> branchInfoDTOs = ConvertUtilityBranchInfo.ConvertListBranchInfoResponseDTO(childBranches);

            Map<String, Object> details = new HashMap<>();
            details.put("childBranches", branchInfoDTOs);
            details.put("parentId", parentId);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Child branches retrieved successfully for parent ID: " + parentId,
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
    public ResponseDTO getActiveBranchInfos() {
        return null;
    }

    @Override
    public ResponseDTO getBranchInfosByCity(String city) {
        return null;
    }

    @Override
    public ResponseDTO getBranchInfosByProvince(String province) {
        return null;
    }

}
