package de.codecentric.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import de.codecentric.batch.domain.Item;

/**
 * Dummy {@link ItemProcessor} which only logs data it receives.
 */
public class LogItemProcessor implements ItemProcessor<Item,Item> {

	private static final Logger log = LoggerFactory.getLogger(LogItemProcessor.class);
	
	public Item process(Item item) throws Exception {
		log.info("Processed->" + item.toString());
		return item;
	}

}
