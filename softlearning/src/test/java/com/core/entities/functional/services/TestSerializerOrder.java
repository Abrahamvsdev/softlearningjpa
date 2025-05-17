package com.core.entities.functional.services;
import com.example.softlearning.applicationcore.entity.order.dtos.JapaneseOrderDTO;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializer;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializers;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.SerializersCatalog;

public class TestSerializerOrder {
    public static void main(String[] args) {
                    
        JapaneseOrderDTO japaneseOrderTest = new JapaneseOrderDTO(
                
        1234,
        "description",
        "2023/11/02-10:00:10",
        "calle falsa 123",
        "Pedro Medario",
        "123456789",
        "ID1234",
        "2024/11/02-10:00:10",
        "2023/11/02-10:00:10",
        "2024/11/02-10:00:10",
        11.0,
        10.0,
        10.0,
        true,
        10.0,
        "amount:2,ref:REF001,price:10.0,discount:5.0;amount:1,ref:REF002,price:20.0,discount:0.0");
        try {
            System.out.println("JapaneseOrderDTO Serializer: \n");
            Serializer<JapaneseOrderDTO> formatter = SerializersCatalog.getInstance(Serializers.JSON_ORDER);
            String json = formatter.serialize(japaneseOrderTest);
            System.out.println(json);

            System.out.println("\n JapaneseOrderDTO Deserializer: \n");
            JapaneseOrderDTO japaneseOrderDeserialized =(JapaneseOrderDTO) formatter.deserialize(json, JapaneseOrderDTO.class);
            System.out.println(japaneseOrderDeserialized);
            System.out.println("------------------------\n");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    
    }
}
