package project.integrations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:test.integration.properties"})
public class EmployeesTests {
    @Autowired
    MockMvc mockMvc;

    int i=8000;

    @Test
    public void checkNameFormatIsOkay() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+ "@gmail.com\",\n" +
                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +
                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isOk());
    }
    @Test
    public void checkNameHasNumbers() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"11yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+1+ "@gmail.com\",\n" +
                "    \"birthDate\": \"2010-10-11\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkNameIsNull() throws Exception {
        String body = "{\n" +

                "    \"email\": \"yasmine" +i+2+ "@gmail.com\",\n" +
                "    \"birthDate\": \"2010-10-11\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkNameIsEmpty() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"\",\n" +
                "    \"email\": \"yasmine" +i+3+ "@gmail.com\",\n" +
                "    \"birthDate\": \"2010-10-11\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkNameLength() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatemyasmine hatemyasmine hatemyasmine hatemyasmine hatemyasmine hatemyasmine hatemyasmine hatemyasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+4+ "@gmail.com\",\n" +
                "    \"birthDate\": \"2010-10-11\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkBirthdayInFuture() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+5+ "@gmail.com\",\n" +
                "    \"birthDate\": \"2020-10-11\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkBirthdayFormatNotComplete() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+6+ "@gmail.com\",\n" +
                "    \"birthDate\": \"10-11\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkBirthdayIsNull() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+13+ "@gmail.com\",\n" +

                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkBirthdayFormatInvalid() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+7+ "@gmail.com\",\n" +
                "    \"birthDate\": \"iii\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }


    @Test
    public void checkEmailFormatNotValid() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+8+ ".com\",\n" +
                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }


    @Test
    public void checkEmailFormatIsOkay() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+9+ "@gmail.com\",\n" +

                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isOk());
    }

    @Test
    public void checkEmailIsUnique() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine10@gmail.com\",\n" +

                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }


    @Test
    public void checkEmailIsEmpty() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"\",\n" +

                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }
    @Test
    public void checkEmailIsNull() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +


                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"HR Employee\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }



    @Test
    public void checkTitleNotExists() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+11+ "@gmail.com\",\n" +

                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false,\n" +
                "     \n" +
                "    \"titleName\":\"accounter\"\n" +

                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }

    @Test
    public void checkTitleIsNull() throws Exception {
        String body = "{\n" +
                "    \"fullName\": \"yasmine hatem\",\n" +
                "    \"email\": \"yasmine" +i+12+ "@gmail.com\",\n" +

                "    \"birthDate\": \"2000-07-07\",\n" +
                "     \n" +
                "     \"isLeader\" : false\n" +
                "     \n" +


                "    \n" +
                "    \n" +
                "}\n" +
                "\n";
        mockMvc.perform(post("/add")
                .header("Content-type", "application/json").content(body)).andExpect(status().isBadRequest());
    }




}
