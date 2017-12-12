/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.speciestypej;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

/**
 *
 * @author Dániel
 */
public class Container<Species> {
    
    private int limit; //hány van egy oldalon
    private int page;   //oldalszám
    private int offset; //melyik az első elem
    private int fullitemnumber; //size()
    
    private List<Species> items = new ArrayList();
    
        @InjectLinks({
            @InjectLink(
                    resource = SpeciesResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    rel = "self"
            ),
            @InjectLink(
                    resource = SpeciesResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${0 <= instance.offset}",
                    bindings = {
                        @Binding(name = "offset", value = "${instance.offset - instance.limit}"),
                        @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "last"
            ),
            @InjectLink(
                    resource = SpeciesResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset + instance.limit < instance.fullitemnumber}",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset + instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "next"
            )        
    })
    @JsonSerialize(using = ListLinkSerializer.class)  
    private List<Link> links;
         
        
    public Container(int pageitemnumber, int startitemnumber, int fullitemnumber) {
        this.limit = pageitemnumber;
        this.offset = startitemnumber;
        this.fullitemnumber = fullitemnumber;
        this.page = this.offset/pageitemnumber +1;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the fullitemnumber
     */
    public int getFullitemnumber() {
        return fullitemnumber;
    }

    /**
     * @param fullitemnumber the fullitemnumber to set
     */
    public void setFullitemnumber(int fullitemnumber) {
        this.fullitemnumber = fullitemnumber;
    }

    /**
     * @return the items
     */
    public List<Species> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Species> items) {
        this.items = items;
    }
    
    public void newElem(Species s)
    {
        this.items.add(s);
    }

    /**
     * @return the links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
