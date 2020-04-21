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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.nisum.college.bean.CollegeConstants.STUDENTS_NAME;
import static com.nisum.college.bean.CollegeConstants.TOPIC_NAME;


@Component
@Path("/kafka")
@Api(value = "Kafka Details")
public class KafkaResource {
    private static Logger logger = LoggerFactory.getLogger(KafkaResource.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GET
    @Path("/publish")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Kafka Publish Details", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Kafka Publish Details", response = String.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public String publishStudentsDetails() {
        List<StudentBO> studentList = studentService.getStudents();
        kafkaTemplate.send(TOPIC_NAME, STUDENTS_NAME, studentList);
        logger.debug("Student details published to Kafka Topic");
        return "Published successfully";
    }
}
