package com.anz.account.controller;


import com.anz.account.model.Message;
import com.anz.account.model.TransactionRes;
import com.anz.account.service.AccountService;
import com.anz.account.service.TransactionService;
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
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Value("${transactions.for.account}")
    private String transactionInfo;

    @Value("${transactions.not.found}")
    private String transactionNotFound;

    @GetMapping(value = "/{accountNumber}/transactions", produces = "application/hal+json")
    public @ResponseBody ResponseEntity<?> getTransactions(@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int page,
                                                             @RequestParam(value = "size", defaultValue = "20") @Max(value = 100) @Positive int size,
                                                             @PathVariable String accountNumber){
        log.debug("Request in TransactionController::getTransactions:accountNumber: " + accountNumber);
        List<TransactionRes> transactions = transactionService.getTransactions(accountNumber, PageRequest.of(page, size));

        if(transactions.size() > 0) {
            Resources<TransactionRes> resources = new Resources<TransactionRes>(transactions);
            resources.add(linkTo(methodOn(TransactionController.class).getTransactions(page, size, accountNumber)).withSelfRel());

            //Add 'accounts' url/link to transactions response for traversing back on accounts listing
            resources.add(linkTo(methodOn(AccountController.class).getUserAccounts(page, size, accountService.getAccountByAccountNumber(accountNumber).getUserId())).withRel("accounts"));

            log.debug("Response out TransactionController::getTransactions:accountNumber: " + accountNumber + " transactions : "+transactions);
            return ResponseEntity.ok().body(resources);

        } else {
            log.info(transactionNotFound + accountNumber, HttpStatus.OK);
            return ResponseEntity.ok().body( new Message(transactionNotFound + accountNumber, HttpStatus.OK));
        }
   }

}
