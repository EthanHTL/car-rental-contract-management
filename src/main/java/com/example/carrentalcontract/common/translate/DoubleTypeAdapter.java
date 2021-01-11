package com.example.carrentalcontract.common.translate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class DoubleTypeAdapter extends TypeAdapter<Double> {
    public DoubleTypeAdapter() {
    }

    public void write(JsonWriter writer, Double value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else {
            writer.value(value.toString());
        }

    }

    public Double read(JsonReader reader) throws IOException {
        if (reader.peek() == null) {
            return null;
        } else {
            String value = reader.nextString();
            return Double.parseDouble(value);
        }
    }
}
