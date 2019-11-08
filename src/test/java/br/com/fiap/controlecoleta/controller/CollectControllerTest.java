package br.com.fiap.controlecoleta.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import br.com.fiap.controlecoleta.entity.Collect;
import br.com.fiap.controlecoleta.entity.vo.CollectVo;
import br.com.fiap.controlecoleta.service.CollectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class CollectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean()
    private CollectService service;

    @Test
    public void createCollect() throws Exception {
        Collect savedResult = Collect.builder()
            .id(2L)
            .build();
        Mockito.when(this.service.registerCollect(any())).thenReturn(savedResult);

        String collectJson = "{"
            + "  \"cpfCnpj\": \"12345678911\",\n"
            + "  \"address\": \"Rua da avenida\",\n"
            + "  \"fastCollect\": \"false\",\n"
            + "  \"scheduledDate\": \"2019-11-07\",\n"
            + "  \"timeRange\": \"MORNING\",\n"
            + "  \"status\": \"SCHEDULED\"\n"
            + "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/collect/create")
            .accept(MediaType.APPLICATION_JSON).content(collectJson)
            .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("2", response.getContentAsString());
    }

    public void getActiveCollect() throws Exception {
        CollectVo expected = CollectVo.builder()
            .cpfCnpj("12345678911")
            .address("Endereço de casa")
            .scheduledDate(LocalDate.of(2019, 11, 11))
            .build();

        Mockito.when(this.service.getActiveCollects("12345678911")).thenReturn(Collections.singletonList(expected));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
            "/collect/activeCollect").accept(
            MediaType.APPLICATION_JSON).param("cpfCnpj", "12345678911");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        List<CollectVo> actual = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
                                                              List.class);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(1, actual.size());
        CollectVo actualCollectVo = actual.get(0);
        assertEquals(expected.getCpfCnpj(), actualCollectVo.getCpfCnpj());
        assertEquals(expected.getAddress(), actualCollectVo.getAddress());
        assertEquals(expected.getScheduledDate(), actualCollectVo.getScheduledDate());
    }

    @Test
    public void cancelCollect() throws Exception {
        Mockito.when(this.service.cancelCollect(1L)).thenReturn(true);
        Mockito.when(this.service.cancelCollect(2L)).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
            "/collect/cancel").accept(
            MediaType.APPLICATION_JSON).param("collectId", "1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        requestBuilder = MockMvcRequestBuilders.delete(
            "/collect/cancel").accept(
            MediaType.APPLICATION_JSON).param("collectId", "2");

        result = mockMvc.perform(requestBuilder).andReturn();
        response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    }

    @Test
    public void toCollect() throws Exception {
        Mockito.when(this.service.toCollect(1L)).thenReturn(true);
        Mockito.when(this.service.toCollect(2L)).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
            "/collect/toCollect").accept(
            MediaType.APPLICATION_JSON).param("collectId", "1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        requestBuilder = MockMvcRequestBuilders.post(
            "/collect/toCollect").accept(
            MediaType.APPLICATION_JSON).param("collectId", "2");

        result = mockMvc.perform(requestBuilder).andReturn();
        response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    }

    @Test
    public void toFinish() throws Exception {
        Mockito.when(this.service.toFinish(1L, 1500d)).thenReturn(true);
        Mockito.when(this.service.toFinish(2L, 2000d)).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
            "/collect/toFinish").accept(
            MediaType.APPLICATION_JSON).param("collectId", "1").param("value", "1500");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        requestBuilder = MockMvcRequestBuilders.post(
            "/collect/toFinish").accept(
            MediaType.APPLICATION_JSON).param("collectId", "2").param("value","2000");

        result = mockMvc.perform(requestBuilder).andReturn();
        response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    }
}
