/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.speciestypej;

/**
 *
 * @author Dániel
 */
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Link;
/**
 *
 * @author krisztian
 */
public class ListLinkSerializer extends JsonSerializer<List<Link>>{
/*
    public LinkSerializer() { 
        this(null); 
    } 
 
    public LinkSerializer(Class<Link> t) {
        super(t); 
    }
  */  
    @Override
    public void serialize(List<Link> links, JsonGenerator jg, SerializerProvider sp) 
            throws IOException, JsonProcessingException {
        jg.writeStartArray();
        for(Link l:links){
            jg.writeStartObject();
            jg.writeStringField("rel", l.getRel());
            jg.writeStringField("href", l.getUri().toString());
        jg.writeEndObject();
        }
        jg.writeEndArray();
    }
    
}
