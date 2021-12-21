/*
 * package com.revature.data.collections;
 * 
 * import java.util.HashSet; import java.util.Set;
 * 
 * import com.revature.beans.Stock; import com.revature.data.StockDAO;
 * 
 * @Deprecated public class StockCollection implements StockDAO { private
 * Set<Stock> allPeople; private int lastIndex;
 * 
 * public StockCollection() { lastIndex = 0;
 * 
 * allPeople = new HashSet<>(); Stock person1 = new Stock();
 * person1.setUsername("sierra");
 * 
 * create(person1); }
 * 
 * @Override public int create(Stock dataToAdd) { lastIndex++;
 * dataToAdd.setId(lastIndex); allPeople.add(dataToAdd); return
 * dataToAdd.getId(); }
 * 
 * @Override public Stock getById(int id) { for (Stock stock : allPeople) { if
 * (stock.getId() == id) { return stock; } } return null; }
 * 
 * @Override public Set<Stock> getAll() { return allPeople; }
 * 
 * @Override public void update(Stock dataToUpdate) { Stock previousData =
 * getById(dataToUpdate.getId()); if (previousData != null) {
 * allPeople.remove(previousData); allPeople.add(dataToUpdate); } }
 * 
 * @Override public void delete(Stock dataToDelete) { Stock previousData =
 * getById(dataToDelete.getId()); if (previousData != null) {
 * allPeople.remove(previousData); } }
 * 
 * @Override public Stock getByUsername(String username) { for (Stock stock :
 * allPeople) { if (stock.getUsername().equals(username)) { return stock; } }
 * return null; }
 * 
 * }
 */