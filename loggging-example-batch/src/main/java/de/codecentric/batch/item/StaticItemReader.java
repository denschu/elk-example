package de.codecentric.batch.item;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;

import de.codecentric.batch.domain.Item;

public class StaticItemReader implements ItemReader<Item> {

	private Integer index = 0;
	
	private LocalDateTime startTime;
	
	private Integer duration = 10;
	
	private List<Item> items;
	
	public StaticItemReader() {
		items = new ArrayList<Item>();
		index = 0;
		items.add(new Item("Item A",true));
		items.add(new Item("Item B",true));
		items.add(new Item("Item C",true));
		items.add(new Item("Item D",true));
		items.add(new Item("Item E",false));
		items.add(new Item("Item F",true));
		items.add(new Item("Item G",true));
		items.add(new Item("Item H",true));
	}
	
	@Override
	public Item read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(index >= items.size()){
			index = 0;
		}
		if(startTime == null){
			startTime = LocalDateTime.now();
		}
		if(duration == null){
			duration = 1;
		}
		if(startTime.plusSeconds(duration).isAfter(LocalDateTime.now())){
			Thread.sleep(1000);
			return items.get(index++);
		}
		return null;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
