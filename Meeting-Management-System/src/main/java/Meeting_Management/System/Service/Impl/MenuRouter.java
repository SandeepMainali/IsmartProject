package Meeting_Management.System.Service.Impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MenuRouter {
    private final Map<String, IMenuService> menuService;

    public MenuRouter(ApplicationContext applicationContext) {
        this.menuService = applicationContext.getBeansOfType(IMenuService.class);
    }

    public IMenuService getMenuService(String type) {
        IMenuService service = menuService.get(type);
        if (service == null) {
            throw new IllegalArgumentException("No IMenuService found for type " + type);
        }
        return service;
    }
}
