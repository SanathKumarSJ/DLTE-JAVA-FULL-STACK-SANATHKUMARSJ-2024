package com.jdbctemp.template;

import com.jayway.jsonpath.JsonPath;
import com.jdbctemp.template.entity.Transaction;
import com.jdbctemp.template.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndPointTesting {
    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Autowired
    private MockMvc mockMvc;


//testing new transaction using post mapping
    @Test
    void testNewTransaction() throws Exception {
        String request = "{\n" +
                "    \"transactionId\": 56787676767,\n" +
                "    \"transactionDate\": \"2035-10-21\",\n" +
                "    \"transactionBy\": \"Mahesh\",\n" +
                "    \"transactionTo\": \"Pramith\"\n" +
                "    \"transactionAmount\": 78000.0,\n" +
                "    \"transactionRemarks\": \"friend\",\n" +
                "}";

        Transaction transaction2=new Transaction(56787676767L,new Date(2035,10,21),"Mahesh","Pramith",78000.0,"friend");
        when(transactionService.apiSave(any())).thenReturn(transaction2);

//        mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/credit/new").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(56787676767L)).
//                andExpect(jsonPath("$.transactionDate").value("2035-10-21T18:30:00.000+00:00")).
//                andExpect(jsonPath("$.transactionBy").value("Mahesh")).
//                andExpect(jsonPath("$.transactionTo").value("Pramith")).
//                andExpect(jsonPath("$.transactionAmount").value(78000)).
//                andExpect(jsonPath("$.transactionRemarks").value("friend"))
//        ;
    }

    //testing find transaction from using get mapping
    @Test
    public void testTransactionBy() throws Exception {
        Transaction transaction=new Transaction(56787676767L,new Date(2035,10,21),"Mahesh","Pramith",78000.0,"friend");
        when(transactionService.apiFindBySender(eq("Mahesh"))).thenReturn(Optional.of(transaction));
        mockMvc.perform(get("/transaction/findby/mahesh")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.transactionId").value(56787676767L)).
                andExpect(jsonPath("$.transactionDate").value("2035-10-21T18:30:00.000+00:00")).
                andExpect(jsonPath("$.transactionBy").value("Mahesh")).
                andExpect(jsonPath("$.transactionTo").value("Pramith")).
                andExpect(jsonPath("$.transactionAmount").value(78000)).
                andExpect(jsonPath("$.transactionRemarks").value("friend"))
        ;
    }

    //testing find transaction to using get mapping

    @Test
    public void testTransactionTo() throws Exception {
        Transaction transaction=new Transaction(5456476767L,new Date(2035,10,21),"Rohith","vickey",18000.0,"friend");
        when(transactionService.apiFindToReceiver(eq("vickey"))).thenReturn(Optional.of(transaction));
        mockMvc.perform(get("/transaction/findto/vickey")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.transactionId").value(5456476767L)).
                andExpect(jsonPath("$.transactionDate").value("2035-10-21T18:30:00.000+00:00")).
                andExpect(jsonPath("$.transactionBy").value("Rohith")).
                andExpect(jsonPath("$.transactionTo").value("vickey")).
                andExpect(jsonPath("$.transactionAmount").value(18000)).
                andExpect(jsonPath("$.transactionRemarks").value("friend"))
        ;
    }

    //testing find transaction amount using get mapping

    @Test
    public void testTransactionAmount() throws Exception {
        Transaction transaction=new Transaction(56787676767L,new Date(2035,10,21),"Mahesh","Pramith",78000.0,"friend");
        when(transactionService.apiFindAmount(eq(78000.0))).thenReturn(Optional.of(transaction));
        mockMvc.perform(get("/transaction/findamount/78000.0")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.transactionId").value(56787676767L)).
                andExpect(jsonPath("$.transactionDate").value("2035-10-21T18:30:00.000+00:00")).
                andExpect(jsonPath("$.transactionBy").value("Mahesh")).
                andExpect(jsonPath("$.transactionTo").value("Pramith")).
                andExpect(jsonPath("$.transactionAmount").value(78000)).
                andExpect(jsonPath("$.transactionRemarks").value("friend"))
        ;
    }
}
