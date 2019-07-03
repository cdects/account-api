package com.anz.account.controller;

import com.anz.account.domain.Account;
import com.anz.account.service.AccountService;
import com.anz.account.service.TransactionService;
import com.anz.account.types.AccountType;
import com.anz.account.model.TransactionRes;
import com.anz.account.types.Currency;
import com.anz.account.types.DebitCredit;
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
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void getTransactionsRequestTest_invalidParam() throws Exception {
        Mockito.when(transactionService.getTransactions("a001", PageRequest.of(0, 20))).
                thenReturn(mockTransactions());

        mockMvc.perform(get("/api/v1/accounts/{accountNumber}/transactions", "a001")
                .param("page", "abc")
                .param("size", "20")
                .contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTransactionsRequestTest_happyPath() throws Exception {
        Mockito.when(transactionService.getTransactions("a1001", PageRequest.of(0, 20))).
                thenReturn(mockTransactions());

        Mockito.when(accountService.getAccountByAccountNumber(Mockito.anyString())).
                thenReturn(mockAccount());

        mockMvc.perform(get("/api/v1/accounts/{accountNumber}/transactions", "a1001")
                .param("page", "0")
                .param("size", "20")
                .contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.transactionReses[0].accountNumber", is("a1001")))
                .andExpect(jsonPath("$._embedded.transactionReses[0].accountName", is("AUDSaving1")))
                .andExpect(jsonPath("$._embedded.transactionReses[0].date", is("2019-04-28")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/accounts/a1001/transactions?page=0&size=20")));
    }

    private List<TransactionRes> mockTransactions() {
        List<TransactionRes> transactions = new ArrayList<>();
        TransactionRes transactionRes = new TransactionRes()
                .date(LocalDate.of(2019, Month.APRIL, 28 ))
                .currency(Currency.AUD)
                .accountName("AUDSaving1")
                .accountNumber("a1001")
                .credit(BigDecimal.ZERO)
                .debitCredit(DebitCredit.DEBIT)
                .debit(BigDecimal.valueOf(50))
                .narrative("transaction description");

        transactions.add(transactionRes);
        return transactions;
    }

    private Account mockAccount(){
        return new Account("a1001",
                "AUDSaving1",
                AccountType.SAVING,
                LocalDate.of(2019, Month.APRIL, 28),
                Currency.AUD,
                BigDecimal.valueOf(10000.10),
                "u001");
    }

}
