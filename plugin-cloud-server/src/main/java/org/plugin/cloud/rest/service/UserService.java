package org.plugin.cloud.rest.service;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.UserMaster;
import org.plugin.cloud.db.dao.UserMasterDAOImpl;
import org.plugin.cloud.request.Error;
import org.plugin.cloud.request.MasterUser;
import org.plugin.cloud.request.Success;
import org.plugin.cloud.request.User;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/user")
public class UserService {

	@Autowired
	UserMasterDAOImpl userMasterDAOImpl;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response isValidUser(User user) {
		UserMaster validUser = userMasterDAOImpl.isValidUser(user.getUserName(), user.getUserKey());
        return (validUser != null) ? Response.status(200).entity(validUser).build():Response.status(200).entity(new Error("User is not Valid.")).build();
    }

	@POST
	@Path("/registerUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(MasterUser user) {
		UserMaster userMaster = new UserMaster();
		userMaster.setDisplayName(user.getDisplayName());
		userMaster.setUserKey(user.getUserKey());
		userMaster.setUserName(user.getUserName());
		userMaster.setUserType(user.getUserType());
		userMaster.setUserStatus(Boolean.TRUE);
		Boolean createUser = userMasterDAOImpl.createUser(userMaster);

        return (createUser) ? Response.status(200).entity(new Success("User is been added successfully.", "200")).build():Response.status(200).entity(new Error("User is not Valid.")).build();
    }

	@GET
	@Path("/getUser/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(@PathParam("userID") String userID) {
		List<UserMaster> userMaster = userID.equals("ALL")?userMasterDAOImpl.getAllUsers():userMasterDAOImpl.getUser(userID);

		return (userMaster != null && userMaster.size()>0) ? Response.status(200).entity(userMaster).build():Response.status(200).entity(new Error("User is not Available.")).build();
    }

}