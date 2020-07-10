package kr.hs.dgsw.animal_crossingfish_application.data;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NA on 2020-07-04
 * skehdgur8591@naver.com
 */
public class FishData implements Parcelable {
    private Integer id;
    private String name;
    private Integer price;
    private ArrayList<String> place;
    private Boolean onlyRaining;
    private ArrayList<Integer> applyMonths;
    private Uri imageUrl;

    public FishData() {
    }

    protected FishData(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        place = in.createStringArrayList();
        byte tmpOnlyRaining = in.readByte();
        onlyRaining = tmpOnlyRaining == 0 ? null : tmpOnlyRaining == 1;
        imageUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<FishData> CREATOR = new Creator<FishData>() {
        @Override
        public FishData createFromParcel(Parcel in) {
            return new FishData(in);
        }

        @Override
        public FishData[] newArray(int size) {
            return new FishData[size];
        }
    };

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

    public ArrayList<String> getPlace() {
        return place;
    }

    public void setPlace(ArrayList<String> place) {
        this.place = place;
    }

    public Boolean getOnlyRaining() {
        return onlyRaining;
    }

    public void setOnlyRaining(Boolean onlyRaining) {
        this.onlyRaining = onlyRaining;
    }

    public ArrayList<Integer> getApplyMonths() {
        return applyMonths;
    }

    public void setApplyMonths(ArrayList<Integer> applyMonths) {
        this.applyMonths = applyMonths;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = Uri.parse(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(price);
        }
        dest.writeStringList(place);
        dest.writeByte((byte) (onlyRaining == null ? 0 : onlyRaining ? 1 : 2));
        dest.writeParcelable(imageUrl, flags);
    }
}
