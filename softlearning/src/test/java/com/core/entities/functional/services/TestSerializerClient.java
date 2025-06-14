package com.core.entities.functional.services;
import com.example.softlearning.applicationcore.entity.client.dtos.JapaneseClientDTO;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializer;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializers;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.SerializersCatalog;

public class TestSerializerClient {
    public static void main(String[] args) {
                    
        JapaneseClientDTO japaneseClientTest = new JapaneseClientDTO(
            "Pedro", 
            "Medario", 
            "email@email.com", 
            "address", 
            "123456789G", 
            "890300499",
            12, 
            "Credit Card", 
            "asd", 
            "02-03-1990");
        try {
            System.out.println("JapaneseClientDTO Serializer: \n");
            Serializer<JapaneseClientDTO> formatter = SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
            String json = formatter.serialize(japaneseClientTest);
            System.out.println(json);

            System.out.println("\n JapaneseClientDTO Deserializer: \n");
            JapaneseClientDTO japaneseClientDeserialized =(JapaneseClientDTO) formatter.deserialize(json, JapaneseClientDTO.class);
            System.out.println(japaneseClientDeserialized);
            System.out.println("------------------------\n");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    
    }
}
