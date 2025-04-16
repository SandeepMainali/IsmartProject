package Meeting_Management.System.Controller.Menu;

import Meeting_Management.System.Dto.MenuDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.MenuRouter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuRouter menuService;

    public MenuController(MenuRouter menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{type}/view")
    public ResponseDTO getAllMenu(@PathVariable("type") String type) {
        return menuService.getMenuService(type).getAllMenus();
    }

    @PostMapping("/{type}/add")
    public ResponseDTO createMenu(@PathVariable("type") String type,@RequestBody MenuDTO menuDTO) {
        return menuService.getMenuService(type).createMenu(menuDTO);
    }

    @PutMapping("/{type}/{id}")
    public ResponseDTO updateMenu(@PathVariable("type") String type,@PathVariable Integer id, @RequestBody MenuDTO menuDTO) {
        return menuService.getMenuService(type).updateMenu(id, menuDTO);
    }

    @DeleteMapping("/{type}/delete/{id}")
    public ResponseDTO deleteBranchInfo(@PathVariable("type") String type,@PathVariable Integer id) {
        return menuService.getMenuService(type).deleteMenu(id);
    }

    @GetMapping("/{type}/{id}")
    public ResponseDTO getMenuById(@PathVariable("type") String type,@PathVariable Integer id) {
        return menuService.getMenuService(type).getMenuById(id);
    }
}
