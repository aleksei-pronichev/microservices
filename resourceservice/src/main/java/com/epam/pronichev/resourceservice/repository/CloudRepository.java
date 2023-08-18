package com.epam.pronichev.resourceservice.repository;


public interface CloudRepository {

    void save(String key, byte[] data);

    byte[] get(String key);
}
