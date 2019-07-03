package com.anz.account.controller;


import com.anz.account.model.AccountRes;
import com.anz.account.service.AccountService;
import com.anz.account.types.AccountType;
import com.anz.account.types.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserAccountsRequestTest_invalidParam() throws Exception {
        Mockito.when(accountService.getUserAccounts("u1001", PageRequest.of(0, 20))).
                thenReturn(mockAccounts());

        mockMvc.perform(get("/api/v1/accounts/{userId}", "u1001")
                .param("page", "abc")
                .param("size", "20")
                .contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void userAccountsRequestTest_happyPath() throws Exception {
        Mockito.when(accountService.getUserAccounts("u1001", PageRequest.of(0, 20))).
                thenReturn(mockAccounts());


        mockMvc.perform(get("/api/v1/accounts/{userId}", "u1001")
                .param("page", "0")
                .param("size", "20")
                .contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.accountReses[0].accountNumber", is("u1001")))
                .andExpect(jsonPath("$._embedded.accountReses[0].accountName", is("AUDSaving1")))
                .andExpect(jsonPath("$._embedded.accountReses[0].accountType", is("SAVING")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/accounts/u1001?page=0&size=20")));
    }

    private List<AccountRes> mockAccounts() {
        List<AccountRes> accounts = new ArrayList<AccountRes>();
        AccountRes accountRes = new AccountRes()
                .accountNumber("u1001")
                .accountName("AUDSaving1")
                .accountType(AccountType.SAVING.name())
                .balanceDate(LocalDate.of(2019, Month.JUNE, 5 ))
                .currency(Currency.AUD)
                .openingAvailableBalance(new BigDecimal("2000"));
        accounts.add(accountRes);
        return accounts;
    }


}
