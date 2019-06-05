package hu.kalee.multi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.kalee.multi.tenant.TenantContext;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping
    public Map<String, String> getUserInfo() {
        Map<String, String> info = new HashMap<>();
        String userName;
        final Object principal = getAuthentication().getPrincipal();

        if (principal instanceof User) {
            userName = ((User) principal).getUsername();
        } else {
            userName = ((Map<String, String>) principal).get("username");
        }
        info.put("username", userName);
        info.put("tenant", TenantContext.getCurrentTenant());

        return info;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
