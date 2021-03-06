/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package solver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MazeSolverControllerTests {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
//
//        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Hello, World!"));
//    }
//
//    @Test
//    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
//
//        this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
//    }

    @Test
    public void paramMazeShouldReturnSolutionMessage() throws Exception {

        this.mockMvc.perform(post("/solver")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("[	\"+-+-+-+-+-+-+-+\", \"&     |   |   |\", \"+ +-+-+ + + +-+\", \"| |   | | |   |\", \"+ + + + + +-+ +\", \"|   |   |     |\", \"+-+-+-+-+ +-+ +\", \"|         |   |\", \"+ +-+-+-+-+-+-+\", \"|   |         |\", \"+-+ +-+-+-+ + +\", \"|   |   |   | |\", \"+ +-+ + + +-+ +\", \"|     |   |   *\", \"+-+-+-+-+-+-+-+\" ]"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"steps\":[\"right\",\"down\",\"down\",\"down\",\"down\",\"right\",\"right\",\"up\",\"up\",\"right\",\"right\",\"down\",\"down\",\"right\",\"right\",\"up\",\"up\",\"up\",\"up\",\"right\",\"right\",\"down\",\"down\",\"down\",\"down\",\"down\",\"down\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"down\",\"down\",\"right\",\"right\",\"down\",\"down\",\"left\",\"left\",\"down\",\"down\",\"right\",\"right\",\"right\",\"right\",\"up\",\"up\",\"right\",\"right\",\"down\",\"down\",\"right\",\"right\",\"up\",\"up\",\"right\",\"right\",\"up\",\"up\",\"right\",\"right\",\"down\",\"down\",\"down\",\"down\",\"right\"]}"));
    }

    @Test
    public void paramMazeWithInternalEntranceAndExit() throws Exception {

        this.mockMvc.perform(post("/solver")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[ \"+-+-+-+-+-+-+-+\", \"|     |   |   |\", \"+ +-+-+ + + +-+\", \"| |   | | |   |\", \"+ + + + + +-+ +\", \"|   |   |   & |\", \"+-+-+-+-+ +-+ +\", \"|         |   |\", \"+ +-+-+-+-+-+-+\", \"|   |         |\", \"+-+ +-+-+-+ + +\", \"|  *|   |   | |\", \"+ +-+ + + +-+ +\", \"|     |   |   |\", \"+-+-+-+-+-+-+-+\" ]"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"steps\":[\"left\",\"left\",\"left\",\"down\",\"down\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"down\",\"down\",\"right\",\"right\",\"down\",\"down\"]}"));
    }

    @Test
    public void paramMazeWithLoops() throws Exception {

        this.mockMvc.perform(post("/solver")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[ \"+-+-+-+-+-+-+-+\", \"|     |   |   |\", \"+ +-+-+ + + +-+\", \"| |   | | |   |\", \"+ + + + + +-+ +\", \"|   |       & |\", \"+-+-+-+-+ +-+ +\", \"|         |   |\", \"+ +-+-+-+ +-+ +\", \"|   |         |\", \"+-+ +-+-+-+ + +\", \"|  *|   |   | |\", \"+ +-+ + + +-+ +\", \"|     |   |   |\", \"+-+-+-+-+-+-+-+\" ]"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"steps\":[\"left\",\"left\",\"left\",\"down\",\"down\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"down\",\"down\",\"right\",\"right\",\"down\",\"down\"]}"));
    }

    @Test
    public void paramMazeFunkyShape() throws Exception {

        this.mockMvc.perform(post("/solver")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[ \"+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+\", \"|   |   |            |       |   |\", \"+ + + + +-+-+-+ ++-+ + +-+-+ + + +\", \"| |   |         ||   | |     | | |\", \"+ +-+-+-+-+-+-+-++ +-+-+ +-+-+-+ +\", \"|   | |   |     ||         |     |\", \"+-+ + +*+ +-+ + ++-+-+-+ + + +-+-+\", \"|     | |   | | ||     | |   |   |\", \"+ +-+ +-+ + + + ++ +-+ + + +-+-+ +\", \"|   |     |   |      |   | |   | |\", \"+-+-+-+-+-+-+-+-++ + +-+-+ + + + +\", \"                 | | |   |   |   |\", \"                 + + +-+ + +-+-+-+-+\", \"                 | |     |         |\", \"                 + +-+-+-+-+-+-+-+ +\", \"                 |     |         | |\", \"                 +-+-+ +-+-+-+-+ + +\", \"                 |   |   |     | | |\", \"                 + +-+-+ + +-+-+ + +\", \"                 |       | |   | | |\", \"                 +-+ +-+ + + + + + -+-+-+-+-+-+-+-+-+\", \"                 | | |   |   |   |                  |\", \"                 + + + + + +-+-+ ++-+-+-+-+-+-+-+-+ +\", \"                 |   | |   |              | |   | | |\", \"                 +-+-+-+-+-+-+-+-++-+-+-+ + + + + + +\", \"                                  | |     | | | | | |\", \"                                  + + +-+-+ + + + + +\", \"                                  | |     | & |   | |\", \"                                  + +-+-+ +-+-+ +-+ +\", \"                                  |   |   |     | | |\", \"                                  + +-+ +-+ +-+-+ + +\", \"                                  |       |     | | |\", \"                                  + +-+-+ +-+ + + + +\", \"                                  | |   | |   |   | |\", \"                                  + + + +-+ +-+-+-+ +\", \"                                  |   |             |\", \"                                  +-+-+-+-+-+-+-+-+-+\" ]"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"steps\":[\"right\",\"up\",\"up\",\"up\",\"up\",\"right\",\"right\",\"down\",\"down\",\"down\",\"down\",\"down\",\"down\",\"left\",\"left\",\"left\",\"left\",\"down\",\"down\",\"right\",\"right\",\"down\",\"down\",\"left\",\"left\",\"down\",\"down\",\"right\",\"right\",\"right\",\"right\",\"right\",\"right\",\"right\",\"right\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"left\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"up\",\"left\",\"left\",\"down\",\"down\",\"down\",\"down\",\"left\",\"left\",\"up\",\"up\",\"left\",\"left\",\"left\",\"left\",\"down\",\"down\",\"left\",\"left\",\"left\",\"up\",\"up\",\"up\",\"up\",\"left\",\"left\",\"down\",\"down\",\"down\",\"down\",\"left\",\"left\",\"up\",\"up\",\"left\",\"left\",\"up\",\"up\",\"left\",\"left\",\"down\"]}"));
    }

}
