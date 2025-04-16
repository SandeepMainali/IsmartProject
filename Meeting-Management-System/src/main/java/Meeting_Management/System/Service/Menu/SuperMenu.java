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

@Service("supermenu")
public class SuperMenu implements IMenuService {

    @Autowired
    private MenuRepo menuRepository;

    @Override
    public ResponseDTO getAllMenus() {
        try {
            List<Menu> superMenus = menuRepository.findBymenuType("super");

            if (superMenus.isEmpty()) {
                System.out.println("No super menus found");
                return new ResponseDTO(
                        "success",
                        "204",
                        "No super menus found",
                        Collections.emptyMap(),
                        null
                );
            }

            List<MenuDTO> superMenuDTOs = ConvertUtilityMenu.ConvertListMenuResponseDTO(superMenus);
            Map<String, Object> details = new HashMap<>();
            details.put("superMenus", superMenuDTOs);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Super menus retrieved successfully",
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

            if (optionalMenu.isPresent() && optionalMenu.get().getParentMenu() == null) {
                MenuDTO menuDTO = ConvertUtilityMenu.convertToMenuDTO(optionalMenu.get());
                Map<String, Object> detail = new HashMap<>();
                detail.put("SuperMenu", menuDTO);

                return new ResponseDTO(
                        "success",
                        "200",
                        "Super Menu information retrieved successfully",
                        null,
                        detail
                );
            } else {
                return new ResponseDTO(
                        "error",
                        "404",
                        "No Super Menu found with ID: " + id,
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

    @Transactional
    @Override
    public ResponseDTO createMenu(MenuDTO menuDTO) {
        try {
            // Ensure this is a super menu
            if (menuDTO.getParentMenuId() != null) {
                return new ResponseDTO(
                        "error",
                        "400",
                        "Super menu cannot have a parent menu",
                        null,
                        null
                );
            }

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

            Menu menu = ConvertUtilityMenu.convertToMenuEntity(menuDTO,null);
            menu.setMenuType("super");
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
                    "Database error during super menu creation: " + e.getMessage(),
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

            if (menuOptional.isEmpty() || menuOptional.get().getParentMenu() != null) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Parent menu not found with id: " + id,
                        null,
                        null
                );
            }

            Menu existingMenu = menuOptional.get();

            // Ensure parentId remains null as this is a parent menu
            menuDTO.setParentMenuId(null);

            // Update the existing entity with values from DTO
            Menu updatedMenu = ConvertUtilityMenu.updateMenuFields(existingMenu, menuDTO);

            // Set update timestamp
            updatedMenu.setEditDate(ZonedDateTime.now());
            updatedMenu.setEditUser(JWTFilter.getCurrentUserId());

            // Save the updated entity
            Menu savedMenu = menuRepository.save(updatedMenu);
            MenuDTO updatedMenuDTO = ConvertUtilityMenu.convertToMenuDTO(savedMenu);

            Map<String, Object> detail = new HashMap<>();
            detail.put("parentMenu", updatedMenuDTO);

            return new ResponseDTO(
                    "success",
                    "200",
                    "Parent menu updated successfully",
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
                    "Database error during parent menu update: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during parent menu update: " + e.getMessage(),
                    null,
                    null
            );
        }
    }

    @Transactional
    @Override
    public ResponseDTO deleteMenu(Integer id) {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);

            if (menuOptional.isEmpty()) {
                return new ResponseDTO(
                        "error",
                        "404",
                        "Super menu not found with id: " + id,
                        null,
                        null
                );
            }

            // Get the parent branch
            Menu superMenu = menuOptional.get();

            // Find all sub menus associated with this parent
            List<Menu> subMenus = menuRepository.findByParentMenu(superMenu);

            // Delete all Sub menus first
            menuRepository.deleteAll(subMenus);

            // Then delete the Super menu
            menuRepository.delete(superMenu);

            Map<String, Object> detail = new HashMap<>();
            detail.put("deletedParentId", id);
            detail.put("deletedChildrenCount", subMenus.size());

            return new ResponseDTO(
                    "success",
                    "200",
                    "Super Menu and all its Sub Menus deleted successfully",
                    null,
                    detail
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO(
                    "error",
                    "400",
                    "Cannot delete this Super Menu as it is referenced by other entities: " + e.getMessage(),
                    null,
                    null
            );
        } catch (DataAccessException e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "Database error during Super Menu deletion: " + e.getMessage(),
                    null,
                    null
            );
        } catch (Exception e) {
            return new ResponseDTO(
                    "error",
                    "500",
                    "An unexpected error occurred during Super Menu deletion: " + e.getMessage(),
                    null,
                    null
            );
        }
    }
}
