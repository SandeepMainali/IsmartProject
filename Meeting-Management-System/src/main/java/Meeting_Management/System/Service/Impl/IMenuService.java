package Meeting_Management.System.Service.Impl;

import Meeting_Management.System.Dto.MenuDTO;
import Meeting_Management.System.Dto.ResponseDTO;

public interface IMenuService {
    ResponseDTO getAllMenus();

    ResponseDTO getMenuById(Integer id);

    ResponseDTO createMenu(MenuDTO menuDTO);

    ResponseDTO updateMenu(Integer id, MenuDTO menuDTO);

    ResponseDTO deleteMenu(Integer id);

}
