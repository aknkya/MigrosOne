package com.example.demo.service;

import com.example.demo.constant.DataPaths;
import com.example.demo.model.StoreInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoresService {
    private final Gson gson;

    public StoresService(Gson gson) {
        this.gson = gson;
    }
    // Function to retrieve the list of stores from a file
    public List<StoreInfo> getStores() throws IOException {
        List<StoreInfo> stores  = parseStoresFromFile(DataPaths.STORE_DATA_PATH);
        return stores;
    }
    // Private helper function to parse store information from a JSON file
    private static List<StoreInfo> parseStoresFromFile(String filePath) {
        List<StoreInfo> stores = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                StoreInfo store = new Gson().fromJson(element, StoreInfo.class);
                stores.add(store);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stores;
    }
}
