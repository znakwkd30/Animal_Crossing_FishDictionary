package kr.hs.dgsw.animal_crossingfish_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import kr.hs.dgsw.animal_crossingfish_application.data.FishData;
import kr.hs.dgsw.animal_crossingfish_application.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<FishData> fishDataList = new ArrayList<>();
    private FishListAdapter fishListAdapter;
    SearchView searchView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTabs();

        binding.tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                binding.recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5));
                switch (pos) {
                    case 0: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setAllJSONData(), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 1: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(1), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 2: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(2), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 3: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(3), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 4: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(4), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 5: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(5), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 6: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(6), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 7: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(7), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 8: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(8), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 9: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(9), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 10: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(10), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 11: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(11), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                    case 12: {
                        fishListAdapter = new FishListAdapter((ArrayList<FishData>) setJSONData(12), getApplicationContext());
                        binding.recyclerview.setAdapter(fishListAdapter);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String requiredPermission = Manifest.permission.RECORD_AUDIO;

            // If the user previously denied this permission then show a message explaining why
            // this permission is needed
            if (checkCallingOrSelfPermission(requiredPermission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{requiredPermission}, 101);
            }
        }

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.search);
        MenuItem mVoiceSearch = menu.findItem(R.id.voice);

        final SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.onActionViewExpanded();
        searchView.setQueryHint("물고기 이름을 입력해주세요");
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fishListAdapter = new FishListAdapter((ArrayList<FishData>) setSearchJSONData(newText), getApplicationContext());
                binding.recyclerview.setAdapter(fishListAdapter);
                return true;
            }
        });

        searchView2 = (SearchView) mVoiceSearch.getActionView();
        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mVoiceSearch.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
                speechRecognizer.setRecognitionListener(listener);
                speechRecognizer.startListening(intent);

                return false;
            }
        });

        return true;
    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(MainActivity.this, "음성인식을 시작합니다.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {
            Toast.makeText(MainActivity.this, "Err Code" + error, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for (int i = 0; i < matches.size(); i++) {
                searchView2.setQuery(matches.get(i), true);
            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        TabLayout.Tab tab = binding.tabs.getTabAt(2);
        tab.select();
        tab = binding.tabs.getTabAt(0);
        tab.select();
    }

    private void setTabs() {
        binding.tabs.addTab(binding.tabs.newTab().setText("전체"));
        binding.tabs.addTab(binding.tabs.newTab().setText("1월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("2월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("3월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("4월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("5월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("6월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("7월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("8월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("9월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("10월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("11월"));
        binding.tabs.addTab(binding.tabs.newTab().setText("12월"));
    }

    private List<FishData> setJSONData(int applyMonth) {
        AssetManager assetManager = getAssets();

        try {
            fishDataList.clear();
            InputStream is = assetManager.open("fish_data.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i ++) {
                boolean apply = false;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                FishData fishData = new FishData();
                fishData.setId(jsonObject.getInt("id"));
                fishData.setName(jsonObject.getString("name"));

                ArrayList<Integer> index = new ArrayList<>();
                if (jsonObject.getJSONArray("applyMonths") != null) {
                    for (int j = 0; j < jsonObject.getJSONArray("applyMonths").length(); j ++) {
                        index.add((Integer) jsonObject.getJSONArray("applyMonths").get(j));
                        if (applyMonth - 1 == (Integer) jsonObject.getJSONArray("applyMonths").get(j)) {
                            apply = true;
                        }
                    }
                }
                fishData.setApplyMonths(index);
                fishData.setPrice(jsonObject.getInt("price"));


                // ADD
                fishData.setPrice(jsonObject.getInt("price"));
                fishData.setOnlyRaining(jsonObject.getBoolean("onlyRaining"));
                ArrayList<String> placeIndex = new ArrayList<>();
                for (int j = 0; j < jsonObject.getJSONArray("place").length(); j ++) {
                    placeIndex.add((String) jsonObject.getJSONArray("place").get(j));
                }
                fishData.setPlace(placeIndex);

                int resID = getResId(jsonObject.getString("imageUrl"), R.drawable.class);
                fishData.setImageUrl("android.resource://" + R.class.getPackage().getName()  + "/drawable/" + resID);

                if (apply) {
                    fishDataList.add(fishData);
                }
            }
            return fishDataList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<FishData> setAllJSONData() {
        AssetManager assetManager = getAssets();
        try {
            fishDataList.clear();
            InputStream is = assetManager.open("fish_data.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i ++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                FishData fishData = new FishData();
                fishData.setId(jsonObject.getInt("id"));
                fishData.setName(jsonObject.getString("name"));
                // TODO
                ArrayList<Integer> index = new ArrayList<>();
                if (jsonObject.getJSONArray("applyMonths") != null) {
                    for (int j = 0; j < jsonObject.getJSONArray("applyMonths").length(); j ++) {
                        index.add((Integer) jsonObject.getJSONArray("applyMonths").get(j));
                    }
                }

                // ADD
                fishData.setPrice(jsonObject.getInt("price"));
                fishData.setOnlyRaining(jsonObject.getBoolean("onlyRaining"));
                ArrayList<String> placeIndex = new ArrayList<>();
                for (int j = 0; j < jsonObject.getJSONArray("place").length(); j ++) {
                    placeIndex.add((String) jsonObject.getJSONArray("place").get(j));
                }
                fishData.setPlace(placeIndex);

                fishData.setApplyMonths(index);
                int resID = getResId(jsonObject.getString("imageUrl"), R.drawable.class);
                fishData.setImageUrl("android.resource://" + R.class.getPackage().getName()  + "/drawable/" + resID);

                fishDataList.add(fishData);
            }
            return fishDataList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<FishData> setSearchJSONData(String searchName) {
        AssetManager assetManager = getAssets();

        try {
            fishDataList.clear();
            InputStream is = assetManager.open("fish_data.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                boolean apply = false;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                FishData fishData = new FishData();
                fishData.setId(jsonObject.getInt("id"));
                fishData.setName(jsonObject.getString("name"));

                if(fishData.getName().toLowerCase().contains(searchName)) {
                    apply = true;
                }

                // TODO
                ArrayList<Integer> index = new ArrayList<>();
                if (jsonObject.getJSONArray("applyMonths") != null) {
                    for (int j = 0; j < jsonObject.getJSONArray("applyMonths").length(); j ++) {
                        index.add((Integer) jsonObject.getJSONArray("applyMonths").get(j));
                    }
                }
                fishData.setApplyMonths(index);

                // ADD
                fishData.setPrice(jsonObject.getInt("price"));
                fishData.setOnlyRaining(jsonObject.getBoolean("onlyRaining"));
                ArrayList<String> placeIndex = new ArrayList<>();
                for (int j = 0; j < jsonObject.getJSONArray("place").length(); j ++) {
                    placeIndex.add((String) jsonObject.getJSONArray("place").get(j));
                }
                fishData.setPlace(placeIndex);
                int resID = getResId(jsonObject.getString("imageUrl"), R.drawable.class);
                fishData.setImageUrl("android.resource://" + R.class.getPackage().getName()  + "/drawable/" + resID);

                if (apply) {
                    fishDataList.add(fishData);
                }
            }

            return fishDataList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}