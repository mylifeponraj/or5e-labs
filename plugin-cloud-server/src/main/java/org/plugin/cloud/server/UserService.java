package org.plugin.cloud.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.plugin.cloud.db.UserMaster;
import org.plugin.cloud.db.dao.UserMasterDAOImpl;
import org.plugin.cloud.request.Error;
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
}