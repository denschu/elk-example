package de.codecentric.batch.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import de.codecentric.batch.domain.Item;

public class LogItemWriter implements ItemWriter<Item> {
	
	private static final Logger log = LoggerFactory.getLogger(LogItemWriter.class);

	@Override
	public void write(List<? extends Item> items) throws Exception {
		log.info("Written->" + items.toString());
	}

}
