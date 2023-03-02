package com.example.shoppeerw59.service;

import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.request.CreateAccountReq;
import com.example.shoppeerw59.modal.request.SearchAccountReq;
import com.example.shoppeerw59.modal.request.UpdateAccountReq;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();

    Page<Account> search(SearchAccountReq req);

    Account getById(int id);

    void create(CreateAccountReq req);

    Account update(int id, UpdateAccountReq updateAccountReq);

    void delete(int id);
}
