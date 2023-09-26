package com.esmc.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.esmc.exception.RestDefaultException;
import com.esmc.security.entity.AppRole;
import com.esmc.security.entity.AppUser;
import com.esmc.security.repository.AppRoleRepository;
import com.esmc.security.repository.AppUserRepository;
import com.esmc.security.utils.CurrentUser;
import com.esmc.security.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@RestController
public class AccountRestController {
    public final String cacheUserPrefix = "AppUser";
    public final String cacheRolePrefix = "AppRole";
    private AppUserRepository userRepository;
    private AppRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountRestController(AppUserRepository userRepository,
                                 AppRoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void controleUser(AppUser user) {
        if (user.getUsername() == null) {
            throw new RestDefaultException("Username null");
        }
        AppUser user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null && !user1.getId().equals(user.getId())) {
            throw new RestDefaultException("Doublon de Username");
        }
        if (user.getPassword() == null) {
            throw new RestDefaultException("Password null");
        }
    }

    private void controleRole(AppRole role) {
        if (role.getRolename() == null) {
            throw new RestDefaultException("Rolename null");
        }
    }

    @GetMapping(path = "/refreshToken")
    public void refreshoToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);


        if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.AUTH_PREFIX)) {
            try {
                String jwt = authorizationToken.substring(JWTUtil.AUTH_PREFIX.length());
                Algorithm algo = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jWTVerifier = JWT.require(algo).build();
                DecodedJWT decodedJWT = jWTVerifier.verify(jwt);

                String username = decodedJWT.getSubject();

                AppUser appUser = userRepository.findByUsername(username);

                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getRoles().stream()
                                .map(r -> r.getRolename())
                                .collect(Collectors.toList()))
                        .sign(algo);

                Map<String, String> idToken = new HashMap<>();
                idToken.put(JWTUtil.ACCESS_TOKEN, jwtAccessToken);
                idToken.put(JWTUtil.REFRESH_TOKEN, jwt);

                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception ex) {
                response.setHeader("error-message", ex.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            throw new RestDefaultException("Refresh token required");
        }
    }



    @PostMapping(path = "/getNewToken")
    public void getNewToken(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        List<String> roles = Collections.emptyList();
        Algorithm algo = Algorithm.HMAC256(JWTUtil.SECRET);
        String jwtAccessToken = JWT.create()
                .withSubject("automate_user")
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", new ArrayList<>(roles))
                .sign(algo);

        String jwtRefreshToken = JWT.create()
                .withSubject("automate_user")
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_REFRESH_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .sign(algo);

        Map<String, String> idToken = new HashMap<>();
        idToken.put(JWTUtil.ACCESS_TOKEN, jwtAccessToken);
        idToken.put(JWTUtil.REFRESH_TOKEN, jwtRefreshToken);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
    }

    @GetMapping(path = "/users")
    public PagedModel<EntityModel<AppUser>> appUsers(Pageable pageable,
                                                     PagedResourcesAssembler<AppUser> userResourcesAssembler) {
        Page<AppUser> appUsers = userRepository.findAll(pageable);

        return userResourcesAssembler.toModel(appUsers);
    }

    @PostMapping(path = "/users")
    public AppUser addUser(@RequestBody AppUser appUser) {
        appUser.setId(null);
        controleUser(appUser);
        String pw = appUser.getPassword();
        if (pw != null && pw.length() <= 50) {
            appUser.setPassword(passwordEncoder.encode(pw));
        }
        return userRepository.save(appUser);
    }

    @CachePut(value = cacheUserPrefix, key = "#id")
    @PutMapping(path = "/users/{id}")
    public AppUser updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        appUser.setId(id);
        controleUser(appUser);
        String pw = appUser.getPassword();
        if (pw != null && pw.length() <= 50) {
            appUser.setPassword(passwordEncoder.encode(pw));
        }
        return userRepository.save(appUser);
    }

    @CacheEvict(value = cacheUserPrefix)
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/roles")
    public PagedModel<EntityModel<AppRole>> appRoles(Pageable pageable,
                                                     PagedResourcesAssembler<AppRole> assembler) {
        Page<AppRole> appRoles = roleRepository.findAll(pageable);

        return assembler.toModel(appRoles);
    }

    @PostMapping(path = "/roles")
    public AppRole saveRole(@RequestBody AppRole appRole) {
        controleRole(appRole);
        return roleRepository.save(appRole);
    }

    @CachePut(value = cacheRolePrefix, key = "#id")
    @PutMapping(path = "/roles/{id}")
    public AppRole updateRole(@PathVariable("id") Long id, @RequestBody AppRole appRole) {
        appRole.setId(id);
        controleRole(appRole);
        return roleRepository.save(appRole);
    }

    @CacheEvict(value = cacheRolePrefix)
    @DeleteMapping(path = "/roles/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        AppUser appUser = userRepository.findByUsername(roleUserForm.getUsername());
        if (appUser == null) {
            throw new RestDefaultException("User non trouvé");
        }
        AppRole appRole = roleRepository.findByRolename(roleUserForm.getRolename());
        if (appRole == null) {
            throw new RestDefaultException("Role non trouvé");
        }
        appUser.getRoles().add(appRole);
    }

    @Cacheable(value = cacheUserPrefix, key = "#principal.name", unless = "#result = null")
    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }

    @Cacheable(value = cacheUserPrefix, unless = "#result = null")
    @GetMapping(path = "/users/{id}")
    public AppUser getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    @Cacheable(value = cacheRolePrefix, unless = "#result = null")
    @GetMapping(path = "/roles/{id}")
    public AppRole getRole(@PathVariable("id") Long id) {
        return roleRepository.findById(id).get();
    }

    @GetMapping("/currentUser")
    public AppUser getCurrentUser(HttpServletRequest request) {

        CurrentUser c = new CurrentUser();
       String user = c.whichHasTheRequest(request);

        return userRepository.findByUsername(user);
    }
}


@Data
class RoleUserForm {
    private String username;
    private String rolename;
}

