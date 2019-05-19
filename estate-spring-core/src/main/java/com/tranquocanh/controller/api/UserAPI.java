package com.tranquocanh.controller.api;

import com.tranquocanh.dto.UserDTO;
import com.tranquocanh.service.IUserService;
import com.tranquocanh.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO model) {
        model = userService.save(model);
        return ResponseEntity.ok(model);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO model) {
        model = userService.update(model);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Long[] ids) {
        if(ids != null){
            userService.delete(ids);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<UserDTO> getEmployee(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(userService.getEmployee(id));
    }
}
