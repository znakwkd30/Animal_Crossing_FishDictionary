package kr.hs.dgsw.animal_crossingfish_application.data;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NA on 2020-07-04
 * skehdgur8591@naver.com
 */
public class FishData {
    private Integer id;
    private String name;
    private Integer price;
    private ArrayList<Integer> applyMonths;
    private Uri imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ArrayList<Integer> getApplyMonths() {
        return applyMonths;
    }

    public void setApplyMonths(ArrayList<Integer> applyMonths) {
//        for (int i = 0; i < applyMonths.length(); i ++) {
//            try {
//                this.applyMonths = (ArrayList<Integer>) applyMonths.get(i);
////                this.applyMonths.set(i, (Integer) applyMonths.get(i));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        this.applyMonths = applyMonths;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = Uri.parse(imageUrl);
    }
}
