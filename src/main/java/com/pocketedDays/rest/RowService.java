package com.pocketedDays.rest;

import com.pocketedDays.entity.Row;
import com.pocketedDays.persistence.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Row service.
 */
@Path("/rows")
public class RowService {
    /**
     * The Row dao.
     */
    GenericDao rowDao = new GenericDao(Row.class);

    /**
     * Hello response.
     *
     * @return the response
     */
    @GET
    @Produces("text/html")
    public Response hello() {
        StringBuilder responseHTML = new StringBuilder("<h1>Welcome from pocketedDays api</h1>");
        responseHTML.append("<html><body><h2>Getting all rows</h2><ul>");
        responseHTML.append("<li>To get all rows as json: https://pocketeddays-env.eba-znfy6w8h.us-east-2.elasticbeanstalk.com/report/rows/json/</li>");
        responseHTML.append("<li>To get all rows as text: https://pocketeddays-env.eba-znfy6w8h.us-east-2.elasticbeanstalk.com/report/rows/text/</li>");
        responseHTML.append("<li>To get all rows as table of html: https://pocketeddays-env.eba-znfy6w8h.us-east-2.elasticbeanstalk.com/report/rows/rowTable/</li></ul>");
        responseHTML.append("<h2>Getting row by id</h2><ul>");
        responseHTML.append("<li>To get rows by id as json: https://pocketeddays-env.eba-znfy6w8h.us-east-2.elasticbeanstalk.com/report/rows/json/rowId</li>");
        responseHTML.append("<li>To get rows by id as text: https://pocketeddays-env.eba-znfy6w8h.us-east-2.elasticbeanstalk.com/report/rows/text/rowId</li>");
        responseHTML.append("<li>To get rows by id as table of html: https://pocketeddays-env.eba-znfy6w8h.us-east-2.elasticbeanstalk.com/report/rows/rowTable/rowId</li>");
        responseHTML.append("</ul><p>Put rowId of yours in the place of rowId in url.</p></body></html>");
        String response = responseHTML.toString();
        return Response.status(200).entity(response).build();
    }

    /**
     * Gets json all rows.
     *
     * @return the json all rows
     */
    @GET
    @Path("/json/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSONAllRows() {
        List<Row> rows = (List<Row>) rowDao.getAll();
        if (rows.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("There are no rows yet.").build();
        }
        return Response.status(200).entity(rows.toString()).build();
    }

    /**
     * Gets text all rows.
     *
     * @return the text all rows
     */
    @GET
    @Path("/text/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTextAllRows() {
        List<Row> rows = (List<Row>) rowDao.getAll();
        if (rows.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("There are no rows yet.").build();
        }
        return Response.status(200).entity(rows.toString()).build();
    }

    /**
     * Gets table of rows.
     *
     * @return the table of rows
     */
    @GET
    @Path("/rowTable/")
    @Produces("text/html")
    public Response getTableOfRows() {
        StringBuilder responseHTML = new StringBuilder("<html><body><table><tr><td>RowId</td><td>Description</td><td>Updated Date</td><td>Quantity</td><td>Cost Per Item</td><td>Type</td><td>Tag</td></tr>");
        List<Row> rows = (List<Row>) rowDao.getAll();
        if (rows.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("There are no rows yet.").build();
        }
        for (Row row : rows) {
            responseHTML.append(getTableRowOfRow(row));
        }
        responseHTML.append("</table></body></html>");
        String response = responseHTML.toString();
        return Response.status(200).entity(response).build();
    }

    /**
     * Gets json row by id.
     *
     * @param rowId the row id
     * @return the json row by id
     */
    @GET
    @Path("/json/{rowId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSONRowById(@PathParam("rowId") int rowId) {
        Row row = (Row) rowDao.getById(rowId);
        if (row == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Row by the id of " + rowId + " is not found.").build();
        }
        return Response.status(200).entity(row.toString()).build();
    }

    /**
     * Gets text row by id.
     *
     * @param rowId the row id
     * @return the text row by id
     */
    @GET
    @Path("/text/{rowId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTextRowById(@PathParam("rowId") int rowId) {
        Row row = (Row) rowDao.getById(rowId);
        if (row == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Row by the id of " + rowId + " is not found.").build();
        }
        return Response.status(200).entity(row.toString()).build();
    }

    /**
     * Gets table of rows by id.
     *
     * @param rowId the row id
     * @return the table of rows by id
     */
    @GET
    @Path("/rowTable/{rowId}")
    @Produces("text/html")
    public Response getTableOfRowsById(@PathParam("rowId") int rowId) {
        StringBuilder responseHTML = new StringBuilder("<html><body><table><tr><td>RowId</td><td>Description</td><td>Updated Date</td><td>Quantity</td><td>Cost Per Item</td><td>Type</td><td>Tag</td></tr>");
        Row row = (Row) rowDao.getById(rowId);
        if (row == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no row with the id of " + rowId).build();
        }
        responseHTML.append(getTableRowOfRow(row));
        responseHTML.append("</table></body></html>");
        String response = responseHTML.toString();
        return Response.status(200).entity(response).build();
    }

    /**
     * Gets table row of row.
     *
     * @param row the row
     * @return the table row of row
     */
    public String getTableRowOfRow(Row row) {
        StringBuilder responseRow = new StringBuilder();
        responseRow.append("<tr><td>").append(row.getRowId());
        responseRow.append("</td><td>").append(row.getRowDescription());
        responseRow.append("</td><td>").append(row.getUpdatedDate());
        responseRow.append("</td><td>").append(row.getQuantity());
        responseRow.append("</td><td>").append(row.getCostPerItem());
        responseRow.append("</td><td>").append(row.getRowType());
        responseRow.append("</td><td>").append(row.getTag());
        responseRow.append("</td></tr>");
        String response = responseRow.toString();
        return response;
    }
}