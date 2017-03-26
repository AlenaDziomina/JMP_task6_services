package com.grouk.services.rest;

import com.google.common.io.ByteStreams;
import com.grouk.services.exception.factory.PublicExceptionFactory;
import com.grouk.services.manager.UserAccountManager;
import com.grouk.services.model.AvatarImage;
import com.grouk.services.model.UserAccount;
import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
public class UserAccountRestService {
    private final UserAccountManager userAccountManager;
    private final PublicExceptionFactory publicExceptionFactory;

    public UserAccountRestService(@InjectParam UserAccountManager userAccountManager, @InjectParam
            PublicExceptionFactory exceptionFactory) {
        this.userAccountManager = userAccountManager;
        this.publicExceptionFactory = exceptionFactory;
    }

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    public UserAccount getUserAccount(@PathParam("id") Long userId) {
        try {
            return userAccountManager.getUserAccount(userId);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @GET
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserAccount newUserAccountList() {
        try {
            return userAccountManager.getDefaultUserAccount();
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @POST
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateUserAccount(@PathParam("id") Long userId, UserAccount userAccount) {
        try {
            userAccountManager.updateUserAccount(userId, userAccount);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @PUT
    @Path("")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createUserAccount(UserAccount userAccount) {
        try {
            userAccountManager.createUserAccount(userAccount);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteUserAccount(@PathParam("id") Long userId) {
        try {
            userAccountManager.deleteUserAccount(userId);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }

    @GET
    @Path("/{id}/avatar")
    @Produces("image/*")
    public Response getUserAvatar(@PathParam("id") Long userId) {
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
    public void saveUserAvatar(@PathParam("id") Long userId,
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
    public void deleteUserAvatar(@PathParam("id") Long userId) {
        try {
            userAccountManager.deleteUserAvatar(userId);
        } catch (Exception e) {
            throw publicExceptionFactory.createInternalException(e);
        }
    }
}
