package Meeting_Management.System.Controller.Menu;

import Meeting_Management.System.ConvertUtil.HttpStatusUtility;
import Meeting_Management.System.Dto.MenuDTO;
import Meeting_Management.System.Dto.ResponseDTO;
import Meeting_Management.System.Service.Impl.MenuRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuRouter menuService;

    public MenuController(MenuRouter menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{type}/view")
    public ResponseEntity<ResponseDTO> getAllMenu(@PathVariable("type") String type) {
        ResponseDTO response = menuService.getMenuService(type).getAllMenus();
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @PostMapping("/{type}/add")
    public ResponseEntity<ResponseDTO> createMenu(@PathVariable("type") String type,@RequestBody MenuDTO menuDTO) {
        ResponseDTO response = menuService.getMenuService(type).createMenu(menuDTO);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @PutMapping("/{type}/{id}")
    public ResponseEntity<ResponseDTO> updateMenu(@PathVariable("type") String type,@PathVariable Integer id, @RequestBody MenuDTO menuDTO) {
        ResponseDTO response = menuService.getMenuService(type).updateMenu(id, menuDTO);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @DeleteMapping("/{type}/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBranchInfo(@PathVariable("type") String type,@PathVariable Integer id) {
        ResponseDTO response = menuService.getMenuService(type).deleteMenu(id);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<ResponseDTO> getMenuById(@PathVariable("type") String type,@PathVariable Integer id) {
        ResponseDTO response = menuService.getMenuService(type).getMenuById(id);
        return new ResponseEntity<>(response, HttpStatusUtility.getHttpStatus(response));
    }
}
