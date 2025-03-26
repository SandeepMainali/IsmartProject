package Meeting_Management.System.utils;

import Meeting_Management.System.DTO.*;
import Meeting_Management.System.entity.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertUtility {

    //ConvertUtil For User
    public static UserDTO ConvertUtilityUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setBranch(user.getBranch());
        userDTO.setCounterId(user.getCounterId());
        userDTO.setRole(user.getRole());
        userDTO.setUserName(user.getUserName());
        userDTO.setPasswordSalt(user.getPasswordSalt());
        userDTO.setPasswordHash(user.getPasswordHash());
        userDTO.setFullName(user.getFullName());
        userDTO.setFullNameLocale(user.getFullNameLocale());
        userDTO.setAddress(user.getAddress());
        userDTO.setGender(user.getGender());
        userDTO.setPrimaryContact(user.getPrimaryContact());
        userDTO.setOtherContact(user.getOtherContact());
        userDTO.setEmail(user.getEmail());
        userDTO.setStatus(user.getStatus());
        userDTO.setDeactiveDate(user.getDeactiveDate());
        userDTO.setRemarks(user.getRemarks());
        userDTO.setInsertUser(user.getInsertUser());
        userDTO.setInsertDate(user.getInsertDate());
        userDTO.setEditUser(user.getEditUser());
        userDTO.setEditDate(user.getEditDate());
        return userDTO;
    }

    //ConvertUtil For Roles
    public static RolesDTO ConvertUtilityRoles(Roles role) {
        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setId(role.getId());
        rolesDTO.setRoleAlias(role.getRoleAlias());
        rolesDTO.setRoleName(role.getRoleName());
        rolesDTO.setRemarks(role.getRemarks());
        rolesDTO.setInsertUser(role.getInsertUser());
        rolesDTO.setInsertDate(role.getInsertDate());
        rolesDTO.setEditUser(role.getEditUser());
        rolesDTO.setEditDate(role.getEditDate());
        return rolesDTO;
    }

    //ConvertUtil For Menus
    public static MenusDTO ConvertUtilityMenus(Menus menu) {
        MenusDTO menuDTO = new MenusDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setMenuAlias(menu.getMenuAlias());
        menuDTO.setMenuName(menu.getMenuName());
        menuDTO.setParentMenu(menu.getParentMenu());
        menuDTO.setMenuType(menu.getMenuType());
        menuDTO.setVersion(menu.getVersion());
        menuDTO.setMenuNameOther(menu.getMenuNameOther());
        menuDTO.setMenuUrl(menu.getMenuUrl());
        menuDTO.setMenuController(menu.getMenuController());
        menuDTO.setMenuAction(menu.getMenuAction());
        menuDTO.setDisplayIndex(menu.getDisplayIndex());
        menuDTO.setFaCode(menu.getFaCode());
        menuDTO.setRemarks(menu.getRemarks());
        menuDTO.setInsertUser(menu.getInsertUser());
        menuDTO.setInsertDate(menu.getInsertDate());
        menuDTO.setEditUser(menu.getEditUser());
        menuDTO.setEditDate(menu.getEditDate());
        return menuDTO;
    }

    //ConvertUtil For Gender
    public static GendersDTO ConvertUtilityRolesDTO(Gender gender) {
        GendersDTO gendersDTO = new GendersDTO();
        gendersDTO.setId(gender.getId());
        gendersDTO.setGenderAlias(gender.getGenderAlias());
        gendersDTO.setGenderName(gender.getGenderName());
        gendersDTO.setGenderAliasLocale(gender.getGenderAliasLocale());
        gendersDTO.setGenderNameLocale(gender.getGenderNameLocale());
        gendersDTO.setRemarks(gender.getRemarks());
        gendersDTO.setInsertUser(gender.getInsertUser());
        gendersDTO.setInsertDate(gender.getInsertDate());
        gendersDTO.setEditUser(gender.getEditUser());
        gendersDTO.setEditDate(gender.getEditDate());
        return gendersDTO;
    }

    //ConvertUtil For BranchInfos
    public static BranchInfosDTO ConvertUtilityBranchInfos(BranchInfos branchInfos) {
        BranchInfosDTO branchInfosDTO = new BranchInfosDTO();
        branchInfosDTO.setId(branchInfos.getId());
        branchInfosDTO.setIsCorporate(branchInfos.getIsCorporate());
        branchInfosDTO.setParentBranch(branchInfos.getParentBranch());
        branchInfosDTO.setBranchAlias(branchInfos.getBranchAlias());
        branchInfosDTO.setFullName(branchInfos.getFullName());
        branchInfosDTO.setFullNameLocale(branchInfos.getFullNameLocale());
        branchInfosDTO.setShortName(branchInfos.getShortName());
        branchInfosDTO.setStreetName(branchInfos.getStreetName());
        branchInfosDTO.setStreetNameLocale(branchInfos.getStreetNameLocale());
        branchInfosDTO.setWardNo(branchInfos.getWardNo());
        branchInfosDTO.setWardNoLocale(branchInfos.getWardNoLocale());
        branchInfosDTO.setLocalMnc(branchInfos.getLocalMnc());
        branchInfosDTO.setLocalMncLocale(branchInfos.getLocalMncLocale());
        branchInfosDTO.setCity(branchInfos.getCity());
        branchInfosDTO.setCityLocale(branchInfos.getCityLocale());
        branchInfosDTO.setDistrict(branchInfos.getDistrict());
        branchInfosDTO.setDistrictLocale(branchInfos.getDistrictLocale());
        branchInfosDTO.setBranchZone(branchInfos.getBranchZone());
        branchInfosDTO.setBranchZoneLocale(branchInfos.getBranchZoneLocale());
        branchInfosDTO.setProvince(branchInfos.getProvince());
        branchInfosDTO.setProvinceLocale(branchInfos.getProvinceLocale());
        branchInfosDTO.setCountry(branchInfos.getCountry());
        branchInfosDTO.setCountryLocale(branchInfos.getCountryLocale());
        branchInfosDTO.setZipCode(branchInfos.getZipCode());
        branchInfosDTO.setDefaultCurrencyId(branchInfos.getDefaultCurrencyId());
        branchInfosDTO.setRegNo(branchInfos.getRegNo());
        branchInfosDTO.setRegNoLocale(branchInfos.getRegNoLocale());
        branchInfosDTO.setRegDate(branchInfos.getRegDate());
        branchInfosDTO.setPan(branchInfos.getPan());
        branchInfosDTO.setPanLocale(branchInfos.getPanLocale());
        branchInfosDTO.setPhone(branchInfos.getPhone());
        branchInfosDTO.setPhoneLocale(branchInfos.getPhoneLocale());
        branchInfosDTO.setEmail(branchInfos.getEmail());
        branchInfosDTO.setContactPerson(branchInfos.getContactPerson());
        branchInfosDTO.setFax(branchInfos.getFax());
        branchInfosDTO.setUrl(branchInfos.getUrl());
        branchInfosDTO.setTimeZone(branchInfos.getTimeZone());
        branchInfosDTO.setShowLocaleDate(branchInfos.getShowLocaleDate());
        branchInfosDTO.setBranchKey(branchInfos.getBranchKey());
        branchInfosDTO.setStatus(branchInfos.getStatus());
        branchInfosDTO.setRemarks(branchInfos.getRemarks());
        branchInfosDTO.setInsertDate(branchInfos.getInsertDate());
        branchInfosDTO.setInsertUser(branchInfos.getInsertUser());
        branchInfosDTO.setEditUser(branchInfos.getEditUser());
        branchInfosDTO.setEditDate(branchInfos.getEditDate());
        return branchInfosDTO;
    }
}
