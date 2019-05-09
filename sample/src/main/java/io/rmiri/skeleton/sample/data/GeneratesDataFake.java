package io.rmiri.skeleton.sample.data;

import java.util.ArrayList;

import io.rmiri.skeleton.sample.R;

/**
 * Created by Rasoul Miri on 8/23/17.
 */

public class GeneratesDataFake {

    private final ArrayList<DataObject> dataObjects = new ArrayList<>();


    public GeneratesDataFake() {
    }

    private final ArrayList<String> titles = new ArrayList<String>() {
        {
            add("Chevrolet Camaro ZL1");
            add("Porsche Macan");
            add("Audi Q7 2.0T");
            add("Range Rover Velar");
            add("Mazda 3 ");
            add("Honda Accord");
            add("Chevrolet Camaro ZL1");
            add("Porsche Macan");
            add("Audi Q7 2.0T");
            add("Range Rover Velar");
            add("Mazda 3 ");
            add("Honda Accord");
            add("Chevrolet Camaro ZL1");
            add("Porsche Macan");
            add("Audi Q7 2.0T");
            add("Range Rover Velar");
            add("Mazda 3 ");
            add("Honda Accord");
        }
    };
    private final ArrayList<String> descriptions = new ArrayList<String>() {
        {
            add("Chevrolet Camaro ZL1");
            add("Porsche Macan");
            add("Audi Q7 2.0T");
            add("Range Rover Velar");
            add("Mazda 3 ");
            add("Honda Accord");
            add("Chevrolet Camaro ZL1");
            add("Porsche Macan");
            add("Audi Q7 2.0T");
            add("Range Rover Velar");
            add("Mazda 3 ");
            add("Honda Accord");
            add("Chevrolet Camaro ZL1");
            add("Porsche Macan");
            add("Audi Q7 2.0T");
            add("Range Rover Velar");
            add("Mazda 3 ");
            add("Honda Accord");
        }
    };

    private final ArrayList<Integer> photos = new ArrayList<Integer>() {
        {
            add(R.drawable.photo_chevrolet);
            add(R.drawable.photo_porsch);
            add(R.drawable.photo_audi);
            add(R.drawable.photo_range_rover);
            add(R.drawable.photo_mazda_3);
            add(R.drawable.photo_honda);
            add(R.drawable.photo_chevrolet);
            add(R.drawable.photo_porsch);
            add(R.drawable.photo_audi);
            add(R.drawable.photo_range_rover);
            add(R.drawable.photo_mazda_3);
            add(R.drawable.photo_honda);
            add(R.drawable.photo_chevrolet);
            add(R.drawable.photo_porsch);
            add(R.drawable.photo_audi);
            add(R.drawable.photo_range_rover);
            add(R.drawable.photo_mazda_3);
            add(R.drawable.photo_honda);
        }
    };


    private final ArrayList<String> prices = new ArrayList<String>() {
        {
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
            add("240000");
        }
    };

    public ArrayList<DataObject> generateDataFake() {
        for (int i = 0; i < 12; i++) {
            DataObject dataObject = new DataObject();
            dataObject.setId(i);
            dataObject.setTitle(titles.get(i));
            dataObject.setDescription(descriptions.get(i));
            dataObject.setPhoto(photos.get(i));
            dataObject.setPrice(prices.get(i));
            dataObject.setNew(true);
            dataObjects.add(dataObject);
        }
        return dataObjects;
    }
}
