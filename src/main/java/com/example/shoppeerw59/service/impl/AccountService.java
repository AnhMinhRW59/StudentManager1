package com.example.shoppeerw59.service.impl;

import com.example.shoppeerw59.Exception.AppException;
import com.example.shoppeerw59.Exception.ErrorResponseBase;
import com.example.shoppeerw59.modal.dto.BaseReq;
import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Role;
import com.example.shoppeerw59.modal.request.CreateAccountReq;
import com.example.shoppeerw59.modal.request.SearchAccountReq;
import com.example.shoppeerw59.modal.request.UpdateAccountReq;
import com.example.shoppeerw59.repository.IAccountRepository;
import com.example.shoppeerw59.repository.Specification.AccountSpecification;
import com.example.shoppeerw59.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {
    @Autowired
    private IAccountRepository repository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Account> search(SearchAccountReq req) {
        Specification<Account> accountSpecification = AccountSpecification.buildCondition(req);
        PageRequest pageRequest = BaseReq.buildPageReq(req);

        return repository.findAll(accountSpecification, pageRequest);
    }

    @Override
    public Account getById(int id) {
        Optional<Account> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new AppException(ErrorResponseBase.NOT_FOUND);
        }
        try {
            return optional.get();
        }catch (Exception e){
            // Dù có ngoại lệ nào xảy ra thì đều throw ra đối tương AppException
            throw new AppException(e);
        }
    }

    @Override
    public void create(CreateAccountReq req) {
        Account account = new Account();
        String encodePassword = encoder.encode(req.getPassword());
        BeanUtils.copyProperties(req, account);
        account.setPassword(encodePassword);
        account.setRole(Role.CUSTOMER);
        // kiểm tra Username đã tồn tại hay chưa
        Optional<Account> accountCheck = repository.getByUsername(req.getUsername());
        if (accountCheck.isPresent()){
            // Username đã tồn tại -> Bắn ra lỗi
            throw new AppException(ErrorResponseBase.USERNAME_EXISTED);
        }
        try {
            repository.save(account);
        }catch (Exception e){
            throw new AppException(e);
        }
    }

    @Override
    public Account update(int id, UpdateAccountReq updateAccountReq) {

        Account accountDb = getById(id);
        if(accountDb != null){
            BeanUtils.copyProperties(updateAccountReq, accountDb);
            accountDb.setId(id);
            repository.save(accountDb);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = repository.getByUsername(username);
        if (optional.isPresent()) {
            Account account =optional.get();
            // Lấy ra giá trị authorities để phân quyền
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(account.getRole());
            return new User(account.getUsername(), account.getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException((username));
        }

    }
}
