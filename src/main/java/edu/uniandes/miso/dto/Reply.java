package edu.uniandes.miso.dto;

import javax.ws.rs.core.Response;

public class Reply {

    public static Response ok(Object object){
        return Response.status(Response.Status.OK).entity(new ResponseService("success",true,object)).build();
    }
    public static Response created(Object object){
        return Response.status(Response.Status.CREATED).entity(new ResponseService("created",true,object)).build();
    }

    public static Response notFound(Object object){
        return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseService("Not found or fail",false,object)).build();
    }
}
