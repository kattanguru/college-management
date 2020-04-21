package com.nisum.college.rest;

import com.nisum.college.bean.bo.StudentBO;
import com.nisum.college.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.nisum.college.bean.CollegeConstants.*;


@Component
@Path(KAFKA)
@Api(value = "Kafka Details")
public class KafkaResource {
    private static Logger logger = LoggerFactory.getLogger(KafkaResource.class);

    private List<StudentBO> studentBOList = null;

    @Autowired
    private StudentService studentService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GET
    @Path("/publisher/students")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Kafka Publisher Details", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Kafka Publisher Details", response = String.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public String publishStudentsDetails() {
        List<StudentBO> studentList = studentService.getStudents();
        kafkaTemplate.send(STUDENTS_TOPIC, STUDENTS_NAME, studentList);
        logger.debug("Student details published to Kafka Topic");
        return "Published successfully";
    }

    @GET
    @Path("/consumer/students")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Kafka Consumer Details", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Kafka Consumer Details", response = List.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response consumeStudentsDetails() {
        logger.debug("Student details published to Kafka Topic");
        return Response.status(HttpStatus.SC_OK).entity(studentBOList).build();
    }

    @KafkaListener(groupId = STUDENTS_TOPIC, topics = STUDENTS_TOPIC)
    public List<StudentBO> getStudentsFromTopic(List<StudentBO> students) {
        studentBOList = students;
        return studentBOList;
    }
}
