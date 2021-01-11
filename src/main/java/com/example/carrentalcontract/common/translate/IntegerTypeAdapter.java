package com.example.carrentalcontract.common.translate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class IntegerTypeAdapter extends TypeAdapter<Integer> {
    public IntegerTypeAdapter() {
    }

    public void write(JsonWriter writer, Integer value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else {
            writer.value((long)value);
        }

    }

    public Integer read(JsonReader reader) throws IOException {
        if (reader.peek() == null) {
            return null;
        } else {
            String value = reader.nextString();
            return Integer.parseInt(value);
        }
    }
}