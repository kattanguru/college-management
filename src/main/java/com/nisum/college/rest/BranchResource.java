package com.nisum.college.rest;

import com.nisum.college.bean.bo.BranchBO;
import com.nisum.college.bean.bo.BranchesBO;
import com.nisum.college.service.BranchService;
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
@Path(BRANCH)
@Api(value = "Branch Details")
public class BranchResource {

    private static Logger logger = LoggerFactory.getLogger(BranchResource.class);

    @Autowired
    private BranchService branchService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "All Branches Details", response = BranchesBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "All Branches Details", response = BranchesBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getAllBranches(@ApiParam(value = "_expand") @QueryParam("_expand") String expand) {
        logger.debug("START :: Retrieve Branches Details : _expand={}", expand);
        List<BranchBO> branchesList = branchService.getBranches(expand);
        BranchesBO branches = new BranchesBO();
        branches.setBranches(branchesList);
        logger.debug("END :: Retrieve Branches Details : {}", branches);
        return Response.status(HttpStatus.SC_OK).entity(branches).build();
    }

    @GET
    @Path("/{branchName}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Branch Details By Name", response = BranchBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Branch Details By Name", response = BranchBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getBranchByName(@ApiParam(value = "branchName", required = true) @PathParam("branchName") String branchName,
                                    @ApiParam(value = "_expand") @QueryParam("_expand") String expand) {
        logger.debug("START :: Retrieve Branch Details By Branch Name : {} : _expand={}", branchName, expand);
        BranchBO branch = branchService.getBranchByName(branchName, expand);
        logger.debug("END :: Retrieve Branch Details By Branch Name : {}", branch);
        return Response.status(HttpStatus.SC_OK).entity(branch).build();
    }

    @GET
    @Path("/course/{courseName}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Branch Details By Course", response = BranchBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Branch Details By Course", response = BranchBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response getBranchesByCourse(@ApiParam(value = "courseName", required = true) @PathParam("courseName") String courseName,
                                        @ApiParam(value = "_expand") @QueryParam("_expand") String expand) {
        logger.debug("START :: Retrieve Branch Details By Course Name : {} : _expand={}", courseName, expand);
        List<BranchBO> branchesList = branchService.getBranchByCourse(courseName, expand);
        BranchesBO branches = new BranchesBO();
        branches.setBranches(branchesList);
        logger.debug("END :: Retrieve Branch Details By Course Name : {}", branches);
        return Response.status(HttpStatus.SC_OK).entity(branches).build();
    }

    @POST
    @Path(ADD)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Branch Details", response = BranchBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Add Branch Details", response = BranchBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response addBranch(@RequestBody BranchBO branch) {
        logger.debug("START :: Add Branch Details : {}", branch);
        branch = branchService.addBranch(branch);
        logger.debug("END :: Add Branch Details : {}", branch);
        return Response.status(HttpStatus.SC_CREATED).entity(branch).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update Branch Details", response = BranchBO.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Update Branch Details", response = BranchBO.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response updateBranch(@RequestBody BranchBO branch) {
        logger.debug("START :: Update Branch Details : {}", branch);
        branch = branchService.updateBranch(branch);
        logger.debug("END :: Update Branch Details : {}", branch);
        return Response.status(HttpStatus.SC_CREATED).entity(branch).build();
    }

    @DELETE
    @Path("{branchName}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Remove Branch Details", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = "Remove Branch Details", response = String.class),
            @ApiResponse(code = HttpStatus.SC_SERVICE_UNAVAILABLE, message = "Service Unavailable")})
    public Response removeBranch(@ApiParam(value = "branchName", required = true) @PathParam("branchName") String branchName) {
        logger.debug("START :: Remove Branch Details : {}", branchName);
        branchService.removeBranch(branchName);
        logger.debug("END :: Remove Branch Details : {}", branchName);
        return Response.status(HttpStatus.SC_OK).entity(DELETED_SUCCESSFULLY).build();
    }
}