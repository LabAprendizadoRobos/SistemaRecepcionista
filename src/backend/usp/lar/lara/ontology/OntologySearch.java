/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usp.lar.lara.ontology;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import usp.lar.lara.ontology.LaraParser;
import usp.lar.lara.ontology.Ontologia;

/**
 *
 * @author tarcisio
 */
public class OntologySearch {

    private final Ontologia o = new Ontologia();
    
    public JsonObject search(String pergunta, String entidade, String[] propriedades){
        JsonArray novas_props = new JsonArray();

        ArrayList<ArrayList<String>> parsed = LaraParser.parseOntology( pergunta, this.o );
        if(parsed.get(0).isEmpty()){
            if(entidade != null && entidade.isEmpty()){
                parsed.get(0).add(entidade);
            }
        } else if(parsed.get(0).size() == 1){
            entidade = parsed.get(0).get(0);
        } else {
            entidade = "";
        }
        if (parsed.get(1).isEmpty()){
            if(propriedades != null && propriedades.length > 0){
                for(int i = 0; i < propriedades.length; ++i){
                    novas_props.add(propriedades[i]);
                }
                parsed.get(1).addAll(Arrays.asList(propriedades));
            }
        } else {
            parsed.get(1).forEach(novas_props::add);
        }

        String answer = "";
        if ( !pergunta.isEmpty() ) {
            answer = LaraParser.formatResponse( parsed, this.o );
        }
        
        JsonObject rv = new JsonObject();
        rv.addProperty("answer", answer);
        rv.addProperty("entity", entidade);
        rv.add("properties", novas_props);

        return rv;
    }
}
