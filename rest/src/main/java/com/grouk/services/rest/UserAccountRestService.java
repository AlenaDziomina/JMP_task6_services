package com.grouk.services.rest;

import com.google.common.io.ByteStreams;
import com.grouk.services.exception.factory.PublicExceptionFactory;
import com.grouk.services.manager.UserAccountManager;
import com.grouk.services.model.AvatarImage;
import com.grouk.services.model.UserAccount;
import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

/**
 * User Account Rest Service
 * Created by Alena_Grouk on 3/22/2017.
 */
@Path("/user")
@Api(description = "the UserAccount API")
public class UserAccountRestService {
    private final UserAccountManager userAccountManager;
    private final PublicExceptionFactory publicExceptionFactory;

    public UserAccountRestService(@InjectParam UserAccountManager userAccountManager, @InjectParam
            PublicExceptionFactory exceptionFactory) {
        this.userAccountManager = userAccountManager;
        this.publicExceptionFactory = exceptionFactory;
    }

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    @ApiOperation(value = "Get all users", response = UserAccount.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = UserAccount.class,
                    responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid status value", response = UserAccount.class,
                    responseContainer = "List")})

    public List<UserAccount> getUserAccountList() {
        try {
            return userAccountManager.getUserAccountList();
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    @ApiOperation(value = "Get user by ID", response = UserAccount.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = UserAccount.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = UserAccount.class),
            @ApiResponse(code = 404, message = "User not found", response = UserAccount.class)})

    public UserAccount getUserAccount(@ApiParam(value = "User ID that needs to be fetched", required = true)
                                      @PathParam("id") Long userId) {
        try {
            return userAccountManager.getUserAccount(userId);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @GET
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    @ApiOperation(value = "Get default user for UI create", response = UserAccount.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = UserAccount.class),
            @ApiResponse(code = 400, message = "Invalid status value", response = UserAccount.class)})

    public UserAccount newUserAccountList() {
        try {
            return userAccountManager.getDefaultUserAccount();
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    @ApiOperation(value = "Update an existing UserAccount", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Response.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 404, message = "User not found", response = Void.class)})

    public void updateUserAccount(@ApiParam(value = "User ID that needs to update", required = true) @PathParam("id")
                                              Long userId,
                                  @ApiParam(value = "User that needs to update") UserAccount userAccount) {
        try {
            userAccountManager.updateUserAccount(userId, userAccount);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    @ApiOperation(value = "Create a new UserAccount", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Response.class),
            @ApiResponse(code = 405, message = "Invalid input", response = Void.class)})

    public void createUserAccount(@ApiParam(value = "User that needs to create") UserAccount userAccount) {
        try {
            userAccountManager.createUserAccount(userAccount);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @DELETE
    @Path("/{id}")

    @ApiOperation(value = "Delete an existing user", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Response.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 404, message = "User not found", response = Void.class)})

    public void deleteUserAccount(@ApiParam(value = "User ID that needs to delete", required = true) @PathParam("id")
                                              Long userId) {
        try {
            userAccountManager.deleteUserAccount(userId);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @GET
    @Path("/{id}/avatar")
    @Produces("image/*")

    @ApiOperation(value = "Get avatar image by user ID", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Response.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class)})

    public Response getUserAvatar(@ApiParam(value = "User ID that needs to get avatar image", required = true)
                                  @PathParam("id") Long userId) {
        try {
            AvatarImage image = userAccountManager.getUserAvatar(userId);
            return Response.ok(image.getImage()).build();
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @POST
    @Path("/{id}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)

    @ApiOperation(value = "Uploads an avatar image", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Void.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class)})

    public void saveUserAvatar(@ApiParam(value = "User id that needs to upload avatar image", required = true)
                                   @PathParam("id") Long userId,
                               @FormDataParam("file") InputStream inputStream,
                               @FormDataParam("file") FormDataContentDisposition fileDetail) {
        try {
            byte[] targetArray = ByteStreams.toByteArray(inputStream);
            userAccountManager.saveUserAvatar(userId, new AvatarImage(targetArray));
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @DELETE
    @Path("/{id}/avatar")

    @ApiOperation(value = "Delete an avatar image of user", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Void.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 404, message = "User not found", response = Void.class)})

    public void deleteUserAvatar(@ApiParam(value = "User ID that needs to delete avatar image", required = true)
                                     @PathParam("id") Long userId) {
        try {
            userAccountManager.deleteUserAvatar(userId);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @PUT
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    @ApiOperation(value = "Update list of UserAccount", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Response.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 404, message = "User not found", response = Void.class)})

    public void updateUserAccount(@ApiParam(value = "User's list that needs to update") List<UserAccount>
                                              userAccountList) {
        try {
            userAccountManager.updateUserAccountList(userAccountList);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @POST
    @Path("/batch")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    @ApiOperation(value = "Create a list of a new UserAccount", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Response.class),
            @ApiResponse(code = 405, message = "Invalid input", response = Void.class)})

    public void createUserAccount(@ApiParam(value = "User's list that needs to create") List<UserAccount>
                                              userAccountList) {
        try {
            userAccountManager.createUserAccount(userAccountList);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }


}
