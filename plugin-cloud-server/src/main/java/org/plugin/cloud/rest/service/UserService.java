package org.plugin.cloud.rest.service;

import java.nio.charset.Charset;
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

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

@Path("/user")
public class UserService {
	private static final String DEFAULT_STABLE_PRODUCT_VERSION="1.0";
	
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
		userMaster.setUserEmail(user.getUserEmail());
		userMaster.setUserType(user.getUserType());
		userMaster.setUserStatus(Boolean.TRUE);
		userMaster.setAddress1(user.getAddress1());
		userMaster.setAddress2(user.getAddress2());
		userMaster.setCity(user.getCity());
		userMaster.setState(user.getState());
		userMaster.setCountry("IN");
		userMaster.setPincode(user.getPincode());
		
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
	
	@GET
	@Path("/getUserByName/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response getLikeUser(@PathParam("userName") String userName) {
		List<UserMaster> userMaster = userMasterDAOImpl.getUserLike(userName);
		List<String> userNameList = new LinkedList<>();
		for (UserMaster user : userMaster) {
			userNameList.add(user.getUserName());
		}
		return (userNameList != null && userNameList.size()>0) ? Response.status(200).entity(userNameList).build():Response.status(200).entity(new Error("User is not Available.")).build();
    }
	@GET
	@Path("/createLicense/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response createLicenseUser(@PathParam("userID") String userID) {
		List<UserMaster> userMasterList = userID.equals("ALL")?userMasterDAOImpl.getAllUsers():userMasterDAOImpl.getUser(userID);
		UserMaster userMaster = (userMasterList != null && userMasterList.size()>0) ? userMasterList.get(0):null;
		if(userMaster != null ) {
			if(userMaster.getUserLicense() == null || userMaster.getUserLicense().trim().equals("")) {
				String userLicense = createLicenseKey(userMaster.getUserName(), userMaster.getUserEmail(), DEFAULT_STABLE_PRODUCT_VERSION);
				userMaster.setUserLicense(userLicense);
				if(userMasterDAOImpl.updateUserLicense(userID, userLicense))
					return Response.status(200).entity(userMaster).build();
				else
					return Response.status(204).entity(new Error("Not Able to Update License...")).build();
			}
			else {
				return Response.status(200).entity(userMaster).build();
			}
		}
		else
			return Response.status(204).entity(new Error("User is not Available or already has License")).build();
    }
	private String createLicenseKey(String userName, String productKey, String versionNumber) {
		final String s = userName + "|" + productKey + "|" + versionNumber;
		final HashFunction hashFunction = Hashing.sha1();
		final HashCode hashCode = hashFunction.hashString(s, Charset.forName("UTF-8"));
		final String upper = hashCode.toString().toUpperCase();
		return group(upper);
	}
	private String group(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (i % 6 == 0 && i > 0) {
				result += '-';
			}
			result += s.charAt(i);
		}
		return result;
	}

}