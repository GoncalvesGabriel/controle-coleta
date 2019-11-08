package br.com.fiap.controlecoleta.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import br.com.fiap.controlecoleta.service.FinancialMovementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FinancialMovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinancialMovementService service;

    @Test
    public void rescue() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
            "/financialMovement/rescue").accept(
            MediaType.APPLICATION_JSON).param("value", "2000").param("cpfCnpj", "12345678911");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        verify(this.service).rescue(2000d, "12345678911");
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    //@Test
    public void getBalance() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
            "/balance/{cpfCnpj}").accept(
            MediaType.APPLICATION_JSON).param("cpfCnpj","12345678911");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        verify(this.service).getBalance("12345678911");
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


}
