package com.grouk.services.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.grouk.services.service.UserAccountService;
import com.sun.jersey.api.core.InjectParam;

/**
 * User Account Rest Service
 * Created by Alena_Grouk on 3/22/2017.
 */
@Path("/user")
public class UserAccountRestService {

    @Context
    UriInfo uriInfo;

    private final UserAccountService userAccountService;

    public UserAccountRestService(@InjectParam UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GET
    @Path("/")
    public Response getUserAccountList() {
        String output =
                "User Account List" + uriInfo + "/n" + userAccountService + "/n" + userAccountService.uriInfo + "/n"
                        + userAccountService.userAvatarDao + "/n" + userAccountService.userProfileDao;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserAccount(@PathParam("id") Long userId) {
        String output = "User Account  : " + userId;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/create")
    public Response newUserAccountList() {
        String output = "NEW User Account List";
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/{id}")
    public Response updateUserAccount(@PathParam("id") Long userId) {
        String output = "Update User Account  : " + userId;
        return Response.status(200).entity(output).build();
    }

    @PUT
    @Path("/")
    public Response createUserAccount() {
        String output = "Create User Account";
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUserAccount(@PathParam("id") Long userId) {
        String output = "Delete User Account: " + userId;
        return Response.status(200).entity(output).build();
    }
}
