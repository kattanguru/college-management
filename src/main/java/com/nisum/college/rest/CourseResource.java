package com.nisum.college.rest;

import com.nisum.college.bean.bo.CourseBO;
import com.nisum.college.bean.bo.CoursesBO;
import com.nisum.college.service.CourseService;
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
@Path(COURSE)
@Api(value = "Course Details")
public class CourseResource {

    private static Logger logger = LoggerFactory.getLogger(CourseResource.class);

    @Autowired
    private CourseService courseService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "All Courses Details", response = CoursesBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "All Courses Details", response = CoursesBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getAllCourses(@ApiParam(value = "_expand") @QueryParam("_expand") String expand) {
        logger.debug("START :: Retrieve Courses Details : _expand={}", expand);
        List<CourseBO> coursesList = courseService.getCourses(expand);
        CoursesBO courses = new CoursesBO();
        courses.setCourses(coursesList);
        logger.debug("END :: Retrieve Courses Details : {}", courses);
        return Response.status(HttpStatus.SC_OK).entity(courses).build();
    }

    @GET
    @Path("/{courseName}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Course Details By Name", response = CourseBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Course Details By Name", response = CourseBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getCoursesByName(@ApiParam(value = "courseName", required = true) @PathParam("courseName") String courseName,
                                     @ApiParam(value = "_expand") @QueryParam("_expand") String expand) {
        logger.debug("START :: Retrieve Courses Details By Course Name : {} : _expand={}", courseName, expand);
        CourseBO course = courseService.getCourseByName(courseName, expand);
        logger.debug("END :: Retrieve Courses Details By Course Name : {}", course);
        return Response.status(HttpStatus.SC_OK).entity(course).build();
    }

    @POST
    @Path(ADD)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Course Details", response = CourseBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Add Course Details", response = CourseBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response addCourseByID(@RequestBody CourseBO course) {
        logger.debug("START :: Add Course Details : {}", course);
        course = courseService.addCourse(course);
        logger.debug("END :: Add Course Details : {}", course);
        return Response.status(HttpStatus.SC_CREATED).entity(course).build();
    }

    @DELETE
    @Path("{courseName}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Remove Course Details", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Remove Course Details", response = String.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response removeCourse(@ApiParam(value = "courseName", required = true) @PathParam("courseName") String courseName) {
        logger.debug("START :: Remove Course Details : {}", courseName);
        courseService.removeCourse(courseName);
        logger.debug("END :: Remove Course Details : {}", courseName);
        return Response.status(HttpStatus.SC_OK).entity(DELETED_SUCCESSFULLY).build();
    }
}