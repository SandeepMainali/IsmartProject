package Meeting_Management.System.ConvertUtil;

import Meeting_Management.System.Dto.MenuDTO;
import Meeting_Management.System.Dto.UserDTO;
import Meeting_Management.System.Entity.Menu;
import Meeting_Management.System.Service.Impl.UserService;
import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConvertUtilityMenu {


    private static ApplicationContext applicationContext;

    public static Menu updateMenuFields(Menu existingMenu, MenuDTO dto) {
        existingMenu.setParentMenu(existingMenu.getParentMenu());
        existingMenu.setMenuAlias(dto.getMenuAlias());
        existingMenu.setMenuName(dto.getMenuName());
        existingMenu.setVersion(dto.getVersion());
        existingMenu.setMenuNameOther(dto.getMenuNameOther());
        existingMenu.setMenuUrl(dto.getMenuUrl());
        existingMenu.setMenuController(dto.getMenuController());
        existingMenu.setMenuAction(dto.getMenuAction());
        existingMenu.setDisplayIndex(dto.getDisplayIndex());
        existingMenu.setFaCode(dto.getFaCode());
        existingMenu.setRemarks(dto.getRemarks());

        return existingMenu;
    }

    // This helper class will be initialized by Spring and set the context
    @Component
    public static class ContextProvider implements ApplicationContextAware {

        @Override
        public void setApplicationContext(ApplicationContext context) {
            applicationContext = context;
        }
    }

    public static Menu convertToMenuEntity(MenuDTO dto, Menu parentMenu) {
        Menu menu = new Menu();
        menu.setParentMenu(parentMenu);
        menu.setMenuAlias(dto.getMenuAlias());
        menu.setMenuName(dto.getMenuName());
        menu.setVersion(dto.getVersion());
        menu.setMenuNameOther(dto.getMenuNameOther());
        menu.setMenuUrl(dto.getMenuUrl());
        menu.setMenuController(dto.getMenuController());
        menu.setMenuAction(dto.getMenuAction());
        menu.setDisplayIndex(dto.getDisplayIndex());
        menu.setFaCode(dto.getFaCode());
        menu.setRemarks(dto.getRemarks());

        return menu;
    }

    public static List<MenuDTO> ConvertListMenuResponseDTO(List<Menu> superMenus) {
        return superMenus.stream()
                .map(ConvertUtilityMenu::convertToMenuDTO)
                .collect(Collectors.toList());
    }

    public static MenuDTO convertToMenuDTO(Menu menu) {
        UserService userService = applicationContext.getBean(UserService.class);
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setMenuAlias(menu.getMenuAlias());
        dto.setMenuName(menu.getMenuName());
        dto.setParentMenuId(menu.getParentMenu() != null ? menu.getParentMenu().getId() : null);
        dto.setMenuType(menu.getMenuType());
        dto.setVersion(menu.getVersion());
        dto.setMenuNameOther(menu.getMenuNameOther());
        dto.setMenuUrl(menu.getMenuUrl());
        dto.setMenuController(menu.getMenuController());
        dto.setMenuAction(menu.getMenuAction());
        dto.setDisplayIndex(menu.getDisplayIndex());
        dto.setFaCode(menu.getFaCode());
        dto.setRemarks(menu.getRemarks());
        if (menu.getInsertUser() != null) {
            Object userObj = userService.getUserById(menu.getInsertUser()).getDetails().get("user");
            dto.setInsertUser(userObj instanceof UserDTO ? ((UserDTO) userObj).getUserName() : null);
        } else {
            dto.setInsertUser(null);
        }
        dto.setInsertDate(menu.getInsertDate());
        if (menu.getEditUser() != null) {
            Object userObj = userService.getUserById(menu.getEditUser()).getDetails().get("user");
            dto.setEditUser(userObj instanceof UserDTO ? ((UserDTO) userObj).getUserName() : null);
        } else {
            dto.setEditUser(null);
        }
        dto.setEditDate(menu.getEditDate());
        return dto;
    }
}
