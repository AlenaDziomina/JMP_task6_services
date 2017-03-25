package com.grouk.services.rest;

import com.grouk.services.exception.ExceptionFactory;
import com.grouk.services.model.UserAccount;
import com.grouk.services.service.UserAccountService;
import com.sun.jersey.api.core.InjectParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * User Account Rest Service
 * Created by Alena_Grouk on 3/22/2017.
 */
@Path("/user")
public class UserAccountRestService {
    private final UserAccountService userAccountService;
    private final ExceptionFactory exceptionFactory;

    public UserAccountRestService(@InjectParam UserAccountService userAccountService, @InjectParam ExceptionFactory
            exceptionFactory) {
        this.userAccountService = userAccountService;
        this.exceptionFactory = exceptionFactory;
    }

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<UserAccount> getUserAccountList() {
        try {
            return userAccountService.getUserAccountList();
        } catch (Exception e) {
            throw exceptionFactory.createInternalException(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserAccount getUserAccount(@PathParam("id") Long userId) {
        try {
            return userAccountService.getUserAccount(userId);
        } catch (Exception e) {
            throw exceptionFactory.createInternalException(e);
        }
    }

    @GET
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserAccount newUserAccountList() {
        try {
            return userAccountService.getDefaultUserAccount();
        } catch (Exception e) {
            throw exceptionFactory.createInternalException(e);
        }
    }

    @POST
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateUserAccount(@PathParam("id") Long userId, UserAccount userAccount) {
        try {
            userAccountService.updateUserAccount(userId, userAccount);
        } catch (Exception e) {
            throw exceptionFactory.createInternalException(e);
        }
    }

    @PUT
    @Path("")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createUserAccount(UserAccount userAccount) {
        try {
            userAccountService.createUserAccount(userAccount);
        } catch (Exception e) {
            throw exceptionFactory.createInternalException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteUserAccount(@PathParam("id") Long userId) {
        try {
            userAccountService.deleteUserAccount(userId);
        } catch (Exception e) {
            throw exceptionFactory.createInternalException(e);
        }
    }
}
