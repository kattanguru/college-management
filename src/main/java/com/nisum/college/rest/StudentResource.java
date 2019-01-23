package com.nisum.college.rest;

import com.nisum.college.bean.bo.StudentBO;
import com.nisum.college.bean.bo.StudentsBO;
import com.nisum.college.service.StudentService;
import io.swagger.annotations.*;
import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.nisum.college.bean.CollegeConstants.*;

@Component
@Path(STUDENT)
@Api(value = "Student Details")
public class StudentResource {

    private static Logger logger = LoggerFactory.getLogger(StudentResource.class);

    @Autowired
    private StudentService studentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "All Students Details", response = StudentsBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "All Students Details", response = StudentsBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getAllStudents() {
        logger.debug("START :: Retrieve All Students Details");
        List<StudentBO> studentList = studentService.getStudents();
        StudentsBO students = new StudentsBO();
        students.setStudents(studentList);
        logger.debug("END :: Retrieve All Students Details : {}", students);
        return Response.status(HttpStatus.SC_OK).entity(students).build();
    }

    @GET
    @Path("/branch/{branchName}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Students Details By Branch", response = StudentsBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Students Details By Branch", response = StudentsBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getStudentsByCourse(@ApiParam(value = "branchName", required = true) @PathParam("branchName") String branchName) {
        logger.debug("START :: Retrieve Students Details by Branch : {}", branchName);
        List<StudentBO> studentList = studentService.getStudentsByBranch(branchName);
        StudentsBO students = new StudentsBO();
        students.setStudents(studentList);
        logger.debug("END :: Retrieve Students Details by Branch : {}", students);
        return Response.status(HttpStatus.SC_OK).entity(students).build();
    }

    @GET
    @Path("/phone/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Students Details By Phone", response = StudentBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Students Details By Phone", response = StudentBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getStudentsByPhone(@ApiParam(value = "phoneNumber", required = true) @PathParam("phoneNumber") String phoneNumber) {
        logger.debug("START :: Retrieve Student Details by Phone Number : {}", phoneNumber);
        StudentBO student = studentService.getStudentByPhone(phoneNumber);
        logger.debug("END :: Retrieve Student Details by Phone Number : {}", student);
        return Response.status(HttpStatus.SC_OK).entity(student).build();
    }

    @GET
    @Path("{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Specific Student Details", response = StudentBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Students Details By Id", response = StudentBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getStudent(@ApiParam(value = "studentId", required = true) @PathParam("studentId") Long studentId) {
        logger.debug("START :: Retrieve Student Details by ID : {}", studentId);
        StudentBO student = studentService.getStudent(studentId);
        logger.debug("END :: Retrieve Student Details by ID : {}", student);
        return Response.status(HttpStatus.SC_OK).entity(student).build();
    }

    @POST
    @Path(ADD)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Student Details", response = StudentBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Add Student Details", response = StudentBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response addStudent(@RequestBody StudentBO student) {
        logger.debug("START :: Add Student Details : {}", student);
        student = studentService.addStudent(student);
        logger.debug("END :: Add Student Details : {}", student);
        return Response.status(HttpStatus.SC_CREATED).entity(student).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update Student Details", response = StudentBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Update of Specific Student Details", response = StudentBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response updateStudentDetails(@RequestBody StudentBO student) {
        logger.debug("START :: Update Student Details : {}", student);
        student = studentService.updateStudentDetails(student);
        logger.debug("END :: Update Student Details : {}", student);
        return Response.status(HttpStatus.SC_OK).entity(student).build();
    }

    @DELETE
    @Path("{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Remove Student Details", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Remove Student Details", response = String.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response removeStudent(@ApiParam(value = "studentId", required = true) @PathParam("studentId") Long studentId) {
        logger.debug("START :: Remove Student Details : {}", studentId);
        studentService.removeStudentDetails(studentId);
        logger.debug("END :: Remove Student Details :{}", studentId);
        return Response.status(HttpStatus.SC_OK).entity(DELETED_SUCCESSFULLY).build();
    }
}