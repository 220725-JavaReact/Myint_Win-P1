package com.revature.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revature.dao.Dao;
import com.revature.models.Customer;
import com.revature.models.LineItem;
import com.revature.models.Store;

public class StoreService {
	private Dao<Store> storeDao;

    public StoreService(Dao<Store> storeDao) {
        this.storeDao = storeDao;
    }
    public Store replenishStoreInventory(Store store){
        return storeDao.updateInstance(store);
    }
    public List<Store> getAllStore(){
        return storeDao.getAllInstance();
    }
    public Store getStoreByName(String name){
        List<Store> listOfStore = getAllStore(); 

        Optional<Store> foundStore= listOfStore.stream()
            .filter(Store -> Store.getName().equals(name))
            .findFirst();
            
        if (foundStore.isPresent()) {
            return foundStore.get();
        } else {
            return null;
        }
    }
    public List<Store> getStoreByAddress(String address){
        List<Store> listOfStore = getAllStore(); 

        List<Store> foundStore= listOfStore.stream()
            .filter(Store -> Store.getAddress().contains(address))
            .collect(Collectors.toList());
            
        if (!foundStore.isEmpty()) {
            return foundStore;
        } else {
            return null;
        }
    }
}
