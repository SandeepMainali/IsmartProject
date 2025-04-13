package Meeting_Management.System.ConvertUtil;

import Meeting_Management.System.Dto.BranchInfoDTO;

import Meeting_Management.System.Dto.BranchInfoOutDTO;
import Meeting_Management.System.Dto.HierarchyBranch.BranchHierarchyDTO;
import Meeting_Management.System.Dto.HierarchyBranch.BranchInfoChildDTO;
import Meeting_Management.System.Dto.HierarchyBranch.BranchInfoParentDTO;
import Meeting_Management.System.Entity.BranchInfo;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConvertUtilityBranchInfo {
    public static BranchInfoDTO convertToBranchInfoDTO(BranchInfo branchInfo) {
        BranchInfoDTO dto = new BranchInfoDTO();
        dto.setId(branchInfo.getId());
        dto.setIsCorporate(branchInfo.getIsCorporate());
        dto.setParentId(branchInfo.getParentId() != null ? branchInfo.getParentId().getId() : null);
        dto.setBrAlias(branchInfo.getBrAlias());
        dto.setFullName(branchInfo.getFullName());
        dto.setFullNameLocale(branchInfo.getFullNameLocale());
        dto.setShortName(branchInfo.getShortName());
        dto.setStreetName(branchInfo.getStreetName());
        dto.setStreetNameLocale(branchInfo.getStreetNameLocale());
        dto.setWardNo(branchInfo.getWardNo());
        dto.setWardNoLocale(branchInfo.getWardNoLocale());
        dto.setLocalMnc(branchInfo.getLocalMnc());
        dto.setLocalMncLocale(branchInfo.getLocalMncLocale());
        dto.setCity(branchInfo.getCity());
        dto.setCityLocale(branchInfo.getCityLocale());
        dto.setDistrict(branchInfo.getDistrict());
        dto.setDistrictLocale(branchInfo.getDistrictLocale());
        dto.setBrZone(branchInfo.getBrZone());
        dto.setBrZoneLocale(branchInfo.getBrZoneLocale());
        dto.setProvince(branchInfo.getProvince());
        dto.setProvinceLocale(branchInfo.getProvinceLocale());
        dto.setCountry(branchInfo.getCountry());
        dto.setCountryLocale(branchInfo.getCountryLocale());
        dto.setZipCode(branchInfo.getZipCode());
        dto.setDefCurId(branchInfo.getDefCurId());
        dto.setRegNo(branchInfo.getRegNo());
        dto.setRegNoLocale(branchInfo.getRegNoLocale());
        dto.setRegDate(branchInfo.getRegDate());
        dto.setPan(branchInfo.getPan());
        dto.setPanLocale(branchInfo.getPanLocale());
        dto.setPhone(branchInfo.getPhone());
        dto.setPhoneLocale(branchInfo.getPhoneLocale());
        dto.setEmail(branchInfo.getEmail());
        dto.setContactPerson(branchInfo.getContactPerson());
        dto.setFax(branchInfo.getFax());
        dto.setUrl(branchInfo.getUrl());
        dto.setTimeZone(branchInfo.getTimeZone());
        dto.setShowLocaleDate(branchInfo.getShowLocaleDate());
        dto.setBrKey(branchInfo.getBrKey());
        dto.setStatus(branchInfo.getStatus());
        dto.setRemarks(branchInfo.getRemarks());
        dto.setInsertDate(branchInfo.getInsertDate());
        dto.setInsertUser(branchInfo.getInsertUser());
        dto.setEditUser(branchInfo.getEditUser());
        dto.setEditDate(branchInfo.getEditDate());
        return dto;
    }

    public static BranchInfo convertToBranchInfoEntity(BranchInfoDTO dto, BranchInfo parentBranchInfo) {
        BranchInfo entity = new BranchInfo();

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }

        entity.setIsCorporate(dto.getIsCorporate());

        entity.setParentId(parentBranchInfo);

        entity.setBrAlias(dto.getBrAlias());
        entity.setFullName(dto.getFullName());
        entity.setFullNameLocale(dto.getFullNameLocale());
        entity.setShortName(dto.getShortName());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNameLocale(dto.getStreetNameLocale());
        entity.setWardNo(dto.getWardNo());
        entity.setWardNoLocale(dto.getWardNoLocale());
        entity.setLocalMnc(dto.getLocalMnc());
        entity.setLocalMncLocale(dto.getLocalMncLocale());
        entity.setCity(dto.getCity());
        entity.setCityLocale(dto.getCityLocale());
        entity.setDistrict(dto.getDistrict());
        entity.setDistrictLocale(dto.getDistrictLocale());
        entity.setBrZone(dto.getBrZone());
        entity.setBrZoneLocale(dto.getBrZoneLocale());
        entity.setProvince(dto.getProvince());
        entity.setProvinceLocale(dto.getProvinceLocale());
        entity.setCountry(dto.getCountry());
        entity.setCountryLocale(dto.getCountryLocale());
        entity.setZipCode(dto.getZipCode());
        entity.setDefCurId(dto.getDefCurId());
        entity.setRegNo(dto.getRegNo());
        entity.setRegNoLocale(dto.getRegNoLocale());
        entity.setRegDate(dto.getRegDate());
        entity.setPan(dto.getPan());
        entity.setPanLocale(dto.getPanLocale());
        entity.setPhone(dto.getPhone());
        entity.setPhoneLocale(dto.getPhoneLocale());
        entity.setEmail(dto.getEmail());
        entity.setContactPerson(dto.getContactPerson());
        entity.setFax(dto.getFax());
        entity.setUrl(dto.getUrl());
        entity.setTimeZone(dto.getTimeZone());
        entity.setShowLocaleDate(dto.getShowLocaleDate());
        entity.setBrKey(dto.getBrKey());
        entity.setStatus(dto.getStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setInsertUser(dto.getInsertUser());
        entity.setEditUser(dto.getEditUser());

        return entity;
    }

    public static List<BranchInfoDTO> ConvertListBranchInfoDTO(List<BranchInfo> branchInfoList) {
        return branchInfoList.stream()
                .map(ConvertUtilityBranchInfo::convertToBranchInfoDTO)
                .collect(Collectors.toList());
    }

    public static BranchInfo updateBranchInfoFields(BranchInfo entity, BranchInfoDTO dto, BranchInfo parent) {
        entity.setIsCorporate(dto.getIsCorporate());
        entity.setParentId(parent);
        entity.setBrAlias(dto.getBrAlias());
        entity.setFullName(dto.getFullName());
        entity.setFullNameLocale(dto.getFullNameLocale());
        entity.setShortName(dto.getShortName());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNameLocale(dto.getStreetNameLocale());
        entity.setWardNo(dto.getWardNo());
        entity.setWardNoLocale(dto.getWardNoLocale());
        entity.setLocalMnc(dto.getLocalMnc());
        entity.setLocalMncLocale(dto.getLocalMncLocale());
        entity.setCity(dto.getCity());
        entity.setCityLocale(dto.getCityLocale());
        entity.setDistrict(dto.getDistrict());
        entity.setDistrictLocale(dto.getDistrictLocale());
        entity.setBrZone(dto.getBrZone());
        entity.setBrZoneLocale(dto.getBrZoneLocale());
        entity.setProvince(dto.getProvince());
        entity.setProvinceLocale(dto.getProvinceLocale());
        entity.setCountry(dto.getCountry());
        entity.setCountryLocale(dto.getCountryLocale());
        entity.setZipCode(dto.getZipCode());
        entity.setDefCurId(dto.getDefCurId());
        entity.setRegNo(dto.getRegNo());
        entity.setRegNoLocale(dto.getRegNoLocale());
        entity.setRegDate(dto.getRegDate());
        entity.setPan(dto.getPan());
        entity.setPanLocale(dto.getPanLocale());
        entity.setPhone(dto.getPhone());
        entity.setPhoneLocale(dto.getPhoneLocale());
        entity.setEmail(dto.getEmail());
        entity.setContactPerson(dto.getContactPerson());
        entity.setFax(dto.getFax());
        entity.setUrl(dto.getUrl());
        entity.setTimeZone(dto.getTimeZone());
        entity.setShowLocaleDate(dto.getShowLocaleDate());
        entity.setBrKey(dto.getBrKey());
        entity.setStatus(dto.getStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setEditUser(dto.getEditUser());
        return entity;
    }

    public static List<BranchHierarchyDTO> createHierarchicalStructure(List<BranchInfoDTO> branchInfoDTOs) {
        List<BranchHierarchyDTO> result = new ArrayList<>();

        // Find parent branches (those with parentId = null)
        List<BranchInfoDTO> parentBranches = branchInfoDTOs.stream()
                .filter(branch -> branch.getParentId() == null)
                .toList();

        // For each parent branch
        for (BranchInfoDTO parentDTO : parentBranches) {
            // Create parent DTO
            BranchInfoParentDTO parent = convertToParentDTO(parentDTO);

            // Find all children of this parent
            List<BranchInfoChildDTO> children = branchInfoDTOs.stream()
                    .filter(branch -> parentDTO.getId() != null &&
                            parentDTO.getId().equals(branch.getParentId()))
                    .map(ConvertUtilityBranchInfo::convertToChildDTO)
                    .collect(Collectors.toList());

            // Set children list to parent
            parent.setChildBranch(children);

            // Create hierarchy object and add to result
            result.add(new BranchHierarchyDTO(parent));
        }

        return result;
    }

    private static BranchInfoChildDTO convertToChildDTO(BranchInfoDTO dto) {
        BranchInfoChildDTO childDTO = new BranchInfoChildDTO();

        childDTO.setId(dto.getId());
        childDTO.setIsCorporate(dto.getIsCorporate());
        childDTO.setParentId(dto.getParentId());
        childDTO.setBrAlias(dto.getBrAlias());
        childDTO.setFullName(dto.getFullName());
        childDTO.setFullNameLocale(dto.getFullNameLocale());
        childDTO.setShortName(dto.getShortName());
        childDTO.setStreetName(dto.getStreetName());
        childDTO.setStreetNameLocale(dto.getStreetNameLocale());
        childDTO.setWardNo(dto.getWardNo());
        childDTO.setWardNoLocale(dto.getWardNoLocale());
        childDTO.setLocalMnc(dto.getLocalMnc());
        childDTO.setLocalMncLocale(dto.getLocalMncLocale());
        childDTO.setCity(dto.getCity());
        childDTO.setCityLocale(dto.getCityLocale());
        childDTO.setDistrict(dto.getDistrict());
        childDTO.setDistrictLocale(dto.getDistrictLocale());
        childDTO.setBrZone(dto.getBrZone());
        childDTO.setBrZoneLocale(dto.getBrZoneLocale());
        childDTO.setProvince(dto.getProvince());
        childDTO.setProvinceLocale(dto.getProvinceLocale());
        childDTO.setCountry(dto.getCountry());
        childDTO.setCountryLocale(dto.getCountryLocale());
        childDTO.setZipCode(dto.getZipCode());
        childDTO.setDefCurId(dto.getDefCurId());
        childDTO.setRegNo(dto.getRegNo());
        childDTO.setRegNoLocale(dto.getRegNoLocale());
        childDTO.setRegDate(dto.getRegDate());
        childDTO.setPan(dto.getPan());
        childDTO.setPanLocale(dto.getPanLocale());
        childDTO.setPhone(dto.getPhone());
        childDTO.setPhoneLocale(dto.getPhoneLocale());
        childDTO.setEmail(dto.getEmail());
        childDTO.setContactPerson(dto.getContactPerson());
        childDTO.setFax(dto.getFax());
        childDTO.setUrl(dto.getUrl());
        childDTO.setTimeZone(dto.getTimeZone());
        childDTO.setShowLocaleDate(dto.getShowLocaleDate());
        childDTO.setBrKey(dto.getBrKey());
        childDTO.setStatus(dto.getStatus());
        childDTO.setRemarks(dto.getRemarks());
        childDTO.setInsertDate(dto.getInsertDate());
        childDTO.setInsertUser(dto.getInsertUser());
        childDTO.setEditUser(dto.getEditUser());
        childDTO.setEditDate(dto.getEditDate());

        return childDTO;
    }

    private static BranchInfoParentDTO convertToParentDTO(BranchInfoDTO dto) {
        BranchInfoParentDTO parentDTO = new BranchInfoParentDTO();

        // Copy all properties from the original DTO
        parentDTO.setId(dto.getId());
        parentDTO.setIsCorporate(dto.getIsCorporate());
        parentDTO.setParentId(dto.getParentId());
        parentDTO.setBrAlias(dto.getBrAlias());
        parentDTO.setFullName(dto.getFullName());
        parentDTO.setFullNameLocale(dto.getFullNameLocale());
        parentDTO.setShortName(dto.getShortName());
        parentDTO.setStreetName(dto.getStreetName());
        parentDTO.setStreetNameLocale(dto.getStreetNameLocale());
        parentDTO.setWardNo(dto.getWardNo());
        parentDTO.setWardNoLocale(dto.getWardNoLocale());
        parentDTO.setLocalMnc(dto.getLocalMnc());
        parentDTO.setLocalMncLocale(dto.getLocalMncLocale());
        parentDTO.setCity(dto.getCity());
        parentDTO.setCityLocale(dto.getCityLocale());
        parentDTO.setDistrict(dto.getDistrict());
        parentDTO.setDistrictLocale(dto.getDistrictLocale());
        parentDTO.setBrZone(dto.getBrZone());
        parentDTO.setBrZoneLocale(dto.getBrZoneLocale());
        parentDTO.setProvince(dto.getProvince());
        parentDTO.setProvinceLocale(dto.getProvinceLocale());
        parentDTO.setCountry(dto.getCountry());
        parentDTO.setCountryLocale(dto.getCountryLocale());
        parentDTO.setZipCode(dto.getZipCode());
        parentDTO.setDefCurId(dto.getDefCurId());
        parentDTO.setRegNo(dto.getRegNo());
        parentDTO.setRegNoLocale(dto.getRegNoLocale());
        parentDTO.setRegDate(dto.getRegDate());
        parentDTO.setPan(dto.getPan());
        parentDTO.setPanLocale(dto.getPanLocale());
        parentDTO.setPhone(dto.getPhone());
        parentDTO.setPhoneLocale(dto.getPhoneLocale());
        parentDTO.setEmail(dto.getEmail());
        parentDTO.setContactPerson(dto.getContactPerson());
        parentDTO.setFax(dto.getFax());
        parentDTO.setUrl(dto.getUrl());
        parentDTO.setTimeZone(dto.getTimeZone());
        parentDTO.setShowLocaleDate(dto.getShowLocaleDate());
        parentDTO.setBrKey(dto.getBrKey());
        parentDTO.setStatus(dto.getStatus());
        parentDTO.setRemarks(dto.getRemarks());
        parentDTO.setInsertDate(dto.getInsertDate());
        parentDTO.setInsertUser(dto.getInsertUser());
        parentDTO.setEditUser(dto.getEditUser());
        parentDTO.setEditDate(dto.getEditDate());

        return parentDTO;
    }

    public static List<BranchInfoOutDTO> ConvertListBranchInfoResponseDTO(List<BranchInfo> childBranches) {
        return childBranches.stream()
                .map(ConvertUtilityBranchInfo::convertToBranchInfoOutDTO)
                .collect(Collectors.toList());
    }

    public static BranchInfoOutDTO convertToBranchInfoOutDTO(BranchInfo branchInfo) {
        BranchInfoOutDTO dto = new BranchInfoOutDTO();
        dto.setId(branchInfo.getId());
        dto.setIsCorporate(branchInfo.getIsCorporate());
        dto.setParentBranch(branchInfo.getParentId() != null ? branchInfo.getParentId().getFullName() : null);
        dto.setBrAlias(branchInfo.getBrAlias());
        dto.setFullName(branchInfo.getFullName());
        dto.setFullNameLocale(branchInfo.getFullNameLocale());
        dto.setShortName(branchInfo.getShortName());
        dto.setStreetName(branchInfo.getStreetName());
        dto.setStreetNameLocale(branchInfo.getStreetNameLocale());
        dto.setWardNo(branchInfo.getWardNo());
        dto.setWardNoLocale(branchInfo.getWardNoLocale());
        dto.setLocalMnc(branchInfo.getLocalMnc());
        dto.setLocalMncLocale(branchInfo.getLocalMncLocale());
        dto.setCity(branchInfo.getCity());
        dto.setCityLocale(branchInfo.getCityLocale());
        dto.setDistrict(branchInfo.getDistrict());
        dto.setDistrictLocale(branchInfo.getDistrictLocale());
        dto.setBrZone(branchInfo.getBrZone());
        dto.setBrZoneLocale(branchInfo.getBrZoneLocale());
        dto.setProvince(branchInfo.getProvince());
        dto.setProvinceLocale(branchInfo.getProvinceLocale());
        dto.setCountry(branchInfo.getCountry());
        dto.setCountryLocale(branchInfo.getCountryLocale());
        dto.setZipCode(branchInfo.getZipCode());
        dto.setDefCurId(branchInfo.getDefCurId());
        dto.setRegNo(branchInfo.getRegNo());
        dto.setRegNoLocale(branchInfo.getRegNoLocale());
        dto.setRegDate(branchInfo.getRegDate());
        dto.setPan(branchInfo.getPan());
        dto.setPanLocale(branchInfo.getPanLocale());
        dto.setPhone(branchInfo.getPhone());
        dto.setPhoneLocale(branchInfo.getPhoneLocale());
        dto.setEmail(branchInfo.getEmail());
        dto.setContactPerson(branchInfo.getContactPerson());
        dto.setFax(branchInfo.getFax());
        dto.setUrl(branchInfo.getUrl());
        dto.setTimeZone(branchInfo.getTimeZone());
        dto.setShowLocaleDate(branchInfo.getShowLocaleDate());
        dto.setBrKey(branchInfo.getBrKey());
        dto.setStatus(branchInfo.getStatus());
        dto.setRemarks(branchInfo.getRemarks());
        dto.setInsertDate(branchInfo.getInsertDate());
        dto.setInsertUser(branchInfo.getInsertUser());
        dto.setEditUser(branchInfo.getEditUser());
        dto.setEditDate(branchInfo.getEditDate());
        return dto;
    }
}
