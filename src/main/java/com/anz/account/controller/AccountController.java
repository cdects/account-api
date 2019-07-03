package com.anz.account.controller;

import com.anz.account.exception.ErrorMessage;
import com.anz.account.model.AccountRes;
import com.anz.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@Slf4j
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Value("${accounts.for.user}")
    private String accountInfo;

    @Value("${account.not.found}")
    private String accountNotFound;


    @GetMapping(value = "/{userId}" , produces = "application/hal+json")
    public @ResponseBody ResponseEntity<?> getUserAccounts(@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int page,
                                                   @RequestParam(value = "size", defaultValue = "20") @Max(value = 100) @Positive int size,
                                                   @PathVariable String userId){

        List<AccountRes> accounts = accountService.getUserAccounts(userId, PageRequest.of(page, size));
        log.info(accountInfo, accounts.size(), userId);
        if(accounts.size() > 0){
        Resources<AccountRes> resources = new Resources<AccountRes>(accounts);
        resources.add(linkTo(methodOn(AccountController.class).getUserAccounts(page, size, userId)).withSelfRel());
            return ResponseEntity.ok().body(resources);
        }else{
             return ResponseEntity.ok().body( new ErrorMessage(accountNotFound + userId, HttpStatus.OK));
        }
    }

}