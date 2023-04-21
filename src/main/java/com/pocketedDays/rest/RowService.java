package com.pocketedDays.rest;

import com.pocketedDays.entity.Row;
import com.pocketedDays.persistence.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rows")
public class RowService {
    GenericDao rowDao = new GenericDao(Row.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageByName() {
        //Row row = (Row) rowDao.getById(rowId);
        List<Row> rows = (List<Row>) rowDao.getAll();
        return Response.status(200).entity(rows.toString()).build();
    }

    @GET
    @Path("{rowId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWordByDate(@PathParam("rowId") int rowId) {
        Row row = (Row) rowDao.getById(rowId);
        return Response.status(200).entity(row.toString()).build();
    }
}