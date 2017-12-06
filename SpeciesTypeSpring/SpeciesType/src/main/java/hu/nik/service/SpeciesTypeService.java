package hu.nik.service;

import hu.nik.model.SpeciesType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpeciesTypeService {


    private ArrayList<SpeciesType> speciesTypeList;
    private Long nextId;

    public SpeciesTypeService() {
        speciesTypeList = new ArrayList<SpeciesType>();

        speciesTypeList.add(new SpeciesType(1L, "spiderman", "hero with spider skills!"));
        speciesTypeList.add(new SpeciesType(2L, "batman", "super hero who can fly!"));
        speciesTypeList.add(new SpeciesType(3L, "wizard", "magic!"));
        speciesTypeList.add(new SpeciesType(4L, "witch", "magic!"));

        nextId = 5L;
    }

    public ArrayList<SpeciesType> getSpeciesTypes() {
        return speciesTypeList;
    }

    public SpeciesType getSpeciesType(Long id) {
        for (SpeciesType st : speciesTypeList )
        {
            if (st.getId() == id)
                return st;
        }
        return null;
    }


    public boolean deleteSpeciesType(Long id) {
        SpeciesType st = getSpeciesType(id);
        if (st != null) {
            speciesTypeList.remove(st);
            return true;
        }
        return false;
    }

    public SpeciesType createSpeciesType(String name, String description) {
        SpeciesType st = new SpeciesType(nextId, name, description);
        speciesTypeList.add(st);
        nextId++;
        return st;
    }

}
