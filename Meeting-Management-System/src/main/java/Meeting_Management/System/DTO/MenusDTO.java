package Meeting_Management.System.DTO;

import Meeting_Management.System.entity.Menus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class MenusDTO {
    private Integer id;
    private String menuAlias;
    private String menuName;
    private Menus parentMenu;
    private String menuType;
    private String version;
    private String menuNameOther;
    private String menuUrl;
    private String menuController;
    private String menuAction;
    private Integer displayIndex;
    private String faCode;
    private String remarks;
    private Integer insertUser;
    private ZonedDateTime insertDate;
    private Integer editUser;
    private ZonedDateTime editDate;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuAlias() {
        return menuAlias;
    }

    public void setMenuAlias(String menuAlias) {
        this.menuAlias = menuAlias;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Menus getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menus parentMenu) {
        this.parentMenu = parentMenu;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMenuNameOther() {
        return menuNameOther;
    }

    public void setMenuNameOther(String menuNameOther) {
        this.menuNameOther = menuNameOther;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuController() {
        return menuController;
    }

    public void setMenuController(String menuController) {
        this.menuController = menuController;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getFaCode() {
        return faCode;
    }

    public void setFaCode(String faCode) {
        this.faCode = faCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    public ZonedDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(ZonedDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getEditUser() {
        return editUser;
    }

    public void setEditUser(Integer editUser) {
        this.editUser = editUser;
    }

    public ZonedDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(ZonedDateTime editDate) {
        this.editDate = editDate;
    }
}
