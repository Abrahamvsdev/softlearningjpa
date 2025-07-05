package com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers;

import java.util.TreeMap;

import com.example.softlearning.applicationcore.entity.book.dtos.EspanolBookDTO;
import com.example.softlearning.applicationcore.entity.book.dtos.JapaneseBookDTO;
import com.example.softlearning.applicationcore.entity.client.dtos.EspanolClientDTO;
import com.example.softlearning.applicationcore.entity.client.dtos.JapaneseClientDTO;
import com.example.softlearning.applicationcore.entity.order.dtos.JapaneseOrderDTO;

public class SerializersCatalog {

    private static TreeMap<Serializers, Serializer > catalog = new TreeMap<>();

    private static void loadCatalog(){
        //AL CREAR UNA INSTANCIA DE CADA SERIALIZADOR, SE AÑADE AL CATALOGO

        // JapaneseClient
        catalog.put(Serializers.JSON_CLIENT,new JacksonSerializer<JapaneseClientDTO>());
        catalog.put(Serializers.XML_CLIENT,new JacksonXmlSerializer<JapaneseClientDTO>());

        // EspañolClient
        catalog.put(Serializers.JSON_ESPA_CLIENT,new JacksonSerializer<EspanolClientDTO>());        
        catalog.put(Serializers.XML_ESPA_CLIENT,new JacksonXmlSerializer<EspanolClientDTO>());

        // JapaneseBook
        catalog.put(Serializers.JSON_BOOK,new JacksonSerializer<JapaneseBookDTO>());
        catalog.put(Serializers.XML_BOOK,new JacksonXmlSerializer<JapaneseBookDTO>());

        // EspañolBook
        catalog.put(Serializers.JSON_ESPA_BOOK,new JacksonSerializer<EspanolBookDTO>());
        catalog.put(Serializers.XML_ESPA_BOOK,new JacksonXmlSerializer<EspanolBookDTO>());

        // JapaneseOrder
        catalog.put(Serializers.JSON_ORDER,new JacksonSerializer<JapaneseOrderDTO>());
        catalog.put(Serializers.XML_ORDER,new JacksonXmlSerializer<JapaneseOrderDTO>());

        // EspañolOrder
        catalog.put(Serializers.JSON_ESPA_ORDER,new JacksonSerializer<JapaneseBookDTO>());
        catalog.put(Serializers.XML_ESPA_ORDER,new JacksonXmlSerializer<JapaneseBookDTO>());
    }
    public static Serializer getInstance(Serializers type){
        if(catalog.isEmpty()){
            loadCatalog();
        }
        return catalog.get(type);
    }
}
