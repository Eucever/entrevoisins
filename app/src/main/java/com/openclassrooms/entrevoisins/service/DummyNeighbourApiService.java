package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {return neighbours;}

    public void setFavourite(long id, boolean bool){
        for(Neighbour n: neighbours){
           if(n.getId()==id) {
               n.setFavourite(bool);
               break;
           }
        }
    }


    public List<Neighbour> getFavoriteNeighbours(){
        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        for(Neighbour n: neighbours) {
            if (n.isFavourite()){
                favoriteNeighbours.add(n);
            }
        }
        return favoriteNeighbours;
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }
}
