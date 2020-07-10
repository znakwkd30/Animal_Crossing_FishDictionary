package kr.hs.dgsw.animal_crossingfish_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import kr.hs.dgsw.animal_crossingfish_application.data.FishData;
import kr.hs.dgsw.animal_crossingfish_application.databinding.ActivityFishDetailBinding;
import kr.hs.dgsw.animal_crossingfish_application.databinding.ActivityMainBinding;

public class FishDetailActivity extends AppCompatActivity {

    private ActivityFishDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFishDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        FishData fishData = intent.getExtras().getParcelable("fishInfo");

        Glide.with(getApplicationContext()).load(fishData.getImageUrl()).into(binding.fishImageView);
        binding.fishNameTextView.setText(fishData.getName());
        binding.fishPriceTextView.setText("가격 - " + fishData.getPrice().toString()  + "벨");

        // 장소 SET
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fishData.getPlace().size(); i ++) {
            switch (fishData.getPlace().get(i)) {
                case "river" : sb.append("강가" + " "); break;
                case "pond" : sb.append("연못" + " "); break;
                case "clifftop" : sb.append("절벽 위" + " "); break;
                case "mouth" : sb.append("하구" + " "); break;
                case "ocean" : sb.append("바다" + " "); break;
                case "pier" : sb.append("부둣가" + " "); break;
            }
//            sb.append(fishData.getPlace().get(i) + " ");
        }
        binding.fishPlaceTextView.setText("장소 - " + sb);

        // 비가 와야하나요??????
        if (fishData.getOnlyRaining()) {
            binding.fishRainTextView.setText("O");
        }

    }
}