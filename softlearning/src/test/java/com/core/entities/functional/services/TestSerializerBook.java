package com.core.entities.functional.services;
import com.example.softlearning.applicationcore.entity.book.dtos.JapaneseBookDTO;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializer;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializers;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.SerializersCatalog;

public class TestSerializerBook {
    public static void main(String[] args) {
                    
        JapaneseBookDTO japaneseBookTest = new JapaneseBookDTO( 
            "ident", 
            50.0, 
            true, 
            0.0, 
            "type", 
            "payMethod", 
            "2023/11/02-10:00:01", 
            "author", 
            "isbn", 
            "cover", 
            1001, 
            "genre", 
            "editorial", 
            10.0,
            10.0, 
            10.0, 
            true, 
            10.0, 
            10.0);
        try {
            System.out.println("JapaneseBookDTO Serializer: \n");
            Serializer<JapaneseBookDTO> formatter = SerializersCatalog.getInstance(Serializers.JSON_BOOK);
            String json = formatter.serialize(japaneseBookTest);
            System.out.println(json);

            System.out.println("\n JapaneseBookDTO Deserializer: \n");
            JapaneseBookDTO japaneseBookDeserialized =(JapaneseBookDTO) formatter.deserialize(json, JapaneseBookDTO.class);
            System.out.println(japaneseBookDeserialized);
            System.out.println("------------------------\n");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    
    }
}
