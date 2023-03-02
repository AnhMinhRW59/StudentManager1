package com.example.shoppeerw59.controller;

import com.example.shoppeerw59.Exception.AppException;
import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.request.CreateAccountReq;
import com.example.shoppeerw59.modal.request.SearchAccountReq;
import com.example.shoppeerw59.modal.request.UpdateAccountReq;
import com.example.shoppeerw59.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/account")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService service;

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')") // "has Authority"Cấp quyền cho chỉ 1 đối tượng
    public List<Account> getAll() {
        return service.getAll();
    }

    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')") // "has AnyAthortiry" Cấp quyền cho nhiều đối tượng
    public Page<Account> search(@RequestBody SearchAccountReq req){
    return service.search(req);
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){

            Account account = service.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateAccountReq req) {

            service.create(req);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public Account update(@RequestBody UpdateAccountReq req1, @PathVariable int id){
        return service.update(id, req1);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
