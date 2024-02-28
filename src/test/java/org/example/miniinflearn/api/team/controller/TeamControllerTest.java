package org.example.miniinflearn.api.team.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.miniinflearn.api.team.controller.request.CreateTeamRequest;
import org.example.miniinflearn.api.team.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeamService teamService;

    @Test
    @DisplayName("팀 이름은 필수 값이다.")
    void c1() throws Exception {
        //given
        CreateTeamRequest request = new CreateTeamRequest("팀");

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/team")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
    }


    @Test
    @DisplayName("팀 이름이 공백일 경우 예외가 발생한다.")
    void c2() throws Exception {
        //given
        CreateTeamRequest request = new CreateTeamRequest("");

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/team")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("FAIL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("팀명은 공백이나 null이 들어올 수 없습니다."));
    }


}