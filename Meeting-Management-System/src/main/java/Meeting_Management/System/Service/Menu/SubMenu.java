package Meeting_Management.System.Service.Menu;

import Meeting_Management.System.ConvertUtil.ConvertUtilityMenu;
import Meeting_Management.System.Dto.MenuDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Entity.Menu;
import Meeting_Management.System.Filter.JWTFilter;
import Meeting_Management.System.Repository.MenuRepo;
import Meeting_Management.System.Service.Impl.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;

@Service("submenu")
public class SubMenu implements IMenuService {

    @Autowired
    private MenuRepo menuRepository;

    @Override
    public ResponseDTO getAllMenus() {
        try {
            List<Menu> subMenu = menuRepository.findBymenuType("sub");

            if (subMenu.isEmpty()) {
                System.out.println("No sub menus found");
                return new ResponseDTO(
                        "success",
                        "204",
                        "No sub menus found",
                        Collections.emptyMap(),
                        null
                );
            }

            List<MenuDTO> subMenuDTOs = ConvertUtilityMenu.ConvertListMenuResponseDTO(subMenu);
            Map<String, Object> details = new HashMap<>();
            details.put("subMenus", subMenuDTOs);

            return new ResponseDTO(
                    "success",
                    "200",
                    "sub menus retrieved successfully",
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
    public ResponseDTO getMenuById(Integer id) {
        try {
            Optional<Menu> optionalMenu = menuRepository.findById(id);

            if (optionalMenu.isPresent() && optionalMenu.get().getParentMenu() != null) {
                MenuDTO menuDTO = ConvertUtilityMenu.convertToMenuDTO(optionalMenu.get());
                Map<String, Object> detail = new HashMap<>();
                detail.put("SubMenu", menuDTO);

                return new ResponseDTO(
                        "success",
                        "200",
                        "Sub Menu information retrieved successfully",
                        null,
                        detail
                );
            } else {
                return new ResponseDTO(
                        "error",
                        "404",
                        "No Sub Menu found with ID: " + id,
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
    public ResponseDTO createMenu(MenuDTO menuDTO) {
        try {
            // Ensure this is a super menu
            if (menuDTO.getParentMenuId() == null) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "sub menu should have a parent menu",
                        null,
                        null
                );
            }

            Optional<Menu> parentMenu = menuRepository.findById(menuDTO.getParentMenuId());
            if (parentMenu.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Parent Menu not found with ID: " + menuDTO.getParentMenuId(),
                        null,
                        null
                );
            }

            Menu parent = parentMenu.get();

            // Check for duplicate menuAlias
            if (menuDTO.getMenuAlias() != null &&
                    menuRepository.findByMenuAlias(menuDTO.getMenuAlias()).isPresent()) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Menu with alias '" + menuDTO.getMenuAlias() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate menuName
            if (menuDTO.getMenuName() != null &&
                    menuRepository.existsByMenuName(menuDTO.getMenuName())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Menu with name '" + menuDTO.getMenuName() + "' already exists",
                        null,
                        null
                );
            }

            // Check for duplicate menuNameOther
            if (menuDTO.getMenuNameOther() != null &&
                    menuRepository.existsByMenuNameOther(menuDTO.getMenuNameOther())) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Menu with other name '" + menuDTO.getMenuNameOther() + "' already exists",
                        null,
                        null
                );
            }


            Menu menu = ConvertUtilityMenu.convertToMenuEntity(menuDTO,parent);
            menu.setMenuType("sub");
            menu.setInsertDate(ZonedDateTime.now());
            menu.setEditUser(null);
            menu.setEditDate(null);
            menu.setInsertUser(JWTFilter.getCurrentUserId());

            Menu savedMenu = menuRepository.save(menu);
            MenuDTO savedMenuDTO = ConvertUtilityMenu.convertToMenuDTO(savedMenu);

            Map<String, Object> detail = new HashMap<>();
            detail.put("superMenu", savedMenuDTO);

            return new ResponseDTO(
                    "success",
                    "201",
                    "Super menu created successfully",
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
                    "Database error during sub menu creation: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during super menu creation: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Transactional
    @Override
    public ResponseDTO updateMenu(Integer id, MenuDTO menuDTO) {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);

            if (menuOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Sub Menu not found with id: " + id,
                        null,
                        null
                );
            }

            Menu existingMenu = menuOptional.get();

            menuDTO.setParentMenuId(existingMenu.getParentMenu().getId());

            // Update the existing entity with values from DTO
            Menu updatedMenu = ConvertUtilityMenu.updateMenuFields(existingMenu, menuDTO);

            // Set update timestamp
            updatedMenu.setEditDate(ZonedDateTime.now());
            updatedMenu.setEditUser(JWTFilter.getCurrentUserId());

            // Save the updated entity
            Menu savedMenu = menuRepository.save(updatedMenu);
            MenuDTO updatedMenuDTO = ConvertUtilityMenu.convertToMenuDTO(savedMenu);

            Map<String, Object> detail = new HashMap<>();
            detail.put("parentBranch", updatedMenuDTO);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Sub Menu updated successfully",
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
                    "Database error during Sub Menu update: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during Sub Menu update: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Transactional
    @Override
    public ResponseDTO deleteMenu(Integer id) {
        String delete;
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);

            if (menuOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "menu not found with id: " + id,
                        null,
                        null
                );
            }

            Menu subBranch = menuOptional.get();

            delete = subBranch.getMenuName();
            menuRepository.delete(subBranch);

            Map<String, Object> detail = new HashMap<>();
            detail.put("deletedBranch", delete);

            return new ResponseDTO(
                    "success",
                    "200",
                    "the menu deleted successfully",
                    null,
                    detail
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Cannot delete this sub menu as it is referenced by other entities: " + e.getMessage(),
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during sub menu deletion: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during sub menuh deletion: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

}
