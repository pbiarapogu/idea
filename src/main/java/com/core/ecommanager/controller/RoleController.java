package com.core.ecommanager.controller;

import com.core.ecommanager.model.Role;
import com.core.ecommanager.model.User;
import com.core.ecommanager.repository.RoleRepository;
import com.core.ecommanager.repository.UserRepository;
import com.core.ecommanager.utilityFunction.UtilFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable(value = "id") Long id){
        Optional<Role> role = roleRepository.findById(id);

        if(role.isPresent()){
            return ResponseEntity.ok().body(role.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public Role saveUser(@Validated @RequestBody Role role){
        Role roledetail = new Role();
        roledetail.setRoleName(role.getRoleName());
        roledetail.setId(role.getId());
        roledetail.setcDate(new Date());
        roledetail.setmDate(null);
        return roleRepository.save(roledetail);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateUser(@PathVariable(value = "id") Long roleId,@Validated @RequestBody Role
            roleDetails) throws Exception{
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new Exception("User Not Found: "+roleId));
        role.setRoleName(roleDetails.getRoleName());
        role.setcDate(roleDetails.getcDate());
        role.setmDate(new Date());
        final Role updateRole = roleRepository.save(role);
        return ResponseEntity.ok(updateRole);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteUser(@PathVariable(value = "id") Long roleId) throws Exception{
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new Exception("Role Not Found: "+roleId));
        roleRepository.delete(role);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deletedRole",Boolean.TRUE);
        return response;
    }

}
