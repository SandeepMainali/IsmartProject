package Meeting_Management.System.ConvertUtil;

import Meeting_Management.System.Dto.BranchInfoDTO;

import Meeting_Management.System.Entity.BranchInfo;
import lombok.experimental.UtilityClass;

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
}
