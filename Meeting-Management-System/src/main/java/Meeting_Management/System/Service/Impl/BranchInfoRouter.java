package Meeting_Management.System.Service.Impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BranchInfoRouter {
    private final Map<String, IBranchInfoService> branchService;

    public BranchInfoRouter(ApplicationContext applicationContext) {
        this.branchService = applicationContext.getBeansOfType(IBranchInfoService.class);
    }

    public IBranchInfoService getBranchInfoService(String type) {
        IBranchInfoService service = branchService.get(type);
        if (service == null) {
            throw new IllegalArgumentException("No IBranchInfoService found for type " + type);
        }
        return service;
    }
}
