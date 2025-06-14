package com.example.softlearning.applicationcore.entity.order.model;

public enum OrderStatus {
    CREATED,
    CANCELLED,
    CONFIRMED, //aqui esta pagado
    FORTHCOMMING, //PAQUETE PREPARADO
    DELIVERED,
    FINISHED
}
